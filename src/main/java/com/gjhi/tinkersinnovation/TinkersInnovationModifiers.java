package com.gjhi.tinkersinnovation;

import com.gjhi.tinkersinnovation.modifiers.*;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
    public static StaticModifier<ShadowModifier> shadow = MODIFIERS.register("shadow", ShadowModifier::new);
}
