package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TinkersInnovationTags {
    public static void init() {
        TinkersInnovationTags.Blocks.init();
        TinkersInnovationTags.Items.init();
        TinkersInnovationTags.EntityTypes.init();
    }
    public static class Blocks{
        public static final TagKey<Block> BASE_STONE_END = tag("base_stone_end");
        private static TagKey<Block> tag(String path) {
            return TagKey.create(Registries.BLOCK, TinkersInnovation.getResource(path));
        }
        private static void init() {
        }
    }
    public static class Items{
        public static final TagKey<Item> DIFFICULT_MATERIAL = tag("traits_restructuring/difficult_material");
        public static final TagKey<Item> EXPENSIVE_MATERIAL = tag("traits_restructuring/expensive_material");
        private static TagKey<Item> tag(String path) {
            return TagKey.create(Registries.ITEM, TinkersInnovation.getResource(path));
        }
        private static void init() {
        }
    }
    public static class EntityTypes{
        public static final TagKey<EntityType<?>> ANIMALS = tag("animals");
        private static TagKey<EntityType<?>> tag(String path) {
            return TagKey.create(Registries.ENTITY_TYPE, TinkersInnovation.getResource(path));
        }
        private static void init() {
        }
    }
}
