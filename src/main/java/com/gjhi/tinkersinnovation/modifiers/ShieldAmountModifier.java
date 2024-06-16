package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.DurabilityShieldModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;

public class ShieldAmountModifier extends DurabilityShieldModifier implements InventoryTickModifierHook {
    @Override
    public int getShieldCapacity(IToolStackView tool, ModifierEntry modifier) {
        return tool.getStats().get(ToolStats.BLOCK_AMOUNT).intValue() * 20;
    }
    public int getShieldAmount(IToolStackView tool){
        return this.getShield(tool);
    }
    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        /*int shield = this.getShield(tool);
        if (shield > 0) {
            if (shield >= amount) {
                this.setShield(tool, shield - amount * 20);
            } else {
                this.setShieldAmount(tool, 0);
            }
        }*/
        return amount;
    }
    @Override
    public void onInventoryTick (IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){
        /*int shield = this.getShield(tool);
        if (shield > 0) {
            if (!(holder.getMainHandItem().is(tool.getItem())||holder.getOffhandItem().is(tool.getItem()))){
                this.addShieldAmount(tool, 1);
            }
        }else if (shield == 0){
            if (holder instanceof Player player){
                player.getCooldowns().addCooldown(tool.getItem(), 100);
            }
            this.addShieldAmount(tool, 1);
        }*/
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        return null;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        return 0;
    }
}
