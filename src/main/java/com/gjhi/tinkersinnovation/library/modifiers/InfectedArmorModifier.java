package com.gjhi.tinkersinnovation.library.modifiers;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class InfectedArmorModifier extends NoLevelsModifier implements OnAttackedModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED);
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity target = null;
        if (source.getEntity() instanceof LivingEntity entity){
            target = entity;
        }
        if (target != null){
            MobEffectInstance flu = target.getEffect(AMEffectRegistry.ENDER_FLU.get());
            if (flu != null){
                MobEffectInstance newflu;
                if (flu.getDuration() <= 100){
                    newflu = new MobEffectInstance(flu.getEffect(), 2, flu.getAmplifier(), flu.isAmbient(), flu.isVisible(), flu.showIcon());
                }else {
                    newflu = new MobEffectInstance(flu.getEffect(), flu.getDuration() - 100, flu.getAmplifier(), flu.isAmbient(), flu.isVisible(), flu.showIcon());
                }
                target.removeEffect(AMEffectRegistry.ENDER_FLU.get());
                target.addEffect(newflu);
            }else {
                if (RANDOM.nextFloat() < 0.333){
                    target.addEffect(new MobEffectInstance(AMEffectRegistry.ENDER_FLU.get(), 600));
                }
            }
        }
    }
}
