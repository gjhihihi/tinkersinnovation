package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.modifiers.base.EnchantmentEffectsBase;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
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
            for (MobEffect effect : EnchantmentEffectsBase.getBadEffectsByCopy()){
                if (RANDOM.nextFloat() < 0.1){
                    TinkersInnovationUtils.updateEffect(target, effect, 1, 2 * level, 40 * level);
                }
            }
            for (MobEffect effect : EnchantmentEffectsBase.getGoodEffectsByCopy()){
                if (RANDOM.nextFloat() < 0.1){
                    TinkersInnovationUtils.updateEffect(player, effect, 1, 2 * level, 40 * level);
                }
            }
        }
    }
}
