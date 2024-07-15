package com.gjhi.tinkersinnovation.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.DurabilityShieldModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;

import java.util.List;

import static com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats.SHIELD_AMOUNT;

public class ShieldAmountModifier extends DurabilityShieldModifier implements InventoryTickModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.TOOLTIP);
    }

    @Override
    public int getShieldCapacity(IToolStackView tool, ModifierEntry modifier) {
        return tool.getStats().get(SHIELD_AMOUNT).intValue();
    }
    public int getShieldAmount(IToolStackView tool){
        return this.getShield(tool);
    }
    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        int shield = this.getShield(tool);
        if (holder != null && shield > 0 && holder.isBlocking()) {
            this.addShield(tool, modifier, -1);
        }
        return amount;
    }
    @Override
    public void onInventoryTick (IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){
        int shield = this.getShield(tool);
        if (shield > 0) {
            if (!(holder.getMainHandItem().is(tool.getItem())||holder.getOffhandItem().is(tool.getItem()))){
                if(holder.tickCount % 10 == 0)
                    this.addShield(tool, new ModifierEntry(this, 1), 1);
            }
        }else if (shield == 0){
            if (holder instanceof Player player){
                player.getCooldowns().addCooldown(tool.getItem(), 100);
                player.stopUsingItem();
            }
            this.addShield(tool, new ModifierEntry(this, 1), 1);
        }
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @org.jetbrains.annotations.Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("tool_tip.tinkersinnovation.shield_amount", this.getShield(tool), this.getShieldCapacity(tool, modifier)));
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        return true;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        return this.getShield(tool) > 0 ? 0xFFFFFF : -1;
    }
}
