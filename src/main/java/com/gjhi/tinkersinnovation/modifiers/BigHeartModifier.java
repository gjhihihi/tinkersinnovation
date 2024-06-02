package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.DamageTakenModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BigHeartModifier extends NoLevelsModifier implements DamageTakenModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.DAMAGE_TAKEN);
    }
    @Override
    public void onDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity player = context.getEntity();
        if(!player.hasEffect(MobEffects.REGENERATION)){
            float LOVE=player.getHealth()/player.getMaxHealth();
            if(LOVE < 1f){
                int lv = 0;
                if(LOVE < 0.25f){
                    lv = 3;
                } else if (LOVE <0.5f) {
                    lv = 2;
                } else if (LOVE <0.75f) {
                    lv = 1;
                }
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, lv));
            }
        }
    }
}
