package com.gjhi.tinkersinnovation.library.modifiers;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class SunshineArmorModifier extends NoLevelsModifier implements InventoryTickModifierHook, DamageBlockModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.DAMAGE_BLOCK);
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        return source.equals(DamageSource.FLY_INTO_WALL);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (tool.hasTag(TinkerTags.Items.ARMOR)){
            if (TinkersInnovationUtils.isInArmorSlots(holder, stack)){
                holder.addEffect(new MobEffectInstance(AMEffectRegistry.SUNBIRD_BLESSING.get(), 20));
            }
        }
        if (tool.hasTag(TinkerTags.Items.SHIELDS)){
            if (TinkersInnovationUtils.isInHandSlots(holder, stack)){
                holder.addEffect(new MobEffectInstance(AMEffectRegistry.SUNBIRD_BLESSING.get(), 20));
            }
        }
    }
}
