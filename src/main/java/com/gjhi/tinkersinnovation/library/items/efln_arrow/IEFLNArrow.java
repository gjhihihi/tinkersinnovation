package com.gjhi.tinkersinnovation.library.items.efln_arrow;

import com.gjhi.tinkersinnovation.library.entitys.entitys.efln_arrow.EEFLNArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IEFLNArrow extends ArrowItem {
    public IEFLNArrow() {
        super(new Item.Properties());
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity entity) {
        return new EEFLNArrow(level, entity);
    }
}
