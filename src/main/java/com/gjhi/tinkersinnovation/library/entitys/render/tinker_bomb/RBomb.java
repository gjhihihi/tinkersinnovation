package com.gjhi.tinkersinnovation.library.entitys.render.tinker_bomb;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableLauncherItem;

public class RBomb extends ArrowRenderer<EBomb> {
    private final ItemRenderer itemRenderer;
    public RBomb(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(EBomb entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        /*if (entity.tickCount >= 2 || !(this.f_114476_.f_114358_.m_90592_().m_20280_(entity) < 12.25)) {
            matrixStackIn.pushPose();
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-((float)entity.tickCount + partialTicks) * 30.0F % 360.0F));
            matrixStackIn.translate(-0.03125, -0.09375, 0.0);
            Entity var8 = entity.getOwner();
            if (var8 instanceof LivingEntity owner) {
                ItemStack stack = owner.getMainHandItem().getItem().equals(TinkersInnovationItems.tinker_bomb.get()) ? owner.getMainHandItem() : owner.getUseItem();
                this.itemRenderer.render(stack, ItemTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, entity.getId());
            } else {
                this.itemRenderer.render(TinkersInnovationItems.tinker_bomb.get().getRenderTool(), ItemTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn, entity.getId());
            }
            matrixStackIn.popPose();
        }*/
        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(EBomb eBomb) {
        //return InventoryMenu.BLOCK_ATLAS;
        return TinkersInnovation.getResource("textures/item/tool/tinker_bomb/bomb");
    }
}
