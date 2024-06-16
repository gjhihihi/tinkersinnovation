package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class BIGSHOTModifier extends Modifier implements ConditionalStatModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.CONDITIONAL_STAT);
    }
    @Override
    public float modifyStat(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        float current = (float)overslime.getShield(tool);
        if (stat == ToolStats.DRAW_SPEED) {
            return (float)(baseValue + Math.sqrt(current) * modifier.getLevel() * 0.01 * tool.getMultiplier(ToolStats.DRAW_SPEED));
        } else if (stat == ToolStats.ACCURACY) {
            return (float)(baseValue + Math.sqrt(current) * modifier.getLevel() * 0.01 * tool.getMultiplier(ToolStats.DRAW_SPEED));
        } else if (stat == ToolStats.VELOCITY) {
            return (float)(baseValue + Math.sqrt(current) * modifier.getLevel() * 0.01 * tool.getMultiplier(ToolStats.DRAW_SPEED));
        }
        return baseValue;
    }
}