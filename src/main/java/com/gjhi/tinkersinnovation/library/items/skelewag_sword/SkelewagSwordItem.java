package com.gjhi.tinkersinnovation.library.items.skelewag_sword;

import com.gjhi.tinkersinnovation.events.ItemRenderProperties;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

import java.util.function.Consumer;

public class SkelewagSwordItem extends ModifiableItem {
    public SkelewagSwordItem(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        //consumer.accept(new ItemRenderProperties());
    }
}
