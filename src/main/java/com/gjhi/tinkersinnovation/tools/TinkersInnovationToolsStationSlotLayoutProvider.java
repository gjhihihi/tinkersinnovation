package com.gjhi.tinkersinnovation.tools;

import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class TinkersInnovationToolsStationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {
    public TinkersInnovationToolsStationSlotLayoutProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addLayouts() {
        defineModifiable(TinkersInnovationItems.pick_dart)
                .sortIndex(2)
                .addInputItem(TinkerToolParts.pickHead, 39, 35)
                .addInputItem(TinkerToolParts.smallBlade, 39, 53)
                .addInputItem(TinkerToolParts.smallBlade, 21, 35)
                .addInputItem(TinkersInnovationItems.handguard.get(), 21, 53)
                .build();
        defineModifiable(TinkersInnovationItems.heavy_shield)
                .sortIndex(3)
                .addInputItem(TinkerToolParts.largePlate, 25, 20)
                .addInputItem(TinkerToolParts.largePlate, 25, 40)
                .addInputItem(TinkersInnovationItems.shield_plate.get(), 21, 30)
                .addInputItem(TinkerToolParts.toolHandle, 15, 30)
                .build();
    }

    @Override
    public String getName() {
        return "Tinkers Innovation Tool Slot Layout";
    }
}
