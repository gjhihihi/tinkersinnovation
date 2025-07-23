package com.gjhi.tinkersinnovation.library.effects;

import net.minecraft.world.effect.MobEffectCategory;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class CalibrationEffect extends NoMilkEffect {
    public CalibrationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFFFF, true);
    }
}
