package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.library.ModBrewingRecipe;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);
    private static Potion register_potion(MobEffect effect, PotionType type) {
        return new Potion(new MobEffectInstance(effect, type.time, type.level));
    }
    private static Potion register_potion(MobEffect effect, int time, int level) {
        return new Potion(new MobEffectInstance(effect, time, level));
    }
    private static void potionBrewing(ItemStack inputPot, Potion pot, Item item) {
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(inputPot, Ingredient.of(item), PotionUtils.setPotion(new ItemStack(Items.POTION), pot)));
    }
    private enum PotionType{
        COMMON(1200, 0),
        LONG(400, 1),
        STRONG(2400, 0);

        final int time;
        final int level;
        PotionType(int time, int level) {
            this.time = time;
            this.level = level;
        }
    }

    public static RegistryObject<Potion> soul_devouring_potion = POTIONS.register("soul_devouring_potion", () -> register_potion(TinkersInnovationEffects.soulDevouringEffect.get(), PotionType.COMMON));
    public static RegistryObject<Potion> soul_devouring_potion_strong = POTIONS.register("soul_devouring_potion_strong", () -> register_potion(TinkersInnovationEffects.soulDevouringEffect.get(), PotionType.STRONG));
    public static RegistryObject<Potion> soul_devouring_potion_long = POTIONS.register("soul_devouring_potion_long", () -> register_potion(TinkersInnovationEffects.soulDevouringEffect.get(), PotionType.LONG));
    public static void recipesInit(){
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD),TinkersInnovationPotions.soul_devouring_potion.get(), TinkersInnovationItems.soul_bone.get());
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), TinkersInnovationPotions.soul_devouring_potion.get()),TinkersInnovationPotions.soul_devouring_potion_strong.get(), Items.GLOWSTONE_DUST);
        potionBrewing(PotionUtils.setPotion(new ItemStack(Items.POTION), TinkersInnovationPotions.soul_devouring_potion.get()),TinkersInnovationPotions.soul_devouring_potion_long.get(), Items.REDSTONE);
    }
}
