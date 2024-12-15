package com.gjhi.tinkersinnovation.world.features.ores;

import com.gjhi.tinkersinnovation.register.TinkersInnovationBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.block.Blocks;
import com.gjhi.tinkersinnovation.TinkersInnovationConfig;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
public class TIOreFeature {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, "tinkersinnovation");
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, "tinkersinnovation");

    // 创建矿石特征
    public static final RegistryObject<ConfiguredFeature<?, ?>> VOID_CRYSTAL_ORE = CONFIGURED_FEATURES.register(
            "void_crystal_ore", () -> new ConfiguredFeature<>(
                    Feature.ORE, new OreConfiguration(
                    // 使用 OreConfiguration.target 方法创建 TargetBlockState
                    List.of(OreConfiguration.target(
                            new BlockMatchTest(Blocks.STONE), // 匹配 STONE 方块 
                            TinkersInnovationBlocks.void_crystal_ore.get().defaultBlockState() // 替换为自定义矿石方块
                    )),
                    TinkersInnovationConfig.Common.voidCrystalOreSize.get() // 矿脉大小
            )
            )
    );

    // 放置特征
    public static final RegistryObject<PlacedFeature> VOID_CRYSTAL_ORE_PLACED = PLACED_FEATURES.register(
            "void_crystal_ore_placed", () -> new PlacedFeature(
                    Holder.direct(VOID_CRYSTAL_ORE.get()),
                    List.of(
                            CountPlacement.of(TinkersInnovationConfig.Common.voidCrystalOreCount.get()), // 动态频率
                            HeightRangePlacement.uniform(
                                    VerticalAnchor.absolute(TinkersInnovationConfig.Common.voidCrystalOreMinHeight.get()), // 动态最小高度
                                    VerticalAnchor.absolute(TinkersInnovationConfig.Common.voidCrystalOreMaxHeight.get()) // 动态最大高度
                            )
                    )
            )
    );
}
