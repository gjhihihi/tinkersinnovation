package com.gjhi.tinkersinnovation.register;

import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationMaterials {
    public static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(MOD_ID, name));
    }

    public static final MaterialId polychrome_alloy = createMaterial("polychrome_alloy");
    public static final MaterialVariantId shine_polychrome_alloy = MaterialVariantId.create(polychrome_alloy, "shined");
    public static final MaterialId void_crystal = createMaterial("void_crystal");
    public static final MaterialId enchantment_essence = createMaterial("enchantment_essence");
    public static final MaterialId totemic_gold = createMaterial("totemic_gold");
    public static final MaterialId poseidite = createMaterial("poseidite");
    public static final MaterialId experience = createMaterial("experience");
    public static final MaterialId andesite_alloy = createMaterial("andesite_alloy");
    public static final MaterialId decline = createMaterial("decline");
    public static final MaterialId slimton = createMaterial("slimton");
    public static final MaterialId apatite = createMaterial("apatite");
    public static final MaterialId shulkerate = createMaterial("shulkerate");
    public static final MaterialId sculkium = createMaterial("sculkium");
    public static final MaterialId eternium = createMaterial("eternium");
    public static final MaterialId efln = createMaterial("efln");
    public static final MaterialId chaos = createMaterial("chaos");
    public static final MaterialId miracle = createMaterial("miracle");
    public static final MaterialId hostilium = createMaterial("hostilium");
    public static final MaterialId clonate = createMaterial("clonate");
}