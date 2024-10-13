package com.gjhi.tinkersinnovation.library.entitys.render.efln_arrow;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.library.entitys.entitys.efln_arrow.EEFLNArrow;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class REFLNArrow extends ArrowRenderer<EEFLNArrow> {
    public REFLNArrow(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(EEFLNArrow arrow) {
        return TinkersInnovation.getResource("textures/entity/projectiles/efln_arrow");
    }
}
