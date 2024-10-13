package com.gjhi.tinkersinnovation.library.modifiers;

import com.github.alexthe666.iceandfire.entity.EntityStoneStatue;
import com.github.alexthe666.iceandfire.misc.IafDamageRegistry;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class PetrifiedModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        LivingEntity target = context.getLivingTarget();
        if (target != null && RANDOM.nextFloat() < 0.05 * modifier.getLevel()){
            boolean wasSuccessful = true;
            if (target instanceof Player) {
                wasSuccessful = target.hurt(IafDamageRegistry.causeGorgonDamage(target), Integer.MAX_VALUE);
            } else {
                if (!target.level.isClientSide)
                    target.remove(Entity.RemovalReason.KILLED);
            }
            if (wasSuccessful) {
                target.playSound(IafSoundRegistry.TURN_STONE, 1, 1);
                EntityStoneStatue statue = EntityStoneStatue.buildStatueEntity(target);
                statue.absMoveTo(target.getX(), target.getY(), target.getZ(), target.getYRot(), target.getXRot());
                statue.yBodyRot = target.getYRot();
                if (!target.level.isClientSide) {
                    target.level.addFreshEntity(statue);
                }
                ToolDamageUtil.damageAnimated(tool, 20, context.getAttacker(), context.getHand());
                return 0;
            }
        }
        return knockback;
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null && RANDOM.nextFloat() < 0.1 * modifier.getLevel()){
            boolean wasSuccessful = true;
            if (target instanceof Player) {
                wasSuccessful = target.hurt(IafDamageRegistry.causeGorgonDamage(target), Integer.MAX_VALUE);
            } else {
                if (!target.level.isClientSide)
                    target.remove(Entity.RemovalReason.KILLED);
            }
            if (wasSuccessful) {
                target.playSound(IafSoundRegistry.TURN_STONE, 1, 1);
                EntityStoneStatue statue = EntityStoneStatue.buildStatueEntity(target);
                statue.absMoveTo(target.getX(), target.getY(), target.getZ(), target.getYRot(), target.getXRot());
                statue.yBodyRot = target.getYRot();
                if (!target.level.isClientSide) {
                    target.level.addFreshEntity(statue);
                }
            }
        }
        return false;
    }
}
