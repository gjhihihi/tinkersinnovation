package com.gjhi.tinkersinnovation.library.effects;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class NotHungryEffect extends NoMilkEffect {
    public NotHungryEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xA65700, true);
    }

    @Override
    public boolean isDurationEffectTick(int tick, int level) {
        return tick > 0;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int level) {
        if (living instanceof Player player){
            player.getFoodData().setExhaustion(0);
        }
    }
}
