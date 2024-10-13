package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import dev.xkmc.l2hostility.init.data.LHConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.UUID;
import java.util.function.BiConsumer;

public class TankyTraitModifier extends Modifier implements AttributesModifierHook, ModifierRemovalHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE);
    }
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (TinkersInnovationUtils.isInArmorSlots(slot)) {
            consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("56512da5-7c4a-4905-b91f-6bf2928ca95a"), Attributes.MAX_HEALTH.getDescriptionId(), LHConfig.COMMON.tankHealth.get() * modifier.getLevel(), AttributeModifier.Operation.MULTIPLY_BASE));
            consumer.accept(Attributes.ARMOR, new AttributeModifier(UUID.fromString("181ca485-b2b9-4e29-9d0e-c6e3d7b1e871"), Attributes.ARMOR.getDescriptionId(), LHConfig.COMMON.tankArmor.get() * modifier.getLevel(), AttributeModifier.Operation.ADDITION));
            consumer.accept(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("8c0224b7-3ca6-439f-9e4c-60589bcbc48c"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), LHConfig.COMMON.tankTough.get() * modifier.getLevel(), AttributeModifier.Operation.ADDITION));
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }

}
