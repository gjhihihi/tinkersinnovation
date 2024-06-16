package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.UUID;
import java.util.function.BiConsumer;

public class NEOModifier extends Modifier implements AttributesModifierHook, OnAttackedModifierHook, ModifierRemovalHook, EquipmentChangeModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE, ModifierHooks.EQUIPMENT_CHANGE);
    }
    private final ResourceLocation KEY = new ResourceLocation("tinkersinnovation", "neo");
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (tool.hasTag(TinkerTags.Items.ARMOR)) {
            float boost = tool.getPersistentData().getFloat(KEY);
            if (boost > 0) {
                consumer.accept(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("03230c69-3614-4ca5-9ab8-8e3d5c51cab2"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), 5 * modifier.getLevel() * boost, AttributeModifier.Operation.ADDITION));
            }
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        ModDataNBT persistentData = tool.getPersistentData();
        if (tool.hasTag(TinkerTags.Items.ARMOR))
            if ((double) tool.getDamage() / tool.getCurrentDurability() < 1){
            persistentData.putFloat(KEY, (float) tool.getDamage() / tool.getCurrentDurability());
        }
    }

    @Override
    public void onEquip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        ModDataNBT persistentData = tool.getPersistentData();
        if ((double) tool.getDamage() / tool.getCurrentDurability() < 1){
            persistentData.putFloat(KEY, (float) tool.getDamage() / tool.getCurrentDurability());
        }
    }

    @Override
    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        ModDataNBT persistentData = tool.getPersistentData();
        persistentData.remove(KEY);
    }
}
