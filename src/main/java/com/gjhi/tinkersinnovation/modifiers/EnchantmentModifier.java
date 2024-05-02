package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;


public class EnchantmentModifier extends Modifier implements MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.MELEE_HIT);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        int level = modifier.getLevel();
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null) {
            if(RANDOM.nextFloat()<0.1){
               target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.HUNGER, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40 * level ,level-1));
            }
            //------------------------------------------------------------
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, 40 * level ,level-1));
            }
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40 * level,level - 1));
            }
            if (RANDOM.nextFloat()<0.1){
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40 * level ,level-1));
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
    }
    @Override
    public void failedMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageAttempted) {
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null) {
                target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 1200 * modifier.getLevel() ,2));
        }
    }
}
