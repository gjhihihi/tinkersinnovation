package com.gjhi.tinkersinnovation.library.effects;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class SoulDevouringEffect extends NoMilkEffect {
    public SoulDevouringEffect() {
        super(MobEffectCategory.HARMFUL, 0x2AFEFF, true);
        this.addAttributeModifier(Attributes.MAX_HEALTH, "c282b4ff-3631-4793-b55a-3cf82b2aa766", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
