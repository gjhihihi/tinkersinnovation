package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;

public class EnergyStateModifier extends Modifier {
    @Override
    public int getPriority() {
    return 155;
}
    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
        return (int) (amount*1.5);
    }
    @Override
    public float getRepairFactor(IToolStackView toolStack, int level, float factor) {
        return (float) (factor*1.5);
    }
}
