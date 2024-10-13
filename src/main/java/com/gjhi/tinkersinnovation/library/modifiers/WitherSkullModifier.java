package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class WitherSkullModifier extends Modifier implements ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (RANDOM.nextFloat() > 0.1 * modifier.getLevel()){
            projectile.level.explode(projectile, projectile.getX(), projectile.getY(), projectile.getZ(), 1, Explosion.BlockInteraction.BREAK);
        }else {
            projectile.level.explode(projectile, projectile.getX(), projectile.getY(), projectile.getZ(), 2, Explosion.BlockInteraction.DESTROY);
        }
        if (target != null) {
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, modifier.getLevel() - 1));
        }
        return false;
    }
}
