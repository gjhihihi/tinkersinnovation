package com.gjhi.tinkersinnovation.mixins;

import com.gjhi.tinkersinnovation.register.TinkersInnovationEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Creeper.class)
public abstract class CreeperMixin extends Monster {
    protected CreeperMixin(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Shadow public abstract boolean isPowered();

    @Inject(at = @At("HEAD"), method = "explodeCreeper")
    public void explodePoweredCreeper(CallbackInfo ci){
        if (!this.level().isClientSide){
            if (this.isPowered()){
                this.addEffect(new MobEffectInstance(TinkersInnovationEffects.thunderTribulationEffect.get(),100));
            }
        }
    }
}