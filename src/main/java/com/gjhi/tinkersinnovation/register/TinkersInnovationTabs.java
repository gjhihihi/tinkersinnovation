package com.gjhi.tinkersinnovation.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.world.TinkerWorld;

import java.util.function.Consumer;

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
            .withTabsBefore(new ResourceLocation[]{TinkerWorld.tabWorld.getId()})
            .build()
    );
    public static final RegistryObject<CreativeModeTab> toolGroup = CREATIVE_TABS.register("tools", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.TinkersInnovationToolGroup"))
            .icon(() -> TinkersInnovationItems.claw.get().getRenderTool())
            .displayItems((displayParameters, output) -> {
                for (RegistryObject<Item> item : TinkersInnovationItems.TOOLS.getEntries()){
                    if (item.get() instanceof IModifiable iModifiable){
                        acceptTool(output::accept, iModifiable);
                    } else if (item.get() instanceof IMaterialItem iMaterialItem) {
                        acceptPart(output::accept, iMaterialItem);
                    }
                    output.accept(item.get());
                }
            })
            .withTabsBefore(new ResourceLocation[]{itemGroup.getId()})
            .build()
    );
    private static void acceptTool(Consumer<ItemStack> output, IModifiable tool) {
        ToolBuildHandler.addVariants(output, tool, "");
    }
    private static void acceptPart(Consumer<ItemStack> output, IMaterialItem item) {
        item.addVariants(output, "");
    }
}
