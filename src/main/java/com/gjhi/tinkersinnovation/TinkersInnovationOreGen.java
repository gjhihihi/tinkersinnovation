package com.gjhi.tinkersinnovation;

import com.gjhi.tinkersinnovation.register.TinkersInnovationBlocks;
import com.gjhi.tinkersinnovation.register.TinkersInnovationTags;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.ConfiguredFeatureDeferredRegister;
import slimeknights.tconstruct.common.registration.PlacedFeatureDeferredRegister;

import java.util.List;
import java.util.function.Supplier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;
import static slimeknights.tconstruct.world.TinkerWorld.TAB_WORLD;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class TinkersInnovationOreGen {

    //public static Supplier<List<OreConfiguration.TargetBlockState>> titReplace = () -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, TinkersInnovationBlocks.titanium_ore.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, TinkersInnovationBlocks.deepslate_titanium_ore.get().defaultBlockState()));
    public static Supplier<List<OreConfiguration.TargetBlockState>> voiReplace = () -> List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, TinkersInnovationBlocks.void_crystal_ore.get().defaultBlockState()));

    public static final ConfiguredFeatureDeferredRegister CONFIGURED_FEATURES = new ConfiguredFeatureDeferredRegister(MOD_ID);
    public static final PlacedFeatureDeferredRegister PLACED_FEATURES = new PlacedFeatureDeferredRegister(MOD_ID);

    public static RegistryObject<ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>>> VOID_CRYSTAL_ORE = CONFIGURED_FEATURES.registerSupplier("void_crystal_ore", () -> Feature.ORE, () -> new OreConfiguration(voiReplace.get(), TinkersInnovationConfig.COMMON.voidcrystalOre.getSize()));
    public static RegistryObject<PlacedFeature> placedVoidcrystalOre = PLACED_FEATURES.register("void_crystal_ore", VOID_CRYSTAL_ORE, CountPlacement.of(TinkersInnovationConfig.COMMON.voidcrystalOre.getCount()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(TinkersInnovationConfig.COMMON.voidcrystalOre.getMinY()), VerticalAnchor.absolute(TinkersInnovationConfig.COMMON.voidcrystalOre.getMaxY())));
    @SubscribeEvent
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        Biome.BiomeCategory category = event.getCategory();
        if (isOverworldBiome(category)) {
            if (TinkersInnovationConfig.COMMON.voidcrystalOre.isEnabled()) {
                placedVoidcrystalOre.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, holder));
            }
            /*
            if (TinkersInnovationConfig.COMMON.aventurineOre.isEnabled()) {
                placedAventurineOre.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, holder));
            }
            if (TinkersInnovationConfig.COMMON.redinsOre.isEnabled()) {
                placedRedinsOre.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, holder));
            }
            if (TinkersInnovationConfig.COMMON.vibratingcrystalOre.isEnabled()) {
                placedVibratingOre.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, holder));
            }
            if (TinkersInnovationConfig.COMMON.icelandsparGeodes.get() && category == Biome.BiomeCategory.ICY) {
                placedIcelandSparGeode.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, holder));
            }
            if (TinkersInnovationConfig.COMMON.topazGeodes.get() && category == Biome.BiomeCategory.OCEAN) {
                placedTopazGeode.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, holder));
            }
            if (TinkersInnovationConfig.COMMON.lizaniteGeodes.get()) {
                placedLizaniteGeode.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, holder));
            }
            if (TinkersInnovationConfig.COMMON.cordieriteGeodes.get()) {
                placedCordieriteGeode.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, holder));
            }
            if (TinkersInnovationConfig.COMMON.prehniteGeodes.get()) {
                placedprehniteGeode.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, holder));
            }*/
        }
        if (isNetherBiome(category)) {
            /*if (TinkersInnovationConfig.COMMON.inertwitheriumOre.isEnabled()) {
                placedInertwitheriumOre.getHolder().ifPresent(holder -> generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, holder));
            }*/
        }
        if (isEndBiome(category)) {
            //
        }
    }

    public static boolean isOverworldBiome(Biome.BiomeCategory category) {

        return category != Biome.BiomeCategory.NONE && category != Biome.BiomeCategory.THEEND && category != Biome.BiomeCategory.NETHER;
    }

    public static boolean isNetherBiome(Biome.BiomeCategory category) {

        return category == Biome.BiomeCategory.NETHER;
    }
    public static boolean isEndBiome(Biome.BiomeCategory category) {

        return category == Biome.BiomeCategory.THEEND;
    }
    protected static final BlockDeferredRegisterExtension BLOCKS = new BlockDeferredRegisterExtension(MOD_ID);
    private static final Item.Properties WORLD_PROPS = new Item.Properties().tab(TAB_WORLD);
    /*public static final GeodeItemObject icelandGeode = BLOCKS.registerGeode("iceland_spar_crystal", MaterialColor.COLOR_LIGHT_BLUE, Sounds.EARTH_CRYSTAL, Sounds.EARTH_CRYSTAL_CHIME.getSound(), Sounds.EARTH_CRYSTAL_CLUSTER,  3, WORLD_PROPS);
    public static final RegistryObject<ConfiguredFeature<GeodeConfiguration,Feature<GeodeConfiguration>>> configuredIcelandSparGeode = CONFIGURED_FEATURES.registerGeode(
            "iceland_spar_geode", icelandGeode, BlockStateProvider.simple(Blocks.BLUE_ICE), BlockStateProvider.simple(Blocks.SNOW_BLOCK),
            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 5.2D), new GeodeCrackSettings(0.95D, 2.0D, 2), UniformInt.of(6, 9), UniformInt.of(3, 4), UniformInt.of(1, 2), 16, 1);
    public static final RegistryObject<PlacedFeature> placedIcelandSparGeode = PLACED_FEATURES.registerGeode("iceland_spar_geode", configuredIcelandSparGeode, RarityFilter.onAverageOnceEvery(128), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.aboveBottom(54)));
    public static final GeodeItemObject topazGeode = BLOCKS.registerGeode("topaz_crystal", MaterialColor.COLOR_BLUE, Sounds.EARTH_CRYSTAL, Sounds.EARTH_CRYSTAL_CHIME.getSound(), Sounds.EARTH_CRYSTAL_CLUSTER,  3, WORLD_PROPS);
    public static final RegistryObject<ConfiguredFeature<GeodeConfiguration,Feature<GeodeConfiguration>>> configuredtopazGeode = CONFIGURED_FEATURES.registerGeode(
            "topaz_geode", topazGeode, BlockStateProvider.simple(Blocks.LIGHT_BLUE_TERRACOTTA), BlockStateProvider.simple(Blocks.GRANITE),
            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 5.2D), new GeodeCrackSettings(0.95D, 2.0D, 2), UniformInt.of(6, 9), UniformInt.of(3, 4), UniformInt.of(1, 2), 16, 1);
    public static final RegistryObject<PlacedFeature> placedTopazGeode = PLACED_FEATURES.registerGeode("topaz_geode", configuredtopazGeode, RarityFilter.onAverageOnceEvery(128), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(54)));
    public static final GeodeItemObject lizaniteGeode = BLOCKS.registerGeode("lizanite_crystal", MaterialColor.COLOR_MAGENTA, Sounds.EARTH_CRYSTAL, Sounds.EARTH_CRYSTAL_CHIME.getSound(), Sounds.EARTH_CRYSTAL_CLUSTER,  3, WORLD_PROPS);
    public static final RegistryObject<ConfiguredFeature<GeodeConfiguration,Feature<GeodeConfiguration>>> configuredlizaniteGeode = CONFIGURED_FEATURES.registerGeode(
            "lizanite_geode", lizaniteGeode, BlockStateProvider.simple(Blocks.TUFF), BlockStateProvider.simple(Blocks.GRANITE),
            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 5.2D), new GeodeCrackSettings(0.95D, 2.0D, 2), UniformInt.of(6, 9), UniformInt.of(3, 4), UniformInt.of(1, 2), 16, 1);
    public static final RegistryObject<PlacedFeature> placedLizaniteGeode = PLACED_FEATURES.registerGeode("lizanite_geode", configuredlizaniteGeode, RarityFilter.onAverageOnceEvery(128), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(20), VerticalAnchor.aboveBottom(54)));
    public static final GeodeItemObject cordieriteGeode = BLOCKS.registerGeode("cordierite_crystal", MaterialColor.COLOR_CYAN, Sounds.EARTH_CRYSTAL, Sounds.EARTH_CRYSTAL_CHIME.getSound(), Sounds.EARTH_CRYSTAL_CLUSTER,  3, WORLD_PROPS);
    public static final RegistryObject<ConfiguredFeature<GeodeConfiguration,Feature<GeodeConfiguration>>> configuredcordieriteGeode = CONFIGURED_FEATURES.registerGeode(
            "cordierite_geode", cordieriteGeode, BlockStateProvider.simple(Blocks.POLISHED_BASALT), BlockStateProvider.simple(Blocks.ANDESITE),
            new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 5.2D), new GeodeCrackSettings(0.95D, 2.0D, 2), UniformInt.of(6, 9), UniformInt.of(3, 4), UniformInt.of(1, 2), 16, 1);
    public static final RegistryObject<PlacedFeature> placedCordieriteGeode = PLACED_FEATURES.registerGeode("cordierite_geode", configuredcordieriteGeode, RarityFilter.onAverageOnceEvery(128), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(20), VerticalAnchor.aboveBottom(54)));
    public static final GeodeItemObject prehniteGeode = BLOCKS.registerGeode("prehnite_crystal", MaterialColor.COLOR_YELLOW, Sounds.EARTH_CRYSTAL, Sounds.EARTH_CRYSTAL_CHIME.getSound(), Sounds.EARTH_CRYSTAL_CLUSTER,  3, WORLD_PROPS);
    public static final RegistryObject<ConfiguredFeature<GeodeConfiguration,Feature<GeodeConfiguration>>> configuredprehniteGeode = CONFIGURED_FEATURES.registerGeode(
            "prehnite_geode", prehniteGeode, BlockStateProvider.simple(Blocks.GRANITE), BlockStateProvider.simple(Blocks.DRIPSTONE_BLOCK),
            new GeodeLayerSettings(1.5D, 2.0D, 3.0D, 4.5D), new GeodeCrackSettings(0.55D, 0.5D, 2), UniformInt.of(3, 4), ConstantInt.of(2), ConstantInt.of(1), 8, 3);
    public static final RegistryObject<PlacedFeature> placedprehniteGeode = PLACED_FEATURES.registerGeode("prehnite_geode", configuredprehniteGeode, RarityFilter.onAverageOnceEvery(128), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(20), VerticalAnchor.aboveBottom(54)));
*/
}