package com.gjhi.tinkersinnovation.register;

import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationMaterials {
    public static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(MOD_ID, name));
    }

    public static final MaterialId polychrome_alloy = createMaterial("polychrome_alloy");
    public static final MaterialId void_crystal = createMaterial("void_crystal");
    public static final MaterialId enchantment_essence = createMaterial("enchantment_essence");
    public static final MaterialId life_essence = createMaterial("life_essence");
    public static final MaterialId ocean_essence = createMaterial("ocean_essence");
    public static final MaterialId andesite_alloy = createMaterial("andesite_alloy");
    public static final MaterialId experience = createMaterial("experience");
    //public static final MaterialId shadow_steel = createMaterial("shadow_steel");
    //public static final MaterialId refined_radiance = createMaterial("refined_radiance");
    //public static final MaterialId rose_quartz = createMaterial("rose_quartz");
    public static final MaterialId ether = createMaterial("ether");
}