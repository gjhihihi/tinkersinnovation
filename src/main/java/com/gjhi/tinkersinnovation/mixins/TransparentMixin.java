package com.gjhi.tinkersinnovation.mixins;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import static slimeknights.tconstruct.library.modifiers.Modifier.getHeldTool;

@Mixin(ItemInHandLayer.class)
public abstract class TransparentMixin {
    @Inject(at = @At("HEAD"), method = "renderArmWithItem", cancellable = true)
    public void hideInvisibleItem(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType type, HumanoidArm arm, PoseStack pose, MultiBufferSource buffer, int light, CallbackInfo ci) {
        if (stack.getItem() instanceof IModifiable && entity.hasEffect(MobEffects.INVISIBILITY)){
            ToolStack tool = ToolStack.from(stack);
            for (ModifierEntry modifier: tool.getModifiers().getModifiers()){
                if (modifier.getId().equals(TinkersInnovationModifiers.transparent.getId())){
                    ci.cancel();
                }
            }
        }
    }
}
