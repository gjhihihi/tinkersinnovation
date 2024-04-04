package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class OceanBlessingModifier extends Modifier implements ConditionalStatModifierHook {
    @Override
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
    }
    @Override
    public void afterBlockBreak(@NotNull IToolStackView tool, int level, ToolHarvestContext context){
        Player player = context.getPlayer();
        if (player != null && player.isInWaterOrBubble()) {
            MobEffectInstance effect =new MobEffectInstance(MobEffects.WATER_BREATHING,200 * level);
            player.addEffect(effect);
        }
    }
    @Override
    public float getEntityDamage(@NotNull IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage){
        Player player = context.getPlayerAttacker();
        if (player != null && player.isInWaterOrBubble()) {
            return damage + (level * 3);
        }
        return damage;
    }
    @Override
    public float beforeEntityHit(@NotNull IToolStackView tool, int level, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        Player player = context.getPlayerAttacker();
        if (player != null && player.isInWaterOrBubble()) {
            MobEffectInstance effect =new MobEffectInstance(MobEffects.WATER_BREATHING,200 * level);
            player.addEffect(effect);
        }
        return knockback;
    }
    @Override
    public int afterEntityHit(@NotNull IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        if (player != null && target != null && player.isInWaterOrBubble()) {
            MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200 * level ,level-1);
            target.addEffect(effect);
        }
        return level;
    }
    @Override
    public int getPriority() {
        return 75;
    }

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT);
    }

    @Override
    public float modifyStat(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull LivingEntity living, @NotNull FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.ACCURACY && living.isInWaterOrBubble()) {
            return baseValue + 0.5f;
        }
        return baseValue;
    }
}
