package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovationConfig;
import com.gjhi.tinkersinnovation.register.TinkersInnovationBlocks;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
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

public class TinkersInnovationWorldGen {

    public static final BlockDeferredRegisterExtension BLOCKS = new BlockDeferredRegisterExtension(MOD_ID);
    private static final Item.Properties WORLD_PROPS = new Item.Properties().tab(TAB_WORLD);
    public static final RuleTest END_ORE_REPLACEABLES = new BlockMatchTest(Blocks.END_STONE);
    public static final RuleTest BEDROCK_ORE_REPLACEABLES = new BlockMatchTest(Blocks.BEDROCK);
    //public static Supplier<List<OreConfiguration.TargetBlockState>> titReplace = () -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, TinkersInnovationBlocks.titanium_ore.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, TinkersInnovationBlocks.deepslate_titanium_ore.get().defaultBlockState()));
    public static Supplier<List<OreConfiguration.TargetBlockState>> voiReplace = () -> List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, TinkersInnovationBlocks.void_crystal_ore.get().defaultBlockState()));

    public static final ConfiguredFeatureDeferredRegister CONFIGURED_FEATURES = new ConfiguredFeatureDeferredRegister(MOD_ID);
    public static final PlacedFeatureDeferredRegister PLACED_FEATURES = new PlacedFeatureDeferredRegister(MOD_ID);

    public static RegistryObject<ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>>> VOID_CRYSTAL_ORE = CONFIGURED_FEATURES.registerSupplier("void_crystal_ore", () -> Feature.ORE, () -> new OreConfiguration(voiReplace.get(), TinkersInnovationConfig.COMMON.voidcrystalOre.getSize()));
    public static RegistryObject<PlacedFeature> placedVoidcrystalOre = PLACED_FEATURES.register("void_crystal_ore", VOID_CRYSTAL_ORE, CountPlacement.of(TinkersInnovationConfig.COMMON.voidcrystalOre.getCount()), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(TinkersInnovationConfig.COMMON.voidcrystalOre.getMinY()), VerticalAnchor.absolute(TinkersInnovationConfig.COMMON.voidcrystalOre.getMaxY())));


}