package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import slimeknights.mantle.Mantle;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.BlockInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.definition.module.weapon.MeleeHitToolHook;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;


public class TeleportModifier extends NoLevelsModifier implements GeneralInteractionModifierHook, MeleeHitModifierHook, TooltipModifierHook {
    private final ResourceLocation X = new ResourceLocation(TinkersInnovation.MOD_ID, "teleport_x");
    private final ResourceLocation Y = new ResourceLocation(TinkersInnovation.MOD_ID, "teleport_y");
    private final ResourceLocation Z = new ResourceLocation(TinkersInnovation.MOD_ID, "teleport_z");
    private final ResourceLocation WORLD = new ResourceLocation(TinkersInnovation.MOD_ID, "teleport_dimension");
    private static final String TELEPORT_SUCCEED = Mantle.makeDescriptionId("modifier", "teleport.tp_successful");
    private static final String TELEPORT_FAILED = Mantle.makeDescriptionId("modifier", "teleport.tp_failed");
    private static final String LINK_SUCCEED = Mantle.makeDescriptionId("modifier", "teleport.link");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT, ModifierHooks.MELEE_HIT, ModifierHooks.TOOLTIP);
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK && !tool.isBroken()) {
            if (player.isCrouching()) {
                if (!player.getLevel().isClientSide) {
                    Level world = player.getLevel();
                    ModDataNBT data = tool.getPersistentData();
                    data.putFloat(X, (float) player.getX());
                    data.putFloat(Y, (float) player.getY());
                    data.putFloat(Z, (float) player.getZ());
                    data.putString(WORLD, world.dimension().location().getPath());
                    ToolDamageUtil.damageAnimated(tool, 10, player);
                    player.getCooldowns().addCooldown(tool.getItem(), 100);
                    player.displayClientMessage(Component.translatable(LINK_SUCCEED, data.getFloat(X), data.getFloat(Y), data.getFloat(Z)), true);
                }
            }else {
                if (!player.hasEffect(TinkerModifiers.teleportCooldownEffect.get()) || !player.hasEffect(TinkerModifiers.enderferenceEffect.get())) {
                        Level world = player.getLevel();
                        ModDataNBT data = tool.getPersistentData();
                        if (data.contains(X, Tag.TAG_FLOAT) && data.contains(Y, Tag.TAG_FLOAT) && data.contains(Z, Tag.TAG_FLOAT) && data.contains(WORLD, Tag.TAG_STRING)) {
                            if (data.getString(WORLD).equals(world.dimension().location().getPath())) {
                                player.setPos(data.getFloat(X), data.getFloat(Y), data.getFloat(Z));
                                ToolDamageUtil.damageAnimated(tool, 5, player);
                                player.addEffect(new MobEffectInstance(TinkerModifiers.teleportCooldownEffect.get(), 100));
                                player.displayClientMessage(Component.translatable(TELEPORT_SUCCEED, data.getFloat(X), data.getFloat(Y), data.getFloat(Z)), true);
                            }else {
                                player.displayClientMessage(Component.translatable(TELEPORT_FAILED), true);
                            }
                        }else {
                            player.displayClientMessage(Component.translatable("tooltip.tinkersinnovation.teleport_pos_null"), true);
                        }
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        LivingEntity target = context.getLivingTarget();
        if (!tool.isBroken()) {
            if (target != null && !target.hasEffect(TinkerModifiers.enderferenceEffect.get())) {
                    Level world = target.getLevel();
                    ModDataNBT data = tool.getPersistentData();
                    if (data.contains(X, Tag.TAG_FLOAT) && data.contains(Y, Tag.TAG_FLOAT) && data.contains(Z, Tag.TAG_FLOAT) && data.contains(WORLD, Tag.TAG_STRING)) {
                        if (data.getString(WORLD).equals(world.dimension().location().getPath())) {
                            target.setPos(data.getFloat(X), data.getFloat(Y), data.getFloat(Z));
                            ToolDamageUtil.damageAnimated(tool, 5, context.getAttacker());
                            return 0;
                        }
                    }
            }
        }
        return knockback;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        ModDataNBT data = tool.getPersistentData();
        if (data.contains(X, Tag.TAG_FLOAT) && data.contains(Y, Tag.TAG_FLOAT) && data.contains(Z, Tag.TAG_STRING)) {
            tooltip.add(Component.translatable("tooltip.tinkersinnovation.teleport_pos", data.getFloat(X), data.getFloat(Y), data.getFloat(Z)));
        }else {
            tooltip.add(Component.translatable("tooltip.tinkersinnovation.teleport_pos_null"));
        }
    }
}
