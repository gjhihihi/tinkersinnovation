package com.gjhi.tinkersinnovation.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.tools.TinkerToolParts;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationTabs {
    public static final SynchronizedDeferredRegister<CreativeModeTab> CREATIVE_TABS = SynchronizedDeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final RegistryObject<CreativeModeTab> itemGroup = CREATIVE_TABS.register("items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.TinkersInnovationItemGroup"))
            .icon(() -> new ItemStack(TinkersInnovationItems.polychrome_alloy_ingot.get()))
            .displayItems((displayParameters, output) -> {
                for (RegistryObject<Item> item : TinkersInnovationItems.ITEMS.getEntries()){
                    output.accept(item.get());
                }
            })
            .withTabsBefore(new ResourceLocation[]{TinkerToolParts.tabToolParts.getId()})
            .build()
    );
    public static final RegistryObject<CreativeModeTab> toolGroup = CREATIVE_TABS.register("tools", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.TinkersInnovationToolGroup"))
            .icon(() -> TinkersInnovationItems.claw.get().getRenderTool())
            .displayItems((displayParameters, output) -> {
                for (RegistryObject<Item> item : TinkersInnovationItems.TOOLS.getEntries()){
                    output.accept(item.get());
                }
            })
            .withTabsBefore(new ResourceLocation[]{itemGroup.getId()})
            .build()
    );
}
