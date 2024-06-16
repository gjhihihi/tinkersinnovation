package com.gjhi.tinkersinnovation.mixins;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import static slimeknights.tconstruct.library.modifiers.Modifier.getHeldTool;

@Mixin(LivingEntity.class)
public abstract class HealthFixMixin {
    @Shadow public abstract float getHealth();

    @Shadow public abstract boolean isAlive();

    @Inject(at = @At("HEAD"), method = "setHealth", cancellable = true)
    public void onHealth(float health, CallbackInfo ci){
        LivingEntity entity = (LivingEntity) (Object) this;
        if (this.getHealth() < health && !this.isAlive()){
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ToolStack tool = getHeldTool(entity, slot);
                if (tool != null)
                    for (ModifierEntry modifier: tool.getModifiers().getModifiers()){
                        if (modifier.getId().equals(TinkersInnovationModifiers.health_fixing.getId())){
                            while (tool.getDamage() > 0){
                                tool.setDamage((int) Math.max(0, tool.getDamage() - Math.pow(2, modifier.getLevel() - 1)));
                                --health;
                                if (health - this.getHealth() <= 0)ci.cancel();
                            }
                        }
                    }
            }
            if (health - this.getHealth() <= 0)ci.cancel();
        }
    }
}
