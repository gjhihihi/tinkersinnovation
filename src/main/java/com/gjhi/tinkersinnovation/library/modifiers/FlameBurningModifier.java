package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;


public class FlameBurningModifier extends Modifier implements OnAttackedModifierHook {
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
            int time = target.getRemainingFireTicks();
            if (time > 0){
                target.hurt(DamageSource.ON_FIRE, time / 200.0f);
            }
            target.setRemainingFireTicks(target.getRemainingFireTicks() + 60);
        }
    }
}
