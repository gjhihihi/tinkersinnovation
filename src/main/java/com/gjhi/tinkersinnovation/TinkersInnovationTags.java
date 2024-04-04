package com.gjhi.tinkersinnovation;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TinkersInnovationTags {
    public static final TagKey<Block> BASE_STONE_END = create_block("base_stone_end");
    public static final TagKey<Item> HANDGUARD = create_item("handguard");
    public static final TagKey<Item> SHIELD_PLATE = create_item("shield_plate");
    private static TagKey<Block> create_block (String p_203847_) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(p_203847_));
    }
    private static TagKey<Item> create_item (String p_203847_) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(p_203847_));
    }
}
