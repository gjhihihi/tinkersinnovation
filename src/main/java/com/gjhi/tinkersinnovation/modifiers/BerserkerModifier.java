package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BerserkerModifier extends Modifier {
    @Override
    public void onAttacked(IToolStackView tool, int level, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity player = context.getEntity();
        if(!player.hasEffect(MobEffects.DAMAGE_RESISTANCE)){
            player.hurt(DamageSource.OUT_OF_WORLD,level * level);
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,level*200,level-1));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,level*200,level-1));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,level*200,level-1));
        }
    }
}
