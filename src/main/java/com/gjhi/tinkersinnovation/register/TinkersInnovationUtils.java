package com.gjhi.tinkersinnovation.register;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TinkersInnovationUtils {
    public static boolean isInArmorSlots(EquipmentSlot slot){
        return slot.getType().equals(EquipmentSlot.Type.ARMOR);
    }
    public static boolean isInHandSlots(EquipmentSlot slot){
        return slot.getType().equals(EquipmentSlot.Type.HAND);
    }
    public static boolean isInArmorSlots(LivingEntity entity, ItemStack item){
        for (ItemStack i : entity.getArmorSlots()){
            if (i.equals(item)){
                return true;
            }
        }
        return false;
    }
    public static boolean isInHandSlots(LivingEntity entity, ItemStack item){
        for (ItemStack i : entity.getHandSlots()){
            if (i.equals(item)){
                return true;
            }
        }
        return false;
    }
    public static void updateEffect(LivingEntity entity, MobEffect effect, int addLevel, int maxLevel, int time){
        MobEffectInstance mobeffect = entity.getEffect(effect);
        if (mobeffect != null){
            entity.addEffect(new MobEffectInstance(effect, time, Math.min(mobeffect.getAmplifier() + addLevel, maxLevel - 1)));
        }else {
            entity.addEffect(new MobEffectInstance(effect, time, Math.min(addLevel - 1, maxLevel - 1)));
        }
    }
}
