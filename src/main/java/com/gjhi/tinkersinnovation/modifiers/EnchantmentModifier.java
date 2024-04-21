package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import static java.lang.Math.random;


public class EnchantmentModifier extends Modifier {
    @Override
    public int afterEntityHit(@NotNull IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null) {
            if(RANDOM.nextFloat()<0.1){
               target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40 * level ,level-1),target);
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, 40 * level ,level-1),target);
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40 * level ,level-1),target);
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40 * level ,level-1),target);
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40 * level ,level-1),target);
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.HUNGER, 40 * level ,level-1),target);
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 40 * level ,level-1),target);
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40 * level ,level-1),target);
            }
            //------------------------------------------------------------
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, 40 * level ,level-1),player);
            }
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40 * level,level - 1));
            }
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40 * level ,level-1));
            }
        }
        return level;
    }
    @Override
    public void failedEntityHit(@NotNull IToolStackView tool, int level, @NotNull ToolAttackContext context) {
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null) {
                target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 1200 * level ,2));
        }
    }
}
