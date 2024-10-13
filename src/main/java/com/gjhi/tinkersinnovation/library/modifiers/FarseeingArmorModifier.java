package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class FarseeingArmorModifier extends NoLevelsModifier implements OnAttackedModifierHook {
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity wearer = context.getEntity();
        LivingEntity target = null;
        if (source.getEntity() instanceof LivingEntity entity){
            target = entity;
        }
        if (target != null && target.distanceToSqr(wearer) < 5){
            double damage = amount * 0.2 * (5 - target.distanceToSqr(wearer));
            target.hurt(DamageSource.MAGIC.bypassArmor().bypassMagic(), (float) damage);
        }
    }
}
