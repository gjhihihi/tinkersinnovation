package com.gjhi.tinkersinnovation;

import com.gjhi.tinkersinnovation.modifiers.*;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
    public static StaticModifier<ShadowModifier> shadow = MODIFIERS.register("shadow", ShadowModifier::new);
    public static StaticModifier<EnergyStateModifier> energy_state = MODIFIERS.register("energy_state", EnergyStateModifier::new);
    public static StaticModifier<OceanBlessingModifier> ocean_blessing = MODIFIERS.register("ocean_blessing", OceanBlessingModifier::new);
    public static StaticModifier<FallingAttackModifier> falling_attack = MODIFIERS.register("falling_attack", FallingAttackModifier::new);
    public static StaticModifier<VoidModifier> _void = MODIFIERS.register("void", VoidModifier::new);
}
