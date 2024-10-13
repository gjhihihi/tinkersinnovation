package com.gjhi.tinkersinnovation.library.modifiers;

import dev.xkmc.l2hostility.compat.curios.CurioCompat;
import dev.xkmc.l2hostility.init.data.LHConfig;
import dev.xkmc.l2hostility.init.registrate.LHItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.DamageDealtModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class FieryTraitModifier extends NoLevelsModifier implements OnAttackedModifierHook, DamageDealtModifierHook, DamageBlockModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED, ModifierHooks.DAMAGE_DEALT, ModifierHooks.DAMAGE_BLOCK);
    }
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity target = null;
        if (source.getEntity() instanceof LivingEntity entity){
            target = entity;
        }
        if (target != null){
            if (CurioCompat.hasItem(target, LHItems.ABRAHADABRA.get()))return;
            target.setSecondsOnFire(LHConfig.COMMON.fieryTime.get());
        }
    }
    @Override
    public void onDamageDealt(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, LivingEntity target, DamageSource source, float amount, boolean isDirectDamage) {
        if (CurioCompat.hasItem(target, LHItems.ABRAHADABRA.get()))return;
        target.setSecondsOnFire(LHConfig.COMMON.fieryTime.get());
    }
    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        return source.isFire();
    }
}
