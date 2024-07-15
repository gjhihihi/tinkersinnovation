package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.stats.ShieldMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

public class TinkersInnovationMaterialStats {
    public static final MaterialStatsId SHIELD = new MaterialStatsId(TinkersInnovation.getResource("shield"));

    public static void setup() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(ShieldMaterialStats.TYPE, SHIELD);
    }
}
