package com.gjhi.tinkersinnovation.library.modifiers.base;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class ChaosDamageSourcesBase {
    protected static Random RANDOM = new Random();
    public static DamageSource randomSource(DamageSource source){
        switch (RANDOM.nextInt(7)){
            case 0 -> source.setExplosion();
            case 1 -> source.setProjectile();
            case 2 -> source.setIsFall();
            case 3 -> source.setMagic();
            case 4 -> source.setIsFire();
            case 5 -> source.damageHelmet();
            case 6 -> source.setNoAggro();
        }
        return source;
    }
}
