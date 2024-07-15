package com.gjhi.tinkersinnovation;

import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slimeknights.tconstruct.library.client.model.TinkerItemProperties;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        value = {Dist.CLIENT},
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ToolClientEvents {
    @SubscribeEvent
    static void clientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TinkerItemProperties.registerToolProperties(TinkersInnovationItems.claw.get().asItem());
            TinkerItemProperties.registerToolProperties(TinkersInnovationItems.heavy_shield.get().asItem());
            TinkerItemProperties.registerToolProperties(TinkersInnovationItems.round_shield.get().asItem());
        });
    }
}
