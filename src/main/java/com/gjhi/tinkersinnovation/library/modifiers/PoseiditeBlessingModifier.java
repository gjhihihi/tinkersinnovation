package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
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

import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;

public class PoseiditeBlessingModifier extends NoLevelsModifier implements AttributesModifierHook,  ModifierRemovalHook,  InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE);
    }
    private final ResourceLocation KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "poseidite_blessing");
    @Override
    public void onInventoryTick (@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){
        holder.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        if (holder.isInWaterOrBubble()) {
            tool.getPersistentData().putBoolean(KEY, true);
            switch (Objects.requireNonNull(stack.getEquipmentSlot())){
                case HEAD,CHEST ->
                        holder.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 20));
                case FEET,LEGS ->
                        holder.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 20));
            }
        }else {
            tool.getPersistentData().putBoolean(KEY,false);
        }
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (tool.getPersistentData().getBoolean(KEY)){
            switch (slot){
                case HEAD -> {
                    consumer.accept(Attributes.ARMOR, new AttributeModifier(UUID.fromString("03b6013c-6a49-48a7-92a9-9cba8a8a78cb"), Attributes.ARMOR.getDescriptionId(), 4, AttributeModifier.Operation.ADDITION));
                    consumer.accept(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("d70caf99-afef-40c9-ad5d-5167829d982f"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), 2, AttributeModifier.Operation.ADDITION));
                    consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("0e6aa9d6-0eb3-4d2e-9737-1ffa6a9cb02d"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("334b7ee5-d05f-4c41-b70f-afef270388df"), ForgeMod.SWIM_SPEED.get().getDescriptionId(), 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
                }
                case CHEST -> {
                    consumer.accept(Attributes.ARMOR, new AttributeModifier(UUID.fromString("5c60cb1e-f1b5-411f-8772-1c778e3417b2"), Attributes.ARMOR.getDescriptionId(), 6, AttributeModifier.Operation.ADDITION));
                    consumer.accept(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("e6698b5e-78cc-47f0-b30d-f57d6f27a1d6"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), 3, AttributeModifier.Operation.ADDITION));
                    consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("801831f7-47c1-4a33-8c27-9c1e64de5a25"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("b6c53715-783a-490e-b163-1ddb37e6f481"), ForgeMod.SWIM_SPEED.get().getDescriptionId(), 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
                }
                case LEGS -> {
                    consumer.accept(Attributes.ARMOR, new AttributeModifier(UUID.fromString("5af95ebd-e8a7-4d66-a9d9-178f0673d214"), Attributes.ARMOR.getDescriptionId(), 6, AttributeModifier.Operation.ADDITION));
                    consumer.accept(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("ec9e3981-6106-458d-b7b5-6578b5060edc"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), 3, AttributeModifier.Operation.ADDITION));
                    consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("4a66c99c-bf89-4067-a28f-36fa1bb128ee"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("f5295365-8c66-4e01-857e-b85de6bc2515"), ForgeMod.SWIM_SPEED.get().getDescriptionId(), 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
                }
                case FEET -> {
                    consumer.accept(Attributes.ARMOR, new AttributeModifier(UUID.fromString("d75727a1-c1b7-42cc-a58f-c87928f5c336"), Attributes.ARMOR.getDescriptionId(), 4, AttributeModifier.Operation.ADDITION));
                    consumer.accept(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("92af545d-83b5-4595-bce9-806ff79e3878"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), 2, AttributeModifier.Operation.ADDITION));
                    consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("eed80e05-e680-4d41-ae3f-04c3ddea63d5"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("949d13a8-2501-44cb-a400-489a3c9fd291"), ForgeMod.SWIM_SPEED.get().getDescriptionId(), 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
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
