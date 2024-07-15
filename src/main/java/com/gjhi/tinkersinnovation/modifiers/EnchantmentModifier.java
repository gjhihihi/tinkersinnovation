package com.gjhi.tinkersinnovation.modifiers;

import com.gjhi.tinkersinnovation.modifiers.base.EnchantmentEffectsBase;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import javax.annotation.Nullable;


public class EnchantmentModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        int level = modifier.getLevel();
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null) {
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
    @Override
    public void failedMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageAttempted) {
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null) {
                target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 1200 * modifier.getLevel() ,2));
        }
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @Nullable LivingEntity player, @Nullable LivingEntity target) {
        int level = modifier.getLevel();
        if (player != null && target != null) {
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
        return false;
    }
}
