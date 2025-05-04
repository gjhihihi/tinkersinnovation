package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.library.recipes.ModifiersRestructuringRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;

public class TinkersInnovationRecipes {
    public static final SynchronizedDeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = SynchronizedDeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TinkersInnovation.MOD_ID);
    public static final RegistryObject<SimpleRecipeSerializer<ModifiersRestructuringRecipe>> MODIFIERS_RESTRUCTURING_RECIPE = RECIPE_SERIALIZERS.register("modifiers_restructuring_recipe", () -> new SimpleRecipeSerializer<>(ModifiersRestructuringRecipe::new));
}
