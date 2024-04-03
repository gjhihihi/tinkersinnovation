package com.gjhi.tinkersinnovation.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ColorfulModifer extends Modifier {
    @Override
    public int getPriority() {
        return 100;
    }
    @Override
    public float beforeEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        return knockback;
    }
}
