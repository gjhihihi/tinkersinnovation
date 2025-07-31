package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import java.util.ArrayList;
import java.util.List;

public class OvertripModifier extends Modifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        //TinkersInnovation.tinkers_logger.info("start");
        if (!world.isClientSide && holder.tickCount % Math.max(40 / modifier.getLevel(), 1) == 0 && overslime.getShield(tool) > 0) {
            List<ItemStack> items = new ArrayList<>();
            if (holder instanceof Player player) {
                Inventory inventory = player.getInventory();
                items.addAll(inventory.items);
                items.addAll(inventory.armor);
                items.addAll(inventory.offhand);
            } else {
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    items.add(holder.getItemBySlot(slot));
                }
            }
            List<ItemStack> newItems = new ArrayList<>();
            for (ItemStack stack1 : items) {
                if (stack1.getItem() instanceof IModifiable) {
                    ToolStack tool1 = ToolStack.from(stack1);
                    if (tool1.getModifierLevel(TinkerModifiers.overslime.get()) > 0) {
                        if (overslime.getShield(tool1) < overslime.getShieldCapacity(tool1, modifier)){
                            if (tool1.getModifierLevel(this) <= 0)
                                newItems.add(stack1);
                        }
                    }
                }
            }
            ItemStack luckItem = TinkersInnovationUtils.getRandomInList(newItems);
            if (luckItem != null) {
                ToolStack tool2 = ToolStack.from(luckItem);
                //TinkersInnovation.tinkers_logger.info("tool:" + overslime.getShield(tool));
                //TinkersInnovation.tinkers_logger.info("tool2:" + overslime.getShield(tool2));
                overslime.addOverslime(tool2, modifier, 1);
                overslime.addOverslime(tool, modifier, -1);
                //TinkersInnovation.tinkers_logger.info("tool:" + overslime.getShield(tool));
                //TinkersInnovation.tinkers_logger.info("tool2:" + overslime.getShield(tool2));
            }
        }
        //TinkersInnovation.tinkers_logger.info("end");
    }
}
