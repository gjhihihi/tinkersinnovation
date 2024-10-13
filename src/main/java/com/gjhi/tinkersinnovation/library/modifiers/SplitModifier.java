package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;
import java.util.List;

public class SplitModifier extends NoLevelsModifier implements ToolStatsModifierHook, InventoryTickModifierHook, ModifierRemovalHook, TooltipModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS, ModifierHooks.INVENTORY_TICK, ModifierHooks.REMOVE, ModifierHooks.TOOLTIP);
    }

    private final ResourceLocation KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "split");

    private int getMaxAnotherDurability(IToolStackView tool){
        return tool.getCurrentDurability();
    }

    private int getAnotherDurability(IToolStackView tool){
        return Math.min(tool.getPersistentData().contains(KEY, Tag.TAG_INT) ? tool.getPersistentData().getInt(KEY) : 0, getMaxAnotherDurability(tool));
    }

    private void setAnotherDurability(IToolStackView tool, int amount){
        tool.getPersistentData().putInt(KEY, Math.min(getMaxAnotherDurability(tool), Math.max(0, amount)));
    }

    private void addAnotherDurability(IToolStackView tool, int amount){
        setAnotherDurability(tool, getAnotherDurability(tool) + amount);
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.DURABILITY.multiplyAll(builder, 0.4);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (tool.isBroken() && getAnotherDurability(tool) > 0){
            int swap = tool.getCurrentDurability() - tool.getDamage();
            tool.setDamage(getMaxAnotherDurability(tool) - getAnotherDurability(tool));
            setAnotherDurability(tool, swap);
        }
        if (holder.tickCount % 200 == 0 && getAnotherDurability(tool) < getMaxAnotherDurability(tool)){
            addAnotherDurability(tool, 1);
        }
    }

    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("tooltip.tinkersinnovation.split_durability", getAnotherDurability(tool)));
    }
}
