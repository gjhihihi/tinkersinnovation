package com.gjhi.tinkersinnovation.library.modifiers;

import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class WindPowerModifier extends Modifier implements ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }
    @Override
    public void addToolStats(@NotNull IToolContext context, @NotNull ModifierEntry modifier, @NotNull ModifierStatsBuilder builder) {
        ToolStats.VELOCITY.multiply(builder, 1 + 0.12 * modifier.getLevel());
        ToolStats.DRAW_SPEED.multiply(builder, 1 + 0.12 * modifier.getLevel());
        ToolStats.MINING_SPEED.multiply(builder, 1 + 0.12 * modifier.getLevel());
        ToolStats.ATTACK_SPEED.multiply(builder, 1 + 0.12 * modifier.getLevel());
    }
}
