package com.gjhi.tinkersinnovation.library.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.Iterator;

public class ElasticModifier extends Modifier implements ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    private double getBonus(ModifierEntry modifier){
        return Math.min(0.1 * modifier.getLevel(), 0.3);
    }
    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.ATTACK_SPEED.percent(builder, getBonus(modifier));
        ToolStats.DRAW_SPEED.percent(builder, getBonus(modifier));
        ToolStats.VELOCITY.percent(builder, getBonus(modifier));
        ToolStats.ATTACK_DAMAGE.percent(builder, -getBonus(modifier));
        ToolStats.MINING_SPEED.percent(builder, -getBonus(modifier));
        ToolStats.ACCURACY.percent(builder, -getBonus(modifier));
    }
}
