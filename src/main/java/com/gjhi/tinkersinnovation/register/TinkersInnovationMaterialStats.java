package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.library.stats.BombCoreStats;
import com.gjhi.tinkersinnovation.library.stats.BombShellStats;
import com.gjhi.tinkersinnovation.library.stats.ShieldMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.ARMOR;
import static slimeknights.tconstruct.library.materials.MaterialRegistry.RANGED;

public class TinkersInnovationMaterialStats {
    public static final MaterialStatsId SHIELD = new MaterialStatsId(TinkersInnovation.getResource("shield"));
    public static final MaterialStatsId BOMB = new MaterialStatsId(TinkersInnovation.getResource("bomb"));

    public static void setup() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(ShieldMaterialStats.TYPE, ARMOR);
        registry.registerStatType(BombCoreStats.TYPE, RANGED);
        registry.registerStatType(BombShellStats.TYPE, RANGED);
    }
}
