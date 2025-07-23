package com.gjhi.tinkersinnovation.library.recipes;

import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import com.gjhi.tinkersinnovation.register.TinkersInnovationRecipes;
import com.gjhi.tinkersinnovation.register.TinkersInnovationSlots;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.recipe.RecipeResult;
import slimeknights.tconstruct.library.recipe.tinkerstation.IMutableTinkerStationContainer;
import slimeknights.tconstruct.library.recipe.tinkerstation.ITinkerStationContainer;
import slimeknights.tconstruct.library.recipe.tinkerstation.ITinkerStationRecipe;
import slimeknights.tconstruct.library.tools.definition.module.material.ToolPartsHook;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.part.IToolPart;

import java.util.List;

public class TraitsRestructuringRecipe implements ITinkerStationRecipe {
    private final ResourceLocation id;
    private final int reinforcement_count = 5;

    public TraitsRestructuringRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public boolean matches(ITinkerStationContainer inv, Level level) {
        ToolStack tool = inv.getTinkerable();
        ItemStack stack = inv.getTinkerableStack();
        if (!stack.isEmpty() && stack.is(TinkerTags.Items.MULTIPART_TOOL)){
                for(int i = 0; i < inv.getInputCount(); ++i) {
                    ItemStack input = inv.getInput(i);
                    if (input.getItem() instanceof IToolPart) {
                        List<IToolPart> parts = ToolPartsHook.parts(tool.getDefinition());
                        if (parts.isEmpty()) {
                            return false;
                        }
                        return this.checkMeta(inv) && parts.stream().anyMatch((p) -> p.asItem() == input.getItem());
                    }
                }
        }
        return false;
    }

    private boolean checkMeta(ITinkerStationContainer inv) {
        ItemStack name_tag = ItemStack.EMPTY;
        ItemStack polychrome_alloy_reinforcement = ItemStack.EMPTY;
        ItemStack expensive = ItemStack.EMPTY;
        ItemStack difficult = ItemStack.EMPTY;

        for(int i = 0; i < inv.getInputCount(); ++i) {
            ItemStack input = inv.getInput(i);
            if (input.is(Items.NAME_TAG)) {
                name_tag = input;
            } else if (input.is(TinkersInnovationItems.polychrome_alloy_reinforcement.get())) {
                polychrome_alloy_reinforcement = input;
            } else if (input.is(Items.NETHERITE_BLOCK)) {
                expensive = input;
            } else if (input.is(Items.NETHER_STAR)) {
                difficult = input;
            }
        }

        return !name_tag.isEmpty()
                && !polychrome_alloy_reinforcement.isEmpty()
                && polychrome_alloy_reinforcement.getCount() >= reinforcement_count
                && !expensive.isEmpty()
                && !difficult.isEmpty();
    }

    @Override
    public RecipeResult<ItemStack> getValidatedResult(ITinkerStationContainer inv) {
        ToolStack tool = inv.getTinkerable();
        ItemStack name_tag = ItemStack.EMPTY;
        ItemStack part = ItemStack.EMPTY;
        IToolPart part1 = null;
        ModifierEntry trait = null;
        if (tool.getFreeSlots(TinkersInnovationSlots.OMNIPOTENT) == 0){
            return RecipeResult.failure(Component.translatable(TConstruct.makeTranslationKey("recipe", "modifier.not_enough_slot"), TinkersInnovationSlots.OMNIPOTENT.getDisplayName()));
        }
        for(int i = 0; i < inv.getInputCount(); ++i) {
            ItemStack input = inv.getInput(i);
            if (input.is(Items.NAME_TAG)) {
                name_tag = input;
            }
            Item var6 = input.getItem();
            if (var6 instanceof IToolPart part2) {
                part = input;
                part1 = part2;
            }
        }
        if (part1 != null && name_tag != ItemStack.EMPTY) {
            for (ModifierEntry trait1 : MaterialRegistry.getInstance().getTraits(part1.getMaterial(part).getId(), part1.getStatType())) {
                if (trait1.getId().toString().equals(name_tag.getHoverName().getString())){
                    trait = trait1;
                    break;
                }
            }
        }
        if (trait != null) {
            if (trait.matches(TinkersInnovationModifiers.omnipotent.getId())){
                return RecipeResult.failure(Component.translatable("recipe.tconstruct.modifier.traits_restructuring.omnipotent"));
            }
            ToolStack newTool = tool.copy();
            newTool.addModifier(trait.getId(), trait.getLevel());
            newTool.getPersistentData().addSlots(TinkersInnovationSlots.OMNIPOTENT, -1);
            return RecipeResult.success(newTool.createStack());
        }else {
            return RecipeResult.failure(Component.translatable("recipe.tconstruct.modifier.traits_restructuring.not_included"));
        }
    }

    @Override
    public void updateInputs(ItemStack result, IMutableTinkerStationContainer inv, boolean isServer) {
        for(int index = 0; index < inv.getInputCount(); ++index) {
            if (inv.getInput(index).getItem().equals(TinkersInnovationItems.polychrome_alloy_reinforcement.get())){
                inv.shrinkInput(index, reinforcement_count);
            }else {
                inv.shrinkInput(index, 1);
            }
        }
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TinkersInnovationRecipes.TRAITS_RESTRUCTURING_RECIPE.get();
    }
}
