package com.gjhi.tinkersinnovation.tools;

import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

public class TinkersInnovationToolsDefinition {
    public static final ToolDefinition PickDart = ToolDefinition.builder(TinkersInnovationItems.pick_dart).meleeHarvest().build();
    public static final ToolDefinition HeavyShield = ToolDefinition.builder(TinkersInnovationItems.heavy_shield).meleeHarvest().build();
}