package com.gjhi.tinkersinnovation.library.modifiers;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;


public class InfectedModifier extends NoLevelsModifier implements MeleeHitModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null){
            MobEffectInstance flu = target.getEffect(AMEffectRegistry.ENDER_FLU.get());
            if (flu != null){
                MobEffectInstance newflu;
                if (flu.getDuration() <= 100){
                    newflu = new MobEffectInstance(flu.getEffect(), 2, flu.getAmplifier(), flu.isAmbient(), flu.isVisible(), flu.showIcon());
                }else {
                    newflu = new MobEffectInstance(flu.getEffect(), flu.getDuration() - 100, flu.getAmplifier(), flu.isAmbient(), flu.isVisible(), flu.showIcon());
                }
                target.removeEffect(AMEffectRegistry.ENDER_FLU.get());
                target.addEffect(newflu);
            }else {
                if (RANDOM.nextFloat() < 0.333){
                    target.addEffect(new MobEffectInstance(AMEffectRegistry.ENDER_FLU.get(), 600));
                }
            }
        }
        return false;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (target != null){
            MobEffectInstance flu = target.getEffect(AMEffectRegistry.ENDER_FLU.get());
            if (flu != null){
                MobEffectInstance newflu;
                if (flu.getDuration() <= 100){
                    newflu = new MobEffectInstance(flu.getEffect(), 2, flu.getAmplifier(), flu.isAmbient(), flu.isVisible(), flu.showIcon());
                }else {
                    newflu = new MobEffectInstance(flu.getEffect(), flu.getDuration() - 100, flu.getAmplifier(), flu.isAmbient(), flu.isVisible(), flu.showIcon());
                }
                target.removeEffect(AMEffectRegistry.ENDER_FLU.get());
                target.addEffect(newflu);
            }else {
                if (RANDOM.nextFloat() < 0.333){
                    target.addEffect(new MobEffectInstance(AMEffectRegistry.ENDER_FLU.get(), 600));
                }
            }
        }
    }

}
