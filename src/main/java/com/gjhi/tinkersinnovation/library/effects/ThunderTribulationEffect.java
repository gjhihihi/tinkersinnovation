package com.gjhi.tinkersinnovation.library.effects;

import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class ThunderTribulationEffect extends NoMilkEffect {
    public ThunderTribulationEffect() {
        super(MobEffectCategory.HARMFUL, 0x6414C8, true);
    }

    @Override
    public boolean isDurationEffectTick(int tick, int amplifier) {
        return tick > 0 && tick % Math.max(40 / (amplifier + 1), 1) == 0;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity target, int amplifier) {
        TinkersInnovationUtils.spawnLightningBolt(target, MobSpawnType.MOB_SUMMONED, TinkersInnovationUtils.getLightningBoltDamage() * (amplifier + 1));
    }
}
