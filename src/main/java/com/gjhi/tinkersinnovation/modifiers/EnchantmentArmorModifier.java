package com.gjhi.tinkersinnovation.modifiers;

import com.gjhi.tinkersinnovation.modifiers.base.EnchantmentEffectsBase;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;


public class EnchantmentArmorModifier extends Modifier implements OnAttackedModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED);
    }
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        int level = modifier.getLevel();
        LivingEntity player = context.getEntity();
        LivingEntity target = null;
        if (source.getEntity() instanceof LivingEntity entity){
            target = entity;
        }
        if (target != null) {
            for (MobEffect effect : EnchantmentEffectsBase.BAD_EFFECTS){
                if (RANDOM.nextFloat()<0.1){
                    target.addEffect(new MobEffectInstance(effect, 40 * level ,level-1));
                }
            }
            for (MobEffect effect : EnchantmentEffectsBase.GOOD_EFFECTS){
                if (RANDOM.nextFloat()<0.1){
                    player.addEffect(new MobEffectInstance(effect, 40 * level ,level-1));
                }
            }
        }
    }
}
