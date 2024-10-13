package com.gjhi.tinkersinnovation.library.modifiers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.recipe.RecipeCacheInvalidator;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.recipe.SingleItemContainer;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class CrushingModifier extends NoLevelsModifier implements ProcessLootModifierHook {
    private final Cache<Item, Optional<CrushingRecipe>> recipeCache = CacheBuilder.newBuilder().maximumSize(64L).build();
    private final SingleItemContainer inventory = new SingleItemContainer();
    public CrushingModifier() {
        RecipeCacheInvalidator.addReloadListener((client) -> {
            if (!client) {
                this.recipeCache.invalidateAll();
            }
        });
    }

    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROCESS_LOOT);
    }

/*
    private Optional<CrushingRecipe> findRecipe(ItemStack stack, Level world) {
        this.inventory.setStack(stack);
        return world.getRecipeManager().getRecipeFor(AllRecipeTypes.CRUSHING.getType(), this.inventory, world);
    }

    @Nullable
    private CrushingRecipe findCachedRecipe(ItemStack stack, Level world) {
        if (stack.hasTag()) {
            return this.findRecipe(stack, world).orElse(null);
        } else {
            try {
                return (CrushingRecipe)((Optional)this.recipeCache.get(stack.getItem(), () -> this.findRecipe(stack, world))).orElse(null);
            } catch (ExecutionException var4) {
                return null;
            }
        }
    }

    private List<ItemStack> crushItem(ItemStack stack, Level world) {
            CrushingRecipe recipe = this.findCachedRecipe(stack, world);
            if (recipe != null) {
                this.inventory.setStack(stack);
                List<ItemStack> output = recipe.getRollableResultsAsItemStacks();
                if (stack.getCount() > 1) {
                    for (ItemStack item : output){
                        item.setCount(item.getCount() * stack.getCount());
                    }
                }

                return output;
            } else {
                List<ItemStack> output = new ArrayList<>();
                output.add(stack);
                return output;
            }
    }
*/

    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        /*Level world = context.getLevel();
        List<ItemStack> loot = new ArrayList<>();
        if (!generatedLoot.isEmpty()) {
            for (ItemStack stack : generatedLoot) {
                List<ItemStack> crushed = this.crushItem(stack, world);
                loot.addAll(crushed);
            }
        }
        generatedLoot.clear();
        generatedLoot.addAll(loot);*/
    }
}
