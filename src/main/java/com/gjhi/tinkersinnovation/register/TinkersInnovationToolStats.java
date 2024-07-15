package com.gjhi.tinkersinnovation.register;

import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStatId;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationToolStats {
    public static final FloatToolStat SHIELD_AMOUNT = ToolStats.register(new FloatToolStat(name("shield_amount"), -3450403, 1.0F, 1.0F, 2.14748365E9F));
    public static final FloatToolStat SHIELD_BREAK = ToolStats.register(new FloatToolStat(name("shield_break"), -3450403, 1.0F, 1.0F, 2.14748365E9F));

    public static void init() {
    }
    private static ToolStatId name(String name) {
        return new ToolStatId(MOD_ID, name);
    }
}
