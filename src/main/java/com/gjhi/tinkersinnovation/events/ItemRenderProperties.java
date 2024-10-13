package com.gjhi.tinkersinnovation.events;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ItemRenderProperties implements IClientItemExtensions {
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return new ItemStackRenderer();
    }
}
