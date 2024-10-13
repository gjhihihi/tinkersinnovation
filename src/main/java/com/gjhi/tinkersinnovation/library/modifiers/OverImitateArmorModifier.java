package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.DamageDealtModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class OverImitateArmorModifier extends Modifier implements AttributesModifierHook, ModifierRemovalHook, DamageDealtModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.DAMAGE_DEALT, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE, ModifierHooks.TOOLTIP);
    }
    private final ResourceLocation KEY_ARMOR = new ResourceLocation(TinkersInnovation.MOD_ID, "over_imitate_armor");
    private final ResourceLocation KEY_TOUGHNESS = new ResourceLocation(TinkersInnovation.MOD_ID, "over_imitate_toughness");
    void modifyImitateArmor(ModDataNBT data, float factor){
        data.putFloat(KEY_ARMOR, data.getFloat(KEY_ARMOR) * factor);
    }
    void modifyImitateToughness(ModDataNBT data, float factor){
        data.putFloat(KEY_TOUGHNESS, data.getFloat(KEY_TOUGHNESS) * factor);
    }
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (TinkersInnovationUtils.isInArmorSlots(slot) || TinkersInnovationUtils.isShieldInHandSlots(tool, slot)) {
            float boost_armor = tool.getPersistentData().getFloat(KEY_ARMOR);
            float boost_toughness = tool.getPersistentData().getFloat(KEY_TOUGHNESS);
            if (boost_armor >= 0) {
                consumer.accept(Attributes.ARMOR, new AttributeModifier(UUID.fromString("116aab4f-49fb-4366-8bc1-dceba697fece"), Attributes.ARMOR.getDescriptionId(), 0.2 * modifier.getLevel() * boost_armor, AttributeModifier.Operation.ADDITION));
            }
            if (boost_toughness >= 0){
                consumer.accept(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("94e7c303-808f-4966-9c2b-6e678a0cb8bd"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), 0.2 * modifier.getLevel() * boost_toughness, AttributeModifier.Operation.ADDITION));
            }
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }
    public void onDamageDealt(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, LivingEntity target, DamageSource source, float amount, boolean isDirectDamage) {
        ModDataNBT data = tool.getPersistentData();
        AttributeInstance attribute_armor = target.getAttribute(Attributes.ARMOR);
        AttributeInstance attribute_toughness = target.getAttribute(Attributes.ARMOR_TOUGHNESS);
        if (attribute_armor != null){
            data.putFloat(KEY_ARMOR, (float) attribute_armor.getValue() * 0.2f * modifier.getLevel());
        }
        if (attribute_toughness != null){
            data.putFloat(KEY_TOUGHNESS, (float) attribute_toughness.getValue() * 0.2f * modifier.getLevel());
        }
    }
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @javax.annotation.Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("tooltip.tinkersinnovation.over_imitate_armor", tool.getPersistentData().getFloat(KEY_ARMOR)));
        tooltip.add(Component.translatable("tooltip.tinkersinnovation.over_imitate_toughness", tool.getPersistentData().getFloat(KEY_TOUGHNESS)));
    }
}
