package com.gjhi.tinkersinnovation.creativetabs;

import com.gjhi.tinkersinnovation.TinkersInnovationItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TinkersInnovationToolGroup extends CreativeModeTab {
    public TinkersInnovationToolGroup() {
        super("TinkersInnovationToolGroup");
    }
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(TinkersInnovationItems.pick_dart.get());
    }
}
