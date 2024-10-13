package com.gjhi.tinkersinnovation.mixins;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import dev.xkmc.l2hostility.compat.curios.EntitySlotAccess;
import dev.xkmc.l2hostility.content.traits.legendary.RagnarokTrait;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(value = RagnarokTrait.class, remap = false)
public abstract class NoSealedMixin {
    @Inject(at = @At("HEAD"), method = "allowSeal", cancellable = true)
    private static void allowSeal(EntitySlotAccess access, CallbackInfoReturnable<Boolean> cir){
        ToolStack tool = ToolStack.from(access.get());
        if (tool.getModifierLevel(TinkersInnovationModifiers.L2HostilityModifiers.no_sealed.get()) > 0){
            cir.setReturnValue(false);
        }
    }
}
