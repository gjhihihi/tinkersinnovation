package com.gjhi.tinkersinnovation.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;

public class VoidModifier extends Modifier implements ProjectileHitModifierHook {
    @Override
    public int getPriority() {
        return 120;
    }

}