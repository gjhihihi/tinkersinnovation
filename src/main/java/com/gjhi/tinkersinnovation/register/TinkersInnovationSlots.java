package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slimeknights.mantle.client.model.NBTKeyModel;
import slimeknights.tconstruct.library.tools.SlotType;

public class TinkersInnovationSlots {
    @OnlyIn(Dist.CLIENT)
    public static void init(){
        NBTKeyModel.registerExtraTexture(new ResourceLocation("tconstruct:creative_slot"),
                HOSTILITY.getName(), TinkersInnovation.getResource("item/slots/hostilities")
        );
        NBTKeyModel.registerExtraTexture(new ResourceLocation("tconstruct:creative_slot"),
                MECHANISM.getName(), TinkersInnovation.getResource("item/slots/mechanisms")
        );
        NBTKeyModel.registerExtraTexture(new ResourceLocation("tconstruct:creative_slot"),
                OMNIPOTENT.getName(), TinkersInnovation.getResource("item/slots/omnipotent")
        );
    }
    public static SlotType HOSTILITY = SlotType.getOrCreate("hostilities");
    public static SlotType MECHANISM = SlotType.getOrCreate("mechanisms");
    public static SlotType OMNIPOTENT = SlotType.getOrCreate("omnipotent");
}
