package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ProtectionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.armor.ProtectionModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public class AdversityProtectionModifier extends Modifier implements ProtectionModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROTECTION, ModifierHooks.TOOLTIP);
    }

    @Override
    public float getProtectionModifier(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float modifierValue) {
        float factor = 0;
        LivingEntity attacker = context.getEntity();
        for (MobEffectInstance effect : attacker.getActiveEffects()) {
            if (effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)) {
                factor += 0.05f * (effect.getAmplifier() + 1) * modifier.getLevel();
            }
        }
        return modifierValue + factor;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player != null && tooltipKey.equals(TooltipKey.SHIFT)) {
            float factor = 0;
            for (MobEffectInstance effect : player.getActiveEffects()) {
                if (effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)) {
                    factor += 0.05f * (effect.getAmplifier() + 1) * modifier.getLevel();
                }
            }
            ProtectionModule.addResistanceTooltip(tool, modifier.getModifier(), factor, player, tooltip);
        }
    }
}
