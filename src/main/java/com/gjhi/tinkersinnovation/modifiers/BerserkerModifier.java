package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BerserkerModifier extends Modifier implements OnAttackedModifierHook {
    private static final DamageSource BERSERKER = (new DamageSource(TConstruct.prefix("berserker"))).bypassArmor();
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED);
    }
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        int level = modifier.getLevel();
        LivingEntity player = context.getEntity();
        if(!player.hasEffect(MobEffects.DAMAGE_RESISTANCE)){
            player.hurt(BERSERKER,level * level);
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,level*200,level-1));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,level*200,level-1));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,level*200,level-1));
        }
    }
}
