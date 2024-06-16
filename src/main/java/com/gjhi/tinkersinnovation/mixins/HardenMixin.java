package com.gjhi.tinkersinnovation.mixins;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(ToolStack.class)
public abstract class HardenMixin {/*

    @Shadow public abstract ModifierNBT getModifiers();

    @Shadow public abstract int getDamage();

    @Inject(at = @At("HEAD"), method = "setDamage", cancellable = true)
    public void harden(int damage, CallbackInfo ci){
        if (this.getModifiers().getLevel(TinkersInnovationModifiers.harden.getId()) > 0){
            damage = Math.min(damage, this.getDamage()+1);
        }
    }*/
}
