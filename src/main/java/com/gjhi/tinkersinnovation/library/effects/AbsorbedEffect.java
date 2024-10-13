package com.gjhi.tinkersinnovation.library.effects;

import net.minecraft.world.effect.MobEffectCategory;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class AbsorbedEffect extends NoMilkEffect {
    public AbsorbedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFFFF, true);
    }
}
