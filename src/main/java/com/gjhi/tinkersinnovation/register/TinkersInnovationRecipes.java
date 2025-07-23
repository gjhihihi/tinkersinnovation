package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.library.recipes.TraitsRestructuringRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;

public class TinkersInnovationRecipes {
    public static final SynchronizedDeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = SynchronizedDeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TinkersInnovation.MOD_ID);
    public static final RegistryObject<SimpleRecipeSerializer<TraitsRestructuringRecipe>> TRAITS_RESTRUCTURING_RECIPE = RECIPE_SERIALIZERS.register("traits_restructuring", () -> new SimpleRecipeSerializer<>(TraitsRestructuringRecipe::new));
}
