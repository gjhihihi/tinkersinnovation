package com.gjhi.tinkersinnovation;

import com.gjhi.tinkersinnovation.register.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersInnovation.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersInnovation {
    public static final String MOD_ID = "tinkersinnovation";
    private static final Logger LOGGER = LogManager.getLogger();
    public static Logger getLogger() {
        return LOGGER;
    }
    public static final Logger tinkers_logger = LogManager.getLogger("tinkersinnovation");

    public TinkersInnovation() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TinkersInnovationConfig.config);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
        TinkersInnovationModifiers.MODIFIERS.register(bus);
        TinkersInnovationBlocks.BLOCKS.register(bus);
        TinkersInnovationItems.ITEMS.register(bus);
        TinkersInnovationFluids.FLUIDS.register(bus);
        TinkersInnovationEntityTypes.ENTITY_TYPES.register(bus);
        TinkersInnovationWorldGen.CONFIGURED_FEATURES.register(bus);
        TinkersInnovationWorldGen.PLACED_FEATURES.register(bus);
        TinkersInnovationWorldGen.BLOCKS.register(bus);
        TinkersInnovationEffects.MOB_EFFECTS.register(bus);
        TinkersInnovationPotions.POTIONS.register(bus);
        TinkersInnovationTags.init();
        TinkersInnovationToolStats.init();
        TinkersInnovationSlots.init();
    }

    private void setupClient(FMLClientSetupEvent event) {
    }

    private void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(TinkersInnovationMaterialStats::setup);
        TinkersInnovationPotions.recipesInit();
        TinkersInnovationCompat.L2Complements.init();
        TinkersInnovationCompat.TinkersIngenuity.init();
        TinkersInnovationCompat.L2Hostility.init();
        TinkersInnovationCompat.IceAndFire.init();
        TinkersInnovationCompat.AlexsMobs.init();
    }
    public static ResourceLocation getResource(String id) {
        return new ResourceLocation(MOD_ID, id);
    }
    public static String makeDescriptionId(String type, String name) {
        return type + "." + MOD_ID + "." + name;
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
    public static final CreativeModeTab itemGroup = new CreativeModeTab("TinkersInnovationItemGroup") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(TinkersInnovationItems.polychrome_alloy_ingot.get());
        }
    };
    public static final CreativeModeTab toolGroup = new CreativeModeTab("TinkersInnovationToolGroup") {
        @Override
        public ItemStack makeIcon() {
            return TinkersInnovationItems.claw.get().getRenderTool();
        }
    };
}
