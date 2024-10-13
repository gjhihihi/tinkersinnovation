package com.gjhi.tinkersinnovation.register;

import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStatId;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationToolStats {
    public static final FloatToolStat SHIELD_AMOUNT = ToolStats.register(new FloatToolStat(name("shield_amount"), -3450403, 1.0F, 1.0F, 2.14748365E9F));
    public static final FloatToolStat BOMB_RADIUS = ToolStats.register(new FloatToolStat(name("bomb_radius"), -3450403, 1.0F, 0.0F, 2.14748365E9F));
    public static final FloatToolStat PIECE_COUNT = ToolStats.register(new FloatToolStat(name("piece_count"), -3450403, 1.0F, 1.0F, 2.14748365E9F));
    public static final FloatToolStat PIECE_DAMAGE= ToolStats.register(new FloatToolStat(name("piece_damage"), -3450403, 1.0F, 0.0F, 2.14748365E9F));
    public static final FloatToolStat STRESS = ToolStats.register(new FloatToolStat(name("stress"), -3450403, 20.0F, 1.0F, 2.14748365E9F));

    public static void init() {
    }
    private static ToolStatId name(String name) {
        return new ToolStatId(MOD_ID, name);
    }
}
