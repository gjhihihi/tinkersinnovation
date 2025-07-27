package com.gjhi.tinkersinnovation.library.entitys.render.tinker_bomb;

import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class RBomb extends ArrowRenderer<EBomb> {
    private final ItemRenderer itemRenderer;
    public RBomb(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    private ItemStack getRender(LivingEntity owner) {
        if (owner.getMainHandItem().is(TinkersInnovationItems.tinker_bomb.get())) {
            return owner.getMainHandItem();
        } else {
            return owner.getOffhandItem().is(TinkersInnovationItems.tinker_bomb.get()) ? owner.getOffhandItem() : TinkersInnovationItems.tinker_bomb.get().getRenderTool();
        }
    }

    @Override
    public void render(EBomb entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < 12.25)) {
            matrixStackIn.pushPose();
            //matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            //matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-((float)entity.tickCount + partialTicks) * 30.0F % 360.0F));
            matrixStackIn.translate(-0.03125, -0.09375, 0.0);
            Entity var8 = entity.getOwner();
            if (var8 instanceof LivingEntity owner) {
                this.itemRenderer.renderStatic(this.getRender(owner), ItemDisplayContext.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, entity.level(), entity.getId());
            } else {
                this.itemRenderer.renderStatic(TinkersInnovationItems.tinker_bomb.get().getRenderTool(), ItemDisplayContext.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, entity.level(), entity.getId());
            }
            matrixStackIn.popPose();
        }
    }

    @Override
    public ResourceLocation getTextureLocation(EBomb eBomb) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
