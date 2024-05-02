package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.definition.aoe.IAreaOfEffectIterator;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.helper.ToolHarvestLogic;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.BlockSideHitListener;

import java.util.Objects;


public class VoidModifier extends Modifier implements ProjectileHitModifierHook, MeleeDamageModifierHook {
    @Override
    public int getPriority() {
        return 120;
    }
    public VoidModifier() {
        MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
    }
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.MELEE_DAMAGE, TinkerHooks.PROJECTILE_HIT);
    }

    private void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        BlockState state = event.getEntity().level.getBlockState(event.getPos());
        if (!event.getPlayer().isCreative() && state.getDestroySpeed(event.getEntity().level, event.getPos()) < 0.0F) {
            ToolStack tool = getHeldTool(event.getPlayer(), InteractionHand.MAIN_HAND);
            if (tool == null || tool.isBroken() || tool.getModifierLevel(this) < 1)
                return;

            Player player = event.getPlayer();
            Level world = player.getLevel();
            BlockPos pos = event.getPos();

            state.getBlock().playerWillDestroy(world, pos, state, player);

            if (world instanceof ServerLevel) {
                ItemStack toolStack = player.getItemBySlot(EquipmentSlot.MAINHAND);
                Direction sideHit = BlockSideHitListener.getSideHit(player);
                ToolHarvestContext context = new ToolHarvestContext((ServerLevel) world, player, state, pos, sideHit, true, true);

                if (breakBlock(tool, toolStack, context)) {
                    Iterable<BlockPos> extraBlocks = tool.getDefinition().getData().getAOE().getBlocks(tool, toolStack, player, state, world, pos, sideHit, IAreaOfEffectIterator.AOEMatchType.BREAKING);
                    for (BlockPos extraPos : extraBlocks) {
                        BlockState extraState = world.getBlockState(extraPos);
                        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
                        // this should never actually happen, but just in case some AOE is odd
                        if (!extraState.isAir()) {
                            // prevent mutable position leak, breakBlock has a few places wanting immutable
                            ToolHarvestLogic.breakExtraBlock(tool, toolStack, context.forPosition(extraPos.immutable(), extraState));
                        }
                    }
                    for (ModifierEntry entry : tool.getModifierList()) {
                        entry.getModifier().getHook(TinkerHooks.FINISH_HARVEST).finishHarvest(tool, entry, context);
                    }

                    //bedrock has no loot table
                    if (state.getBlock().getLootTable() == BuiltInLootTables.EMPTY) {
                        Block.popResource(world, pos, new ItemStack(state.getBlock()));
                    }
                }
            }
        }
    }
    protected static boolean breakBlock(ToolStack tool, ItemStack stack, ToolHarvestContext context) {
        // have to rerun the event to get the EXP, also ensures extra blocks broken get EXP properly
        ServerPlayer player = Objects.requireNonNull(context.getPlayer());
        ServerLevel world = context.getWorld();
        BlockPos pos = context.getPos();
        GameType type = player.gameMode.getGameModeForPlayer();
        int exp = ForgeHooks.onBlockBreakEvent(world, type, player, pos);
        if (exp == -1) {
            return false;
        }
        // checked after the Forge hook, so we have to recheck
		/*if (player.blockActionRestricted(world, pos, type)) {
			return false;
		}*/

        // creative just removes the block
        if (player.isCreative()) {
            removeBlock(tool, context);
            return true;
        }

        // determine damage to do
        BlockState state = context.getState();
        int damage = ToolHarvestLogic.getDamage(tool, world, pos, state);

        // remove the block
        BlockEntity te = world.getBlockEntity(pos); // ensures tile entity is fetched so its around for afterBlockBreak
        boolean removed = removeBlock(tool, context);

        // harvest drops
        Block block = state.getBlock();
        if (removed) {
            block.playerDestroy(world, player, pos, state, te, stack);
        }

        // drop XP
        if (removed && exp > 0) {
            block.popExperience(world, pos, exp);
        }

        // handle modifiers if not broken
        // broken means we are using "empty hand"
        if (!tool.isBroken()) {
            for (ModifierEntry entry : tool.getModifierList()) {
                entry.getModifier().getHook(TinkerHooks.BLOCK_BREAK).afterBlockBreak(tool, entry, context);
            }
            ToolDamageUtil.damageAnimated(tool, damage, player);
        }

        return true;
    }
    /*@Override
    public Boolean removeBlock(IToolStackView tool, int level, ToolHarvestContext context) {
        Block block =context.getState().getBlock();
        if (block.equals(Blocks.BEDROCK)){
            block.destroy(context.getWorld(),context.getPos(),context.getState());
            return true;
        }
        return null;
    }*/
    private static boolean removeBlock(IToolStackView tool, ToolHarvestContext context) {
        Boolean removed = null;
        if (!tool.isBroken()) {
            for (ModifierEntry entry : tool.getModifierList()) {
                removed = entry.getModifier().getHook(TinkerHooks.REMOVE_BLOCK).removeBlock(tool, entry, context);
                if (removed != null) {
                    break;
                }
            }
        }
        // if not removed by any modifier, remove with normal forge hook
        BlockState state = context.getState();
        ServerLevel world = context.getWorld();
        BlockPos pos = context.getPos();
        if (removed == null) {
            removed = state.onDestroyedByPlayer(world, pos, context.getPlayer(), context.canHarvest(), world.getFluidState(pos));
        }
        // if removed by anything, finally destroy it
        if (removed) {
            state.getBlock().destroy(world, pos, state);
        }
        return removed;
    }
    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        float voiddamage = damage * 0.05f * modifier.getLevel();
        LivingEntity entity = context.getLivingTarget();
        if (entity != null) {
            entity.hurt(DamageSource.OUT_OF_WORLD,voiddamage);
            entity.invulnerableTime = 0;
            return damage - voiddamage;
        }
        return damage;
    }
}