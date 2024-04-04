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
        return amount*level*2;
    }
    @Override
    public float getRepairFactor(IToolStackView toolStack, int level, float factor) {
        return factor*level*1.5f;
    }
    @Override
    public int getDurabilityRGB(IToolStackView tool, int level) {
        return 0xB0C4DE;
    }
}
