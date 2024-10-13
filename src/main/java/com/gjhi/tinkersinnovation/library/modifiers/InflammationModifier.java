package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import java.util.Iterator;

public class InflammationModifier extends Modifier implements MeleeDamageModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        float factor = 1;
        if (target != null && projectile instanceof AbstractArrow arrow){
            for (MobEffectInstance effect : target.getActiveEffects()){
                if (effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)){
                    factor += 0.1f * (effect.getAmplifier() + 1);
                }else if (effect.getEffect().getCategory().equals(MobEffectCategory.BENEFICIAL)){
                    factor -= 0.1f * (effect.getAmplifier() + 1);
                }
            }
            arrow.setBaseDamage(arrow.getBaseDamage() * Math.max(factor, 0) * modifier.getLevel());
        }
        return false;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        float factor = 1;
        if (target != null){
            for (MobEffectInstance effect : target.getActiveEffects()){
                if (effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)){
                    factor += 0.1f * (effect.getAmplifier() + 1);
                }else if (effect.getEffect().getCategory().equals(MobEffectCategory.BENEFICIAL)){
                    factor -= 0.1f * (effect.getAmplifier() + 1);
                }
            }
        }
        return damage * Math.max(factor, 0) * modifier.getLevel();
    }
}
