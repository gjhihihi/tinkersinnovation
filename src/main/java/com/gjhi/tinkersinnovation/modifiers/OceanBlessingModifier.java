package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;

public class OceanBlessingModifier extends Modifier implements ProjectileHitModifierHook, MeleeHitModifierHook, ToolDamageModifierHook {
    /*@Override
    public void onBreakSpeed(@NotNull IToolStackView tool, int level, PlayerEvent.BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        // the speed is reduced when not on the ground, cancel out
        Player player = event.getPlayer();
        if (player != null && player.isInWaterOrBubble()){
            if (!player.isOnGround()) {
                event.setNewSpeed(event.getNewSpeed() * 5);
            }
            if (player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
                player.removeEffect(MobEffects.DIG_SLOWDOWN);
            }
            event.setNewSpeed(event.getNewSpeed() * 5 + level * 3);
        }
    }*/
    @Override
    public int onDamageTool(@NotNull IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (holder != null && holder.isInWater()) {
            holder.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,200 * modifier.getLevel()));
        }
        return amount;
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        int level = modifier.getLevel();
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null && player.isInWaterRainOrBubble()) {
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200 * level ,level-1));
        }
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        int level = modifier.getLevel();
        if (attacker != null && target != null && attacker.isInWaterRainOrBubble()) {
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200 * level ,level-1));
        }
        return false;
    }
    @Override
    public int getPriority() {
        return 75;
    }

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.PROJECTILE_HIT,TinkerHooks.TOOL_DAMAGE,TinkerHooks.MELEE_HIT);
    }

}
