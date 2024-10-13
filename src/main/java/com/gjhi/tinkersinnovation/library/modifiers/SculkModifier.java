package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.UUID;
import java.util.function.BiConsumer;

public class SculkModifier extends NoLevelsModifier implements AttributesModifierHook, ModifierRemovalHook, InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE);
    }
    @Override
    public void onInventoryTick(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        holder.removeEffect(MobEffects.BLINDNESS);
        holder.removeEffect(MobEffects.DARKNESS);
        holder.removeEffect(MobEffects.DIG_SLOWDOWN);
        holder.removeEffect(MobEffects.CONFUSION);
        holder.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        holder.removeEffect(MobEffects.WEAKNESS);
    }
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (TinkersInnovationUtils.isInArmorSlots(slot)){
            switch (slot){
                case HEAD -> {
                    consumer.accept(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("e1febb59-6960-465a-a00f-c1b32b197405"), Attributes.ATTACK_SPEED.getDescriptionId(), 0.06, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("f85578f9-5954-40dd-a683-5143820babe8"), Attributes.MAX_HEALTH.getDescriptionId(), 4, AttributeModifier.Operation.ADDITION));
                }
                case CHEST -> {
                    consumer.accept(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("0e015770-4331-4988-8adb-25d1f199f737"), Attributes.ATTACK_SPEED.getDescriptionId(), 0.09, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("8cf4ca8a-f3b3-4ac8-a1c3-72d886dad20e"), Attributes.MAX_HEALTH.getDescriptionId(), 6, AttributeModifier.Operation.ADDITION));
                }
                case LEGS -> {
                    consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("558bc33b-be37-4ff6-88de-85565ec3b0a7"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("4aa25a4b-0337-48fe-a21c-879fc3d26caa"), Attributes.MAX_HEALTH.getDescriptionId(), 6, AttributeModifier.Operation.ADDITION));
                }
                case FEET -> {
                    consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("ecd3dc54-2b70-461a-bc41-2ecd30e245e6"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("55407ada-f46f-4421-a9b9-eece8941a19f"), Attributes.MAX_HEALTH.getDescriptionId(), 4, AttributeModifier.Operation.ADDITION));
                }
            }
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }

}
