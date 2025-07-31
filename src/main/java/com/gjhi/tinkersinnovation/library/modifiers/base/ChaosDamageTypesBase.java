package com.gjhi.tinkersinnovation.library.modifiers.base;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import slimeknights.tconstruct.common.TinkerDamageTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaosDamageTypesBase {
    private static final List<ResourceKey<DamageType>> TYPES = new ArrayList<>(List.of(
            DamageTypes.ON_FIRE,
            DamageTypes.FREEZE,
            DamageTypes.ARROW,
            DamageTypes.EXPLOSION,
            DamageTypes.FALL,
            DamageTypes.INDIRECT_MAGIC,
            DamageTypes.STARVE,
            DamageTypes.THORNS,
            DamageTypes.FALLING_ANVIL,
            DamageTypes.FELL_OUT_OF_WORLD
    ));
    public static ResourceKey<DamageType> getChaosDamageType(){
        return TYPES.get(new Random().nextInt(TYPES.size()));
    }
}
