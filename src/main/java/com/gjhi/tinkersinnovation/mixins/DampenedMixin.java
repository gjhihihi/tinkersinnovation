package com.gjhi.tinkersinnovation.mixins;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import net.minecraft.commands.CommandSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import static slimeknights.tconstruct.library.modifiers.Modifier.getHeldTool;

@Mixin(Entity.class)
public abstract class DampenedMixin{

    @Inject(at = @At("HEAD"), method = "dampensVibrations", cancellable = true)
    public void dampened(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof LivingEntity self) {
            int count = 0;
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ToolStack tool = getHeldTool(self, slot);
                if (tool != null && tool.hasTag(TinkerTags.Items.ARMOR))
                    for (ModifierEntry modifier: tool.getModifiers().getModifiers()){
                        if (modifier.getId().equals(TinkersInnovationModifiers.dampened.getId())){
                            count++;
                            break;
                        }
                    }
            }
            if (count >= 4) {
                cir.setReturnValue(true);
            }
        }
    }
}
