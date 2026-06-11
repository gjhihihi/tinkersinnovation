package com.gjhi.tinkersinnovation.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.fluids.TinkerFluids;
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
                output.accept(TinkersInnovationItems.light_shield_plate_cast);
                output.accept(TinkersInnovationItems.heavy_shield_plate_cast);
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
    public static void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == TinkerFluids.tabFluids.getKey()) {
            event.accept(TinkersInnovationFluids.polychrome_alloy);
            event.accept(TinkersInnovationFluids.experience);
            event.accept(TinkersInnovationFluids.void_crystal);
            event.accept(TinkersInnovationFluids.enchantment_essence);
            event.accept(TinkersInnovationFluids.decline);
            event.accept(TinkersInnovationFluids.slimton);
            event.accept(TinkersInnovationFluids.fools_gold);
            event.accept(TinkersInnovationFluids.ruby);
            event.accept(TinkersInnovationFluids.sapphire);
            event.accept(TinkersInnovationFluids.blood);
            event.accept(TinkersInnovationFluids.blazing_soul);
            event.accept(TinkersInnovationFluids.mudslime);
            event.accept(TinkersInnovationFluids.lightning);
            if (TinkersInnovationCompat.Create.isLoaded()) {
                event.accept(TinkersInnovationFluids.andesite_alloy);
            }
            if (TinkersInnovationCompat.L2Complements.isLoaded()){
                event.accept(TinkersInnovationFluids.totemic_gold);
                event.accept(TinkersInnovationFluids.poseidite);
                event.accept(TinkersInnovationFluids.shulkerate);
                event.accept(TinkersInnovationFluids.sculkium);
                event.accept(TinkersInnovationFluids.eternium);
                if (TinkersInnovationCompat.L2Hostility.isLoaded()){
                    event.accept(TinkersInnovationFluids.chaos);
                    event.accept(TinkersInnovationFluids.miracle);
                    event.accept(TinkersInnovationFluids.hostility);
                    event.accept(TinkersInnovationFluids.hostilium);
                }
            }
            if (TinkersInnovationCompat.AlexsMobs.isLoaded()){
                event.accept(TinkersInnovationFluids.mimicream);
                event.accept(TinkersInnovationFluids.farseeing_alloy);
                event.accept(TinkersInnovationFluids.hemolymph);
                event.accept(TinkersInnovationFluids.capsid);
                event.accept(TinkersInnovationFluids.sunsoul_alloy);
                event.accept(TinkersInnovationFluids.straddlite);
                event.accept(TinkersInnovationFluids.straddlite_alloy);
            }
        }
    }
    private static void acceptTool(Consumer<ItemStack> output, IModifiable tool) {
        ToolBuildHandler.addVariants(output, tool, "");
    }
    private static void acceptPart(Consumer<ItemStack> output, IMaterialItem item) {
        item.addVariants(output, "");
    }
}
