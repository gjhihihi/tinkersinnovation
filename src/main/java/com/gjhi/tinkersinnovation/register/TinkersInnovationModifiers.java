package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.modifiers.*;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
    public static StaticModifier<OceanBlessingModifier> ocean_blessing = MODIFIERS.register("ocean_blessing", OceanBlessingModifier::new);
    public static StaticModifier<VoidModifier> _void = MODIFIERS.register("void", VoidModifier::new);
    public static StaticModifier<EnchantmentModifier> enchantment = MODIFIERS.register("enchantment", EnchantmentModifier::new);
    public static StaticModifier<VitalityModifier> vitality = MODIFIERS.register("vitality", VitalityModifier::new);
    public static StaticModifier<OmnipotentModifier> omnipotent = MODIFIERS.register("omnipotent", OmnipotentModifier::new);
    public static StaticModifier<BigHeartModifier> big_heart = MODIFIERS.register("big_heart", BigHeartModifier::new);
    public static StaticModifier<FastEatModifier> fast_eat = MODIFIERS.register("fast_eat", FastEatModifier::new);
    public static StaticModifier<ChromaticAberrationModifier> chromatic_aberration = MODIFIERS.register("chromatic_aberration", ChromaticAberrationModifier::new);
    public static StaticModifier<BerserkerModifier> berserker = MODIFIERS.register("berserker", BerserkerModifier::new);
    public static StaticModifier<MountedStrikeModifier> mounted_strike = MODIFIERS.register("mounted_strike", MountedStrikeModifier::new);
    public static StaticModifier<SuppressModifier> suppress = MODIFIERS.register("suppress", SuppressModifier::new);
    public static StaticModifier<TraderModifier> trader = MODIFIERS.register("trader", TraderModifier::new);
    public static StaticModifier<BIGSHOTModifier> BIGSHOT = MODIFIERS.register("big_shot", BIGSHOTModifier::new);
    public static StaticModifier<SlaughterModifier> slaughter = MODIFIERS.register("slaughter", SlaughterModifier::new);
}
