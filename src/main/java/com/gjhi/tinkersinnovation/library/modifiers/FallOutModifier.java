package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
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

public class FallOutModifier extends Modifier implements MeleeDamageModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        LivingEntity target = context.getLivingTarget();
        if (target != null){
            if (attacker.getLastHurtMob() != null){
                if (!attacker.getLastHurtMob().equals(target)){
                    damage += damage * 0.1f * modifier.getLevel();
                }
            }else {
                damage += damage * 0.1f * modifier.getLevel();
            }
        }
        return damage;
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (attacker != null && target != null && projectile instanceof AbstractArrow arrow){
            if (attacker.getLastHurtMob() != null){
                if (!attacker.getLastHurtMob().equals(target)){
                    arrow.setBaseDamage(arrow.getBaseDamage() * (1 + 0.1 * modifier.getLevel()));
                }
            }else {
                arrow.setBaseDamage(arrow.getBaseDamage() * (1 + 0.1 * modifier.getLevel()));
            }
        }
        return false;
    }
}
