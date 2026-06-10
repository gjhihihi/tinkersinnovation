package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationEffects;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public class AdversityResistanceModifier extends Modifier implements MeleeDamageModifierHook, ProjectileLaunchModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.MONSTER_MELEE_DAMAGE, ModifierHooks.PROJECTILE_LAUNCH, ModifierHooks.TOOLTIP);
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, boolean primary) {
        float factor = 1;
        if (arrow != null){
            for (MobEffectInstance effect : shooter.getActiveEffects()){
                if (effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)){
                    factor += 0.1f * (effect.getAmplifier() + 1) * modifier.getEffectiveLevel();
                }
            }
            arrow.setBaseDamage(arrow.getBaseDamage() * Math.max(factor, 0));
        }
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        float factor = 1;
        for (MobEffectInstance effect : attacker.getActiveEffects()) {
            if (effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)) {
                factor += 0.1f * (effect.getAmplifier() + 1) * modifier.getEffectiveLevel();
            }
        }
        return damage * Math.max(factor, 0);
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player != null && tooltipKey.equals(TooltipKey.SHIFT)) {
            double factor = 0;
            for (MobEffectInstance effect : player.getActiveEffects()) {
                if (effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)) {
                    factor += 0.1 * (effect.getAmplifier() + 1) * modifier.getEffectiveLevel();
                }
            }
            TooltipModifierHook.addPercentBoost(this, TooltipModifierHook.statName(this, ToolStats.ATTACK_DAMAGE), factor, tooltip);
        }
    }
}
