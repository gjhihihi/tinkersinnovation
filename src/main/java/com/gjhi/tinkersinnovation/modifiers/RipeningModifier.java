package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.BlockInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;
import java.util.Random;

public class RipeningModifier extends Modifier implements MeleeHitModifierHook, BlockInteractionModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.BLOCK_INTERACT);
    }

    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (target != null) {
            if (target.isBaby() && RANDOM.nextFloat() < 0.1f * modifier.getLevel()) {
                if (target instanceof AgeableMob age) {
                    age.setBaby(false);
                }
            }
        }
    }

    @Override
    public @NotNull InteractionResult afterBlockUse(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, UseOnContext context, InteractionSource source) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
        if (player != null) {
            if (applyBonemeal(context.getItemInHand(), tool, modifier, level, blockpos, player)) {
                if (!level.isClientSide) {
                    level.levelEvent(1505, blockpos, 0);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.isFaceSturdy(level, blockpos, context.getClickedFace()) && growWaterPlant(context.getItemInHand(), tool, modifier, level, blockpos1, context.getClickedFace(), player)) {
                    if (!level.isClientSide) {
                        level.levelEvent(1505, blockpos1, 0);
                    }
                    return InteractionResult.sidedSuccess(level.isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            }
        }
        return InteractionResult.PASS;
    }
    private static boolean applyBonemeal(ItemStack item, @NotNull IToolStackView tool, @NotNull ModifierEntry modifier, Level world, BlockPos pos, Player player) {
        if (tool.getCurrentDurability() - tool.getDamage() < 100)
            return false;
        BlockState blockstate = world.getBlockState(pos);
        int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, blockstate, item);
        if (hook != 0) {
            return hook > 0;
        } else {
            if (blockstate.getBlock() instanceof BonemealableBlock bonemealableblock) {
                if (bonemealableblock.isValidBonemealTarget(world, pos, blockstate, world.isClientSide)) {
                    if (world instanceof ServerLevel) {
                        if (bonemealableblock.isBonemealSuccess(world, world.random, pos, blockstate)) {
                            bonemealableblock.performBonemeal((ServerLevel)world, world.random, pos, blockstate);
                            if (RANDOM.nextFloat() > 0.2f * modifier.getLevel()){
                                bonemealableblock.performBonemeal((ServerLevel)world, world.random, pos, blockstate);
                                bonemealableblock.performBonemeal((ServerLevel)world, world.random, pos, blockstate);
                            }
                        }
                        ToolDamageUtil.damageAnimated(tool, 25, player);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean growWaterPlant(ItemStack item, @NotNull IToolStackView tool, @NotNull ModifierEntry modifier, Level world, BlockPos pos, @Nullable Direction direction, Player player) {
        if (tool.getCurrentDurability() - tool.getDamage() < 100)
            return false;
        if (world.getBlockState(pos).is(Blocks.WATER) && world.getFluidState(pos).getAmount() == 8) {
            if (!(world instanceof ServerLevel)) {
                return true;
            } else {
                RandomSource random = world.getRandom();

                label77:
                for(int i = 0; i < 128; ++i) {
                    BlockPos blockpos = pos;
                    BlockState blockstate = Blocks.SEAGRASS.defaultBlockState();

                    for(int j = 0; j < i / 16; ++j) {
                        blockpos = blockpos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                        if (world.getBlockState(blockpos).isCollisionShapeFullBlock(world, blockpos)) {
                            continue label77;
                        }
                    }

                    Holder<Biome> holder = world.getBiome(blockpos);
                    if (holder.is(Biomes.WARM_OCEAN)) {
                        if (i == 0 && direction != null && direction.getAxis().isHorizontal()) {
                            blockstate = Registry.BLOCK.getTag(BlockTags.WALL_CORALS).flatMap((block) -> block.getRandomElement(world.random)).map((p_204100_) -> p_204100_.value().defaultBlockState()).orElse(blockstate);
                            if (blockstate.hasProperty(BaseCoralWallFanBlock.FACING)) {
                                blockstate = blockstate.setValue(BaseCoralWallFanBlock.FACING, direction);
                            }
                        } else if (random.nextInt(4) == 0) {
                            blockstate = Registry.BLOCK.getTag(BlockTags.UNDERWATER_BONEMEALS).flatMap((block) -> block.getRandomElement(world.random)).map((p_204095_) -> p_204095_.value().defaultBlockState()).orElse(blockstate);
                        }
                    }

                    if (blockstate.is(BlockTags.WALL_CORALS, (blo) -> blo.hasProperty(BaseCoralWallFanBlock.FACING))) {
                        for(int k = 0; !blockstate.canSurvive(world, blockpos) && k < 4; ++k) {
                            blockstate = blockstate.setValue(BaseCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random));
                        }
                    }

                    if (blockstate.canSurvive(world, blockpos)) {
                        BlockState blockstate1 = world.getBlockState(blockpos);
                        if (blockstate1.is(Blocks.WATER) && world.getFluidState(blockpos).getAmount() == 8) {
                            world.setBlock(blockpos, blockstate, 3);
                        } else if (blockstate1.is(Blocks.SEAGRASS) && random.nextInt(10) == 0) {
                            ((BonemealableBlock)Blocks.SEAGRASS).performBonemeal((ServerLevel)world, random, blockpos, blockstate1);
                            if (RANDOM.nextFloat() > 0.2f * modifier.getLevel()){
                                ((BonemealableBlock)Blocks.SEAGRASS).performBonemeal((ServerLevel)world, random, blockpos, blockstate1);
                                ((BonemealableBlock)Blocks.SEAGRASS).performBonemeal((ServerLevel)world, random, blockpos, blockstate1);
                            }
                        }
                    }
                }
                ToolDamageUtil.damageAnimated(tool, 25, player);
                return true;
            }
        } else {
            return false;
        }
    }
}