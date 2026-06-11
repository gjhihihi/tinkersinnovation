package com.gjhi.tinkersinnovation.contexts;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.library.tools.part.PartCastItem;

public class BetterItemDeferredRegisterExtension extends ItemDeferredRegisterExtension {
    public BetterItemDeferredRegisterExtension(String modID) {
        super(modID);
    }
    public CastItemObject registerCast(RegistryObject<? extends IMaterialItem> item, Item.Properties props) {
        return this.registerCast(item.getId().getPath(), () -> new PartCastItem(props, item));
    }
}
