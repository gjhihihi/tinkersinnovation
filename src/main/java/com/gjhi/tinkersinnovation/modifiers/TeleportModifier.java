package com.gjhi.tinkersinnovation.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.james.tinkerscalibration.TinkersCalibration;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.Mantle;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.data.FloatMultiplier;
import slimeknights.tconstruct.library.modifiers.hook.KeybindInteractModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.BlockInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.capability.TinkerDataKeys;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;


public class TeleportModifier extends NoLevelsModifier implements BlockInteractionModifierHook {
    private final ResourceLocation X = new ResourceLocation(TinkersInnovation.MOD_ID, "teleport_x");
    private final ResourceLocation Y = new ResourceLocation(TinkersInnovation.MOD_ID, "teleport_y");
    private final ResourceLocation Z = new ResourceLocation(TinkersInnovation.MOD_ID, "teleport_z");
    private final ResourceLocation WORLD = new ResourceLocation(TinkersInnovation.MOD_ID, "teleport_dimension");
    private static final Component GLOBAL_POS = TConstruct.makeTranslation("modifier", "teleport.pos");
    private static final String UNLINK_SUCCEED = Mantle.makeDescriptionId("modifier", "teleport.unlink");
    private static final String LINK_SUCCEED = Mantle.makeDescriptionId("modifier", "teleport.link");

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.BLOCK_INTERACT);
    }
    @Override
    public int getPriority() {
        return 10;
    }
    @Override
    public InteractionResult afterBlockUse(IToolStackView tool, ModifierEntry modifier, UseOnContext context, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK && tool.getCurrentDurability() >= 10 && context.getPlayer() != null && context.getPlayer().isCrouching() || tool.hasTag(TinkerTags.Items.RANGED) && source == InteractionSource.LEFT_CLICK && tool.getCurrentDurability() >= 10 && context.getPlayer() != null && context.getPlayer().isCrouching()) {
            Player player = context.getPlayer();
            if (!context.getLevel().isClientSide && player != null) {
                Level world = context.getLevel();
                BlockPos pos = context.getClickedPos();
                BlockEntity block = world.getBlockEntity(pos);
                if (block != null && block.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()) {
                    ModDataNBT persistentData = tool.getPersistentData();
                    if (persistentData.contains(X, 3) && persistentData.contains(Y, 3) && persistentData.contains(Z, 3) && persistentData.contains(WORLD, 8)) {
                        if (persistentData.getInt(X) == pos.getX() && persistentData.getInt(Y) == pos.getY() && persistentData.getInt(Z) == pos.getZ() && persistentData.getString(WORLD).equals(world.dimension().location().getPath())) {
                            persistentData.remove(X);
                            persistentData.remove(Y);
                            persistentData.remove(Z);
                            persistentData.remove(WORLD);
                            player.displayClientMessage(new TranslatableComponent(UNLINK_SUCCEED, pos.toShortString(), world.dimension().location().getPath()), true);
                        } else {
                            persistentData.putInt(X, pos.getX());
                            persistentData.putInt(Y, pos.getY());
                            persistentData.putInt(Z, pos.getZ());
                            persistentData.putString(WORLD, world.dimension().location().getPath());
                            player.displayClientMessage(new TranslatableComponent(LINK_SUCCEED, pos.toShortString(), world.dimension().location().getPath()), true);
                        }
                    } else {
                        persistentData.putInt(X, pos.getX());
                        persistentData.putInt(Y, pos.getY());
                        persistentData.putInt(Z, pos.getZ());
                        persistentData.putString(WORLD, world.dimension().location().getPath());
                        player.displayClientMessage(new TranslatableComponent(LINK_SUCCEED, pos.toShortString(), world.dimension().location().getPath()), true);
                    }
                    player.getCooldowns().addCooldown(tool.getItem(), 40);
                    ToolDamageUtil.damageAnimated(tool, 5, player);
                    return InteractionResult.sidedSuccess(context.getLevel().isClientSide);
                }
            }

        }
        return InteractionResult.PASS;
    }
}
