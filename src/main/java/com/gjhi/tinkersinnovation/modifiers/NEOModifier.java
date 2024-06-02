package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.DamageTakenModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.BiConsumer;

public class NEOModifier extends Modifier implements AttributesModifierHook, DamageTakenModifierHook, ModifierRemovalHook, EquipmentChangeModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.DAMAGE_TAKEN, TinkerHooks.ATTRIBUTES, TinkerHooks.REMOVE, TinkerHooks.EQUIPMENT_CHANGE);
    }
    private final ResourceLocation KEY = new ResourceLocation("tinkersinnovation", "neo");
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (tool.hasTag(TinkerTags.Items.ARMOR) && slot != EquipmentSlot.MAINHAND && slot != EquipmentSlot.OFFHAND) {
            float boost = tool.getPersistentData().getFloat(KEY);
            if (boost > 0) {
                consumer.accept(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("03230c69-3614-4ca5-9ab8-8e3d5c51cab2"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), 5 * modifier.getLevel() * boost, AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }

    @Override
    public void onDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        ModDataNBT persistentData = tool.getPersistentData();
        if (tool.hasTag(TinkerTags.Items.ARMOR) && slotType != EquipmentSlot.MAINHAND && slotType != EquipmentSlot.OFFHAND)
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
