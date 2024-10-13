package com.gjhi.tinkersinnovation.library.items.tinker_bomb;

import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class IBomb extends ArrowItem {
    public IBomb() {
        super(new Item.Properties());
    }

    public static ItemStack setAmmo() {
        ItemStack stack = new ItemStack(TinkersInnovationItems.bomb_item.get());
        stack.setCount(1);
        return stack;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity entity) {
        return new EBomb(level, entity);
    }
}
