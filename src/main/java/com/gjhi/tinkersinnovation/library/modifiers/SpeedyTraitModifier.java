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

public class SpeedyTraitModifier extends Modifier implements AttributesModifierHook, ModifierRemovalHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE);
    }
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (TinkersInnovationUtils.isInArmorSlots(slot)) {
            consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("5aee711f-684a-45a9-b767-b2c052b06ad7"), Attributes.MOVEMENT_SPEED.getDescriptionId(), LHConfig.COMMON.speedy.get() * modifier.getLevel(), AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }

}
