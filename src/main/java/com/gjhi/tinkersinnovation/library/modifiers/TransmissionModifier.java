package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationSlots;
import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

public class TransmissionModifier extends Modifier implements ToolStatsModifierHook, VolatileDataModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS, ModifierHooks.VOLATILE_DATA);
    }

    public void addVolatileData(IToolContext context, ModifierEntry modifier, ModDataNBT volatileData) {
        volatileData.addSlots(TinkersInnovationSlots.MECHANISM, modifier.getLevel());
    }
    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        TinkersInnovationToolStats.STRESS.add(builder, 10 * modifier.getLevel());
    }
}
