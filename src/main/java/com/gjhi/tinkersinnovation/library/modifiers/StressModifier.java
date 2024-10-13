package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.register.TinkersInnovationSlots;
import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class StressModifier extends NoLevelsModifier implements InventoryTickModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.TOOLTIP);
    }

    private final ResourceLocation STRESS_KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "stress");
    public void setStress(IToolStackView tool, float value){
        tool.getPersistentData().putFloat(STRESS_KEY, Math.min(Math.max(value, 0), tool.getStats().get(TinkersInnovationToolStats.STRESS)));
    }
    public float getStress(IToolStackView tool){
        return tool.getPersistentData().contains(STRESS_KEY, Tag.TAG_FLOAT) ? tool.getPersistentData().getFloat(STRESS_KEY) : 0;
    }
    public void addStress(IToolStackView tool, float value){
        setStress(tool, getStress(tool) + value);
    }
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        float speed = holder.getSpeed();
        if (speed > 5 && this.getStress(tool) < tool.getStats().get(TinkersInnovationToolStats.STRESS)){
            this.addStress(tool, 0.01f * speed);
        }else if (speed < 3){
            this.addStress(tool, -0.1f);
        }
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("tooltip.tinkersinnovation.stress.amount", this.getStress(tool), tool.getStats().get(TinkersInnovationToolStats.STRESS)));
    }
}
