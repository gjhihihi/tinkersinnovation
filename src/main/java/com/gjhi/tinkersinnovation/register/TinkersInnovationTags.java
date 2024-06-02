package com.gjhi.tinkersinnovation.register;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import slimeknights.tconstruct.common.TinkerTags;

public class TinkersInnovationTags {
    public static void init() {
        TinkersInnovationTags.Blocks.init();
        TinkersInnovationTags.Items.init();
        TinkersInnovationTags.EntityTypes.init();
    }
    public static class Blocks{
        public static final TagKey<Block> BASE_STONE_END = create("base_stone_end");
        private static TagKey<Block> create(String p_203847_) {
            return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(p_203847_));
        }
        private static void init() {
        }
    }
    public static class Items{
        public static final TagKey<Item> HANDGUARD = create("handguard");
        public static final TagKey<Item> SHIELD_PLATE = create("shield_plate");
        private static TagKey<Item> create(String p_203847_) {
            return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(p_203847_));
        }
        private static void init() {
        }
    }
    public static class EntityTypes{
        public static final TagKey<EntityType<?>> ANIMALS = create("animals");
        private static TagKey<EntityType<?>> create(String p_203847_) {
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(p_203847_));
        }
        private static void init() {
        }
    }
}
