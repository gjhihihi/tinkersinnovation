package com.gjhi.tinkersinnovation;

import com.gjhi.tinkersinnovation.library.fluid_effects.SpawnLightningBoltFluidEffect;
import com.gjhi.tinkersinnovation.library.modules.FieryBombModule;
import com.gjhi.tinkersinnovation.library.modules.FreezingBombModule;
import com.gjhi.tinkersinnovation.register.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffect;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersInnovation.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersInnovation {
    public static final String MOD_ID = "tinkersinnovation";
    private static final Logger LOGGER = LogManager.getLogger();
    public static TinkersInnovationConfig config;

    public static Logger getLogger() {
        return LOGGER;
    }
    public static final Logger tinkers_logger = LogManager.getLogger("tinkersinnovation");

    public TinkersInnovation() {
        config = new TinkersInnovationConfig(ModLoadingContext.get());
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
        bus.addListener(this::registerSerializers);
        TinkersInnovationModifiers.MODIFIERS.register(bus);
        TinkersInnovationBlocks.BLOCKS.register(bus);
        TinkersInnovationItems.ITEMS.register(bus);
        TinkersInnovationItems.TOOLS.register(bus);
        TinkersInnovationItems.NOTDISPLAYS.register(bus);
        TinkersInnovationTabs.CREATIVE_TABS.register(bus);
        TinkersInnovationFluids.FLUIDS.register(bus);
        TinkersInnovationEntityTypes.ENTITY_TYPES.register(bus);
        TinkersInnovationEffects.MOB_EFFECTS.register(bus);
        TinkersInnovationPotions.POTIONS.register(bus);
        TinkersInnovationRecipes.RECIPE_SERIALIZERS.register(bus);
        TinkersInnovationTags.init();
        TinkersInnovationToolStats.init();
    }

    private void setupClient(FMLClientSetupEvent event) {
        TinkersInnovationSlots.init();
    }

    private void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(TinkersInnovationMaterialStats::setup);
        TinkersInnovationPotions.recipesInit();
        TinkersInnovationCompat.L2Complements.init();
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
    void registerSerializers(RegisterEvent event){
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER){
            FluidEffect.registerGeneral(TinkersInnovation.getResource("spawn_lightning_bolt"), SpawnLightningBoltFluidEffect.LOADER);
            ModifierModule.LOADER.register(TinkersInnovation.getResource("fiery_bomb"), FieryBombModule.LOADER);
            ModifierModule.LOADER.register(TinkersInnovation.getResource("freezing_bomb"), FreezingBombModule.LOADER);
        }
    }
}
