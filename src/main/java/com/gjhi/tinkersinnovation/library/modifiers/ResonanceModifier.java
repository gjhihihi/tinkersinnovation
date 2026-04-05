package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationDamageTypes;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
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
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.ArrayList;
import java.util.List;

import static com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers.double_attack;

public class ResonanceModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook, MonsterMeleeHitModifierHook.RedirectAfter {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_HIT, ModifierHooks.MELEE_HIT, ModifierHooks.MONSTER_MELEE_HIT);
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null && projectile instanceof AbstractArrow arrow) {
            int radius = modifier.getLevel() + modifiers.getEntry(TinkerModifiers.expanded.getId()).getLevel();
            List<LivingEntity> targets = TinkersInnovationUtils.getLivingEntitiesInRange(target, radius, true);
            if (attacker != null) {
                targets.remove(attacker);
            }
            for (LivingEntity living : targets) {
                living.hurt(TinkerDamageTypes.source(target.level().registryAccess(), TinkersInnovationDamageTypes.RESONANCE, projectile, attacker), (float) (arrow.getBaseDamage() * modifier.getEffectiveLevel()));
            }
        }
        return false;
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();
        if (target != null) {
            int radius = modifier.getLevel() + tool.getModifierLevel(TinkerModifiers.expanded.getId());
            List<LivingEntity> targets = TinkersInnovationUtils.getLivingEntitiesInRange(target, radius, true);
            targets.remove(attacker);
            for (LivingEntity living : targets) {
                living.hurt(TinkerDamageTypes.source(target.level().registryAccess(), TinkersInnovationDamageTypes.RESONANCE, attacker), damageDealt * 0.2f * modifier.getEffectiveLevel());
            }
        }
    }
}
