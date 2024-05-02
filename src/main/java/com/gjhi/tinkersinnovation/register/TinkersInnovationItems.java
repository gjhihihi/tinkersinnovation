package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.register.tools.TinkersInnovationToolsDefinition;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static com.gjhi.tinkersinnovation.TinkersInnovation.*;

public class TinkersInnovationItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static Item register_item() {
        return new Item(new Item.Properties().tab(itemGroup));
    }
    private static final Item.Properties TOOL = new Item.Properties().stacksTo(1).tab(toolGroup);
    private static final Item.Properties PARTS_PROPS = new Item.Properties().tab(toolGroup);
    public static BlockItem register_block(Block block) {
        return new BlockItem(block, new Item.Properties().tab(itemGroup));
    }
    //ingots
    public static RegistryObject<Item> polychrome_alloy_ingot = ITEMS.register("polychrome_alloy_ingot", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> void_crystal_ingot = ITEMS.register("void_crystal_ingot", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> enchantment_ingot = ITEMS.register("enchantment_ingot", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> life_ingot = ITEMS.register("life_ingot", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> ocean_ingot = ITEMS.register("ocean_ingot", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> slimton_ingot = ITEMS.register("slimton_ingot", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> ether_ingot = ITEMS.register("ether_ingot", TinkersInnovationItems::register_item);
    //nuggets
    public static RegistryObject<Item> polychrome_alloy_nugget = ITEMS.register("polychrome_alloy_nugget", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> void_crystal_nugget = ITEMS.register("void_crystal_nugget", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> enchantment_nugget = ITEMS.register("enchantment_nugget", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> life_nugget = ITEMS.register("life_nugget", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> ocean_nugget = ITEMS.register("ocean_nugget", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> slimton_nugget = ITEMS.register("slimton_nugget", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> ether_nugget = ITEMS.register("ether_nugget", TinkersInnovationItems::register_item);
    //blocks
    public static RegistryObject<Item> polychrome_alloy_block = ITEMS.register("polychrome_alloy_block", () -> register_block(TinkersInnovationBlocks.polychrome_alloy_block.get()));
    public static RegistryObject<Item> void_crystal_block = ITEMS.register("void_crystal_block", () -> register_block(TinkersInnovationBlocks.void_crystal_block.get()));
    public static RegistryObject<Item> enchantment_block = ITEMS.register("enchantment_block", () -> register_block(TinkersInnovationBlocks.enchantment_block.get()));
    public static RegistryObject<Item> life_block = ITEMS.register("life_block", () -> register_block(TinkersInnovationBlocks.life_block.get()));
    public static RegistryObject<Item> ocean_block = ITEMS.register("ocean_block", () -> register_block(TinkersInnovationBlocks.ocean_block.get()));
    public static RegistryObject<Item> slimton_block = ITEMS.register("slimton_block", () -> register_block(TinkersInnovationBlocks.slimton_block.get()));
    public static RegistryObject<Item> ether_block = ITEMS.register("ether_block", () -> register_block(TinkersInnovationBlocks.ether_block.get()));
    //ores
    public static RegistryObject<Item> void_crystal_ore = ITEMS.register("void_crystal_ore", () -> register_block(TinkersInnovationBlocks.void_crystal_ore.get()));
    //others
    public static RegistryObject<Item> seared_bedrock = ITEMS.register("seared_bedrock", () -> register_block(TinkersInnovationBlocks.seared_bedrock.get()));
    public static RegistryObject<Item> scorched_bedrock = ITEMS.register("scorched_bedrock", () -> register_block(TinkersInnovationBlocks.scorched_bedrock.get()));
    //raw ores
    public static RegistryObject<Item> raw_void_crystal = ITEMS.register("raw_void_crystal", TinkersInnovationItems::register_item);
    //tool parts
    public static final RegistryObject<ToolPartItem> handguard = ITEMS.register("handguard", () -> new ToolPartItem(PARTS_PROPS, HandleMaterialStats.ID));
    public static RegistryObject<Item> handguard_cast = ITEMS.register("handguard_cast", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> handguard_sand_cast = ITEMS.register("handguard_sand_cast", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> handguard_red_sand_cast = ITEMS.register("handguard_red_sand_cast", TinkersInnovationItems::register_item);
    public static final RegistryObject<ToolPartItem> shield_plate = ITEMS.register("shield_plate", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID));
    public static RegistryObject<Item> shield_plate_cast = ITEMS.register("shield_plate_cast", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> shield_plate_sand_cast = ITEMS.register("shield_plate_sand_cast", TinkersInnovationItems::register_item);
    public static RegistryObject<Item> shield_plate_red_sand_cast = ITEMS.register("shield_plate_red_sand_cast", TinkersInnovationItems::register_item);
    //tools
    public static final RegistryObject<ModifiableItem> pick_dart = ITEMS.register("pick_dart", () -> new ModifiableItem(TOOL, TinkersInnovationToolsDefinition.PickDart));
    public static final RegistryObject<ModifiableItem> heavy_shield = ITEMS.register("heavy_shield", () -> new ModifiableItem(TOOL, TinkersInnovationToolsDefinition.HeavyShield));
    public static final RegistryObject<ModifiableItem> butcher_knife = ITEMS.register("butcher_knife", () -> new ModifiableItem(TOOL, TinkersInnovationToolsDefinition.ButcherKnife));

}