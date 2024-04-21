package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class ChromaticAberrationModifier extends Modifier {
    @Override
    public @NotNull List<ItemStack> processLoot(@NotNull IToolStackView tool, int level, List<ItemStack> generatedLoot, @NotNull LootContext context) {
        for(ItemStack item :generatedLoot){
            if(item.is(ItemTags.WOOL)){
                generatedLoot.remove(item);
            }
        }
        return generatedLoot;
    }
}
