package com.gjhi.tinkersinnovation.library.modifiers;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;


public class NoKnockbackArmorModifier extends Modifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (TinkersInnovationUtils.isInArmorSlots(holder, stack) || TinkersInnovationUtils.isShieldInHandSlots(tool, holder, stack)){
            holder.addEffect(new MobEffectInstance(AMEffectRegistry.KNOCKBACK_RESISTANCE.get(), 20));
        }
    }
}
