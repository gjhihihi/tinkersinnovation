package com.gjhi.tinkersinnovation;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TinkersInnovationTags {
    public static final TagKey<Block> BASE_STONE_END = create("base_stone_end");
    private static TagKey<Block> create(String p_203847_) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(p_203847_));
    }
}
