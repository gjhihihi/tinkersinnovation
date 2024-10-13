package com.gjhi.tinkersinnovation.library.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class AppearanceModifier extends NoLevelsModifier implements ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    @Override
    public int getPriority() {
        return 20;
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        if (builder.getStat(ToolStats.DRAW_SPEED) < 0.1f){
            ToolStats.DRAW_SPEED.update(builder, 0.1f);
        }
        if (builder.getStat(ToolStats.VELOCITY) < 0.1f){
            ToolStats.VELOCITY.update(builder, 0.1f);
        }
        if (builder.getStat(ToolStats.ACCURACY) < 0.1f){
            ToolStats.ACCURACY.update(builder, 0.1f);
        }
    }
}
