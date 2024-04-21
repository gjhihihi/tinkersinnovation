package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.modifiers.*;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
    public static StaticModifier<ShadowModifier> shadow = MODIFIERS.register("shadow", ShadowModifier::new);
    public static StaticModifier<RadianceModifier> radiance = MODIFIERS.register("radiance", RadianceModifier::new);
    public static StaticModifier<OceanBlessingModifier> ocean_blessing = MODIFIERS.register("ocean_blessing", OceanBlessingModifier::new);
    public static StaticModifier<VoidModifier> _void = MODIFIERS.register("void", VoidModifier::new);
    public static StaticModifier<EnchantmentModifier> enchantment = MODIFIERS.register("enchantment", EnchantmentModifier::new);
    public static StaticModifier<VitalityModifier> vitality = MODIFIERS.register("vitality", VitalityModifier::new);
    public static StaticModifier<OmnipotentModifier> omnipotent = MODIFIERS.register("omnipotent", OmnipotentModifier::new);
    public static StaticModifier<BigHeartModifier> big_heart = MODIFIERS.register("big_heart", BigHeartModifier::new);
}
