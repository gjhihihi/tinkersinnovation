package com.gjhi.tinkersinnovation.mixins;

import com.gjhi.tinkersinnovation.modifiers.HardenModifier;
import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
@Mixin(ToolDamageUtil.class)
public abstract class HardenMixin {
    /*@Inject(at = @At(value = "HEAD"), method = "directDamage")
    private static void harden(IToolStackView tool, int amount, LivingEntity entity, ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if (tool.getModifierLevel(TinkersInnovationModifiers.harden.get()) > 0){
            amount = 1;
        }
        if (tool.getModifierLevel(TinkersInnovationModifiers.eternal.get()) > 0){
            cir.cancel();
        }
    }*/
}
