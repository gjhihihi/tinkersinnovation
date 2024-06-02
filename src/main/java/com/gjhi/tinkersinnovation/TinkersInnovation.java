package com.gjhi.tinkersinnovation;

import com.gjhi.tinkersinnovation.creativetabs.*;
import com.gjhi.tinkersinnovation.register.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersInnovation.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersInnovation {
    public static final String MOD_ID = "tinkersinnovation";
    public TinkersInnovation() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TinkersInnovationConfig.config);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        TinkersInnovationModifiers.MODIFIERS.register(bus);
        TinkersInnovationBlocks.BLOCKS.register(bus);
        TinkersInnovationItems.ITEMS.register(bus);
        TinkersInnovationFluids.FLUIDS.register(bus);
        TinkersInnovationOreGen.CONFIGURED_FEATURES.register(bus);
        TinkersInnovationOreGen.PLACED_FEATURES.register(bus);
        TinkersInnovationOreGen.BLOCKS.register(bus);
        TinkersInnovationTags.init();
    }
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        if (event.includeClient()) {
        }
        if (event.includeServer()) {
        } //两个大括号内的内容暂时不用填写
    }
    public static final CreativeModeTab itemGroup = new TinkersInnovationItemGroup();
    public static final CreativeModeTab toolGroup = new TinkersInnovationToolGroup();
}
