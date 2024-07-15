package com.gjhi.tinkersinnovation.modifiers.base;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class ChaosDamageSourcesBase {
    protected static Random RANDOM = new Random();
    private static final DamageSource[] sources = {
            DamageSource.FALL,
            DamageSource.FREEZE,
            DamageSource.IN_FIRE,
            DamageSource.MAGIC,
            DamageSource.explosion((LivingEntity)null),
            new DamageSource("projectile").setProjectile()
    };
    public static DamageSource randomSource(){
        int i = RANDOM.nextInt(7);
        return sources[i];
    }
}
