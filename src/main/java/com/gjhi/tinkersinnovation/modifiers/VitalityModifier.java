package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public class VitalityModifier extends Modifier  {
    @Override
    public int onDamageTool(@NotNull IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
        if(holder!=null){
            float health=holder.getMaxHealth()-holder.getHealth();
            if (health>level*amount){
                //if(tool.get)
                holder.setHealth(holder.getHealth()+level*amount);
                return level*amount;
            }else{
                holder.setHealth(holder.getMaxHealth());
                return (int) (amount+health+0.5);
            }
        }
        return amount;
    }
}
