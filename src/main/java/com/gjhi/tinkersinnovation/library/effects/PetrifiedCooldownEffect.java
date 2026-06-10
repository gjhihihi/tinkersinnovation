package com.gjhi.tinkersinnovation.library.effects;

import net.minecraft.world.effect.MobEffectCategory;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

@Deprecated
public class PetrifiedCooldownEffect extends NoMilkEffect {
    public PetrifiedCooldownEffect() {
        super(MobEffectCategory.HARMFUL, 0xDADBD2, true);
    }
}
