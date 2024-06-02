package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;


public class ChromaticAberrationModifier extends NoLevelsModifier {
    private static final Item[] wool ={
            Items.WHITE_WOOL,Items.ORANGE_WOOL,Items.MAGENTA_WOOL,Items.LIGHT_BLUE_WOOL,Items.YELLOW_WOOL,
            Items.LIME_WOOL,Items.PINK_WOOL,Items.GRAY_WOOL,Items.LIGHT_GRAY_WOOL,Items.CYAN_WOOL,
            Items.PURPLE_WOOL,Items.BLUE_WOOL,Items.BROWN_WOOL,Items.GREEN_WOOL,Items.RED_WOOL,
            Items.BLACK_WOOL
    };
    @Override
    public @NotNull List<ItemStack> processLoot(@NotNull IToolStackView tool, int level, List<ItemStack> generatedLoot, @NotNull LootContext context) {
        int flag = 0;
        for(ItemStack item :generatedLoot){
            if(item.is(ItemTags.WOOL)){
                generatedLoot.remove(item);
                ++flag;
            }
        }
        for(int i = 0;i < flag;i++){
            generatedLoot.add(new ItemStack(wool[RANDOM.nextInt(15)]));
        }
        return generatedLoot;
    }
}
