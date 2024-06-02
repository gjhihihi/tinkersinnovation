package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.DurabilityShieldModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.IToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;

public class ShieldAmountModifier extends DurabilityShieldModifier implements InventoryTickModifierHook {
    @Override
    protected int getShieldCapacity(IToolStackView tool, int level) {
        return tool.getStats().get(ToolStats.BLOCK_AMOUNT).intValue() * 20;
    }
    public int getShieldAmount(IToolStackView tool){
        return this.getShield(tool);
    }
    public void setShieldAmount(IToolStackView tool, int amount){
        this.setShield(tool,0, amount);
    }
    public void addShieldAmount(IToolStackView tool, int amount){
        this.setShieldAmount(tool, amount + this.getShieldAmount(tool));
    }
    public int getCapacity(IToolStackView tool) {
        return this.getShieldCapacity(tool, 0);
    }
    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
        int shield = this.getShield(tool);
        if (shield > 0) {
            if (shield >= amount) {
                this.setShieldAmount(tool, shield - amount * 20);
            } else {
                this.setShieldAmount(tool, 0);
            }
        }
        return amount;
    }
    @Override
    public void onInventoryTick (IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){
        int shield = this.getShield(tool);
        if (shield > 0) {
            if (!(holder.getMainHandItem().is(tool.getItem())||holder.getOffhandItem().is(tool.getItem()))){
                this.addShieldAmount(tool, 1);
            }
        }else if (shield == 0){
            if (holder instanceof Player player){
                player.getCooldowns().addCooldown(tool.getItem(), 100);
            }
            this.addShieldAmount(tool, 1);
        }
    }
}
