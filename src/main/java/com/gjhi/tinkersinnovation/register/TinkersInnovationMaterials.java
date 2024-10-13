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
    public static final MaterialId gorgon_hair = createMaterial("gorgon_hair");
    public static final MaterialId ruby = createMaterial("ruby");
    public static final MaterialId sapphire = createMaterial("sapphire");
    public static final MaterialId straddlite_alloy = createMaterial("straddlite_alloy");
    public static final MaterialId bear_fur = createMaterial("bear_fur");
    //public static final MaterialId zinc = createMaterial("zinc");
    public static final MaterialId brass = createMaterial("brass");
    public static final MaterialId farseeing_alloy = createMaterial("farseer_arm");
    public static final MaterialId fools_gold = createMaterial("fools_gold");
    public static final MaterialId blood_bone = createMaterial("blood_bone");
    public static final MaterialId hemolymph_bone = createMaterial("hemolymph_bone");
    public static final MaterialId soul_bone = createMaterial("soul_bone");
    public static final MaterialId honey_bone = createMaterial("honey_bone");
    public static final MaterialId cooked_bone = createMaterial("cooked_bone");
    public static final MaterialId slime_bone = createMaterial("slime_bone");
    public static final MaterialId sunsoul_alloy = createMaterial("sunsoul_alloy");
    public static final MaterialId capsid = createMaterial("capsid");
    public static final MaterialId elastic_tendon = createMaterial("elastic_tendon");
    public static final MaterialId spacebreakium = createMaterial("spacebreakium");
    public static final MaterialId witchium = createMaterial("witchium");
    public static final MaterialId eteritchium = createMaterial("eteritchium");
    public static final MaterialId blazing_steel = createMaterial("blazing_steel");
}