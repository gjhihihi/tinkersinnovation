package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import java.util.Iterator;

public class BacktrackModifier extends Modifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }
    private final ResourceLocation KEY = new ResourceLocation("tinkersinnovation", "backtrack_overslime");

    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (holder.tickCount % 200 == 0){
            ModDataNBT data = tool.getPersistentData();
            OverslimeModifier overslime = TinkerModifiers.overslime.get();
            if (data.getInt(KEY) > 0){
                if (data.getInt(KEY) > overslime.getShield(tool)){
                    if (RANDOM.nextFloat() > 0.2 * modifier.getLevel()){
                        overslime.setShield(tool, modifier, data.getInt(KEY));
                    }
                }
            }
            data.putInt(KEY, overslime.getShield(tool));
        }
    }
}
