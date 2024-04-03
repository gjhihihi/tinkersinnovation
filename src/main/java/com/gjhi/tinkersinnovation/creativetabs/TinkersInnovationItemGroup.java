package com.gjhi.tinkersinnovation.creativetabs;

import com.gjhi.tinkersinnovation.TinkersInnovationItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TinkersInnovationItemGroup extends CreativeModeTab {
    public TinkersInnovationItemGroup() {
        super("TinkersInnovationItemGroup");
    }
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(TinkersInnovationItems.polychrome_alloy_ingot.get());
    }
}
