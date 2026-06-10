package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MonsterMeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

public class FocusingModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT, ModifierHooks.MONSTER_MELEE_HIT);
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null){
            int time = target.getRemainingFireTicks();
            if (time > 0){
                target.hurt(TinkerDamageTypes.source(target.level().registryAccess(), DamageTypes.ON_FIRE, attacker), time / 200f);
            }
            target.setRemainingFireTicks(target.getRemainingFireTicks() + 60);
        }
        return false;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (target != null){
            int time = target.getRemainingFireTicks();
            if (time > 0){
                target.hurt(TinkerDamageTypes.source(target.level().registryAccess(), DamageTypes.ON_FIRE, context.getAttacker()), time / 200f);
            }
            target.setRemainingFireTicks(target.getRemainingFireTicks() + 60);
        }
    }
}
