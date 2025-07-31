package com.gjhi.tinkersinnovation.register;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class TinkersInnovationFoods {
    public static final FoodProperties BLOOD_BOTTLE = (new FoodProperties.Builder()).alwaysEat().nutrition(6).saturationMod(0.15F).effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1200), 1.0F).build();
}
