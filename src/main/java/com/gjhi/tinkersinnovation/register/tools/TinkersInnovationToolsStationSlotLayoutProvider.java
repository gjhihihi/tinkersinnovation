package com.gjhi.tinkersinnovation.register.tools;

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
        defineModifiable(TinkersInnovationItems.heavy_shield)
                .sortIndex(3)
                .addInputItem(TinkerToolParts.largePlate, 25, 20)
                .addInputItem(TinkerToolParts.largePlate, 25, 40)
                .addInputItem(TinkersInnovationItems.shield_plate.get(), 21, 30)
                .addInputItem(TinkerToolParts.toughHandle, 15, 30)
                .build();
        defineModifiable(TinkersInnovationItems.claw)
                .sortIndex(15)
                .addInputItem(TinkerToolParts.smallBlade, 25, 20)
                .addInputItem(TinkerToolParts.smallBlade, 35, 20)
                .addInputItem(TinkerToolParts.smallBlade, 45, 20)
                .addInputItem(TinkersInnovationItems.handguard.get(), 35, 30)
                .build();
    }

    @Override
    public String getName() {
        return "Tinkers Innovation Tool Slot Layout";
    }
}
