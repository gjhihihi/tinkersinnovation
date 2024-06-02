package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.DamageTakenModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class SuppressArmorModifier extends Modifier implements DamageTakenModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.DAMAGE_TAKEN);
    }
    @Override
    public void onDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (source.getEntity() instanceof LivingEntity entity){
            entity.removeEffect(MobEffects.WATER_BREATHING);
            entity.removeEffect(MobEffects.DAMAGE_RESISTANCE);
            entity.removeEffect(MobEffects.FIRE_RESISTANCE);
            entity.removeEffect(MobEffects.LUCK);
            entity.removeEffect(MobEffects.HEALTH_BOOST);
            entity.removeEffect(MobEffects.REGENERATION);
            entity.removeEffect(MobEffects.ABSORPTION);
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, modifier.getLevel() * 200, 1));
        }
    }
}