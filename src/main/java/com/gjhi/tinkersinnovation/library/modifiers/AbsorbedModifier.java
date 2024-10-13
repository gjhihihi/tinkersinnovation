package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationEffects;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;
import java.util.List;

public class AbsorbedModifier extends Modifier implements ProjectileLaunchModifierHook, ConditionalStatModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.CONDITIONAL_STAT, ModifierHooks.PROJECTILE_LAUNCH, ModifierHooks.TOOLTIP);
    }

    private static float getBonus(LivingEntity living) {
        return (TinkersInnovationEffects.absorbedEffect.get().getLevel(living) + 1) / 16f;
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
        TinkersInnovationUtils.updateEffect(shooter, TinkersInnovationEffects.absorbedEffect.get(), 1, 16, 100);
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.ACCURACY) {
            baseValue *= 1 + getBonus(living) * modifier.getLevel();
        }
        return baseValue;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player != null && tooltipKey.equals(TooltipKey.SHIFT)) {
            TooltipModifierHook.addStatBoost(tool, this, ToolStats.ACCURACY, TinkerTags.Items.RANGED, getBonus(player), tooltip);
        }
    }
}
