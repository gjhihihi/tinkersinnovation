package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public class EnchantmentModifier extends Modifier {
    @Override
    public int afterEntityHit(@NotNull IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null) {
            if (nextDouble()<0.1){
               MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40 * level ,level-1);
               target.addEffect(effect);
            }
            if (nextDouble()<0.1){
                MobEffectInstance effect = new MobEffectInstance(MobEffects.WITHER, 40 * level ,level-1);
                target.addEffect(effect);
            }
            if (nextDouble()<0.1){
                MobEffectInstance effect = new MobEffectInstance(MobEffects.WEAKNESS, 40 * level ,level-1);
                target.addEffect(effect);
            }
            if (nextDouble()<0.1){
                MobEffectInstance effect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40 * level ,level-1);
                target.addEffect(effect);
            }
            if (nextDouble()<0.1){
                MobEffectInstance effect = new MobEffectInstance(MobEffects.BLINDNESS, 40 * level ,level-1);
                target.addEffect(effect);
            }
            if (nextDouble()<0.1){
                MobEffectInstance effect = new MobEffectInstance(MobEffects.HUNGER, 40 * level ,level-1);
                target.addEffect(effect);
            }
            if (nextDouble()<0.1){
                MobEffectInstance effect = new MobEffectInstance(MobEffects.POISON, 40 * level ,level-1);
                target.addEffect(effect);
            }
            if (nextDouble()<0.1){
                MobEffectInstance effect = new MobEffectInstance(MobEffects.CONFUSION, 40 * level ,level-1);
                target.addEffect(effect);
            }
        }
        return level;
    }
    @Override
    public void failedEntityHit(@NotNull IToolStackView tool, int level, @NotNull ToolAttackContext context) {
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null) {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.CONFUSION, 120 * level ,2);
                target.addEffect(effect);
        }
    }
}
