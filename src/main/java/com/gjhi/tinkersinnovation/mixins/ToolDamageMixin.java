package com.gjhi.tinkersinnovation.mixins;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(value = ToolDamageUtil.class, remap = false)
public abstract class ToolDamageMixin {
    @Inject(at = @At(value = "HEAD"), method = "directDamage", cancellable = true)
    private static void ToolDamage(IToolStackView tool, int amount, LivingEntity entity, ItemStack stack, CallbackInfoReturnable<Boolean> ci){
        if (tool.getModifierLevel(TinkersInnovationModifiers.eternal.get()) > 0){
            ci.setReturnValue(true);
        }
        if (tool.getModifierLevel(TinkersInnovationModifiers.harden.get()) > 0){
            amount = Math.min(amount, 1);
            ci.setReturnValue(TinkersInnovationUtils.noMixinDirectDamage(tool, amount, entity, stack));
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "breakTool", cancellable = true)
    private static void ToolBreak(ItemStack stack, CallbackInfo ci){
        if (stack.getItem() instanceof IModifiable) {
            ToolStack tool = ToolStack.from(stack);
            if (tool.getModifierLevel(TinkersInnovationModifiers.eternal.get()) > 0) {
                ci.cancel();
            }
        }
    }
}
