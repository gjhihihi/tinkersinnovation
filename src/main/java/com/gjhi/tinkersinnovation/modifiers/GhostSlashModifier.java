package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.RequirementsModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.traits.melee.InsatiableModifier;
import slimeknights.tconstruct.tools.stats.ToolType;

import java.util.List;

public class GhostSlashModifier extends NoLevelsModifier implements RequirementsModifierHook {
    public GhostSlashModifier() {
        MinecraftForge.EVENT_BUS.addListener(this::leftClickEmpty);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.REQUIREMENTS);
    }

    @Nullable
    @Override
    public Component requirementsError(ModifierEntry entry) {
        return Component.translatable("recipe.tconstruct.modifier.ghost_slash");
    }
    @Override
    public @NotNull List<ModifierEntry> displayModifiers(ModifierEntry entry) {
        return List.of(new ModifierEntry(TinkerModifiers.insatiable.getId(),1));
    }

    private void leftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        ToolStack tool = ToolStack.from(event.getItemStack());
        if (tool.getModifierLevel(this) > 0){
            InsatiableModifier.applyEffect(event.getEntity(), ToolType.MELEE, 100, 1, 7);
            ToolDamageUtil.damage(tool, 1, event.getEntity(), event.getItemStack());
        }
    }
}
