package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import java.util.List;

public class TraderModifier extends Modifier implements VolatileDataModifierHook {
    public TraderModifier() {
    MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onExperienceDrop);
    MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::beforeBlockBreak);
}
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.VOLATILE_DATA);
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
    public @NotNull List<ItemStack> processLoot(@NotNull IToolStackView tool, int level, List<ItemStack> generatedLoot, @NotNull LootContext context) {
        if(RANDOM.nextFloat() < 0.1*level){
            generatedLoot.addAll(generatedLoot);
        }
        return generatedLoot;
    }
    @Override
    public void addVolatileData(@NotNull ToolRebuildContext context, @NotNull ModifierEntry modifier, @NotNull ModDataNBT volatileData) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        overslime.setFriend(volatileData);
    }
}
