package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.ArrayList;
import java.util.List;

public class TraderModifier extends Modifier implements ProcessLootModifierHook {
    public TraderModifier() {
    MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onExperienceDrop);
    MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::beforeBlockBreak);
}
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROCESS_LOOT);
    }
    private void beforeBlockBreak(BlockEvent.BreakEvent event) {
        // only support main hand block breaking currently
        int level = 0;
        ToolStack tool = getHeldTool(event.getPlayer(), InteractionHand.MAIN_HAND);
        if (tool != null) {
            level = tool.getModifierLevel(this);
        }
        if (level > 0) {
            event.setExpToDrop(0);
        }
    }
    private void onExperienceDrop(LivingExperienceDropEvent event) {
        int level = 0;
        ToolStack tool = getHeldTool(event.getAttackingPlayer(), InteractionHand.MAIN_HAND);
        if (tool != null) {
            level = tool.getModifierLevel(this);
        }
        if (level > 0) {
            event.setDroppedExperience(0);
        }
    }
    @Override
    public void processLoot(@NotNull IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, @NotNull LootContext context) {
        if(RANDOM.nextFloat() < 0.1 * modifier.getLevel()){
            List<ItemStack> list = new ArrayList<>(generatedLoot);
            generatedLoot.addAll(list);
        }
    }
}
