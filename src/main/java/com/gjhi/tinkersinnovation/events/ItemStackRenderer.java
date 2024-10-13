package com.gjhi.tinkersinnovation.events;

import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;

public class ItemStackRenderer extends BlockEntityWithoutLevelRenderer {
    public ItemStackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, ItemTransforms.TransformType transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (itemStackIn.getItem().equals(TinkersInnovationItems.skelewag_sword.get())){
            ItemStack inventory = new ItemStack(TinkersInnovationItems.skelewag_sword_inventory.get());
            ItemStack hand = new ItemStack(TinkersInnovationItems.skelewag_sword_hand.get());
            hand.setTag(itemStackIn.getTag());
            inventory.setTag(itemStackIn.getTag());
            if (transformType != ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND && transformType != ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND && transformType != ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND && transformType != ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
                Minecraft.getInstance().getItemRenderer().renderStatic(hand, transformType, transformType == ItemTransforms.TransformType.GROUND ? combinedLightIn : 240, combinedOverlayIn, matrixStackIn, bufferIn, 0);
            } else {
                Minecraft.getInstance().getItemRenderer().renderStatic(inventory, transformType, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, 0);
            }
        }
    }
}
