package com.gjhi.tinkersinnovation.modifiers.base;

import net.minecraft.world.effect.MobEffect;

import static net.minecraft.world.effect.MobEffects.*;

public class EnchantmentEffectsBase {
    public static final MobEffect[] GOOD_EFFECTS = {
            LUCK, DIG_SPEED, FIRE_RESISTANCE, MOVEMENT_SPEED, DAMAGE_RESISTANCE, JUMP
    };
    public static final MobEffect[] BAD_EFFECTS = {
            DIG_SLOWDOWN, WITHER, WEAKNESS, MOVEMENT_SLOWDOWN, BLINDNESS, POISON,CONFUSION,HUNGER
    };
}
