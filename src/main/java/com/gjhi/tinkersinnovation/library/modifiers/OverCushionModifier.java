package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.hooks.OnBlockingModifierHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.RequirementsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import java.util.List;

public class OverCushionModifier extends Modifier implements OnBlockingModifierHook, RequirementsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.REQUIREMENTS, TinkersInnovationHooks.ON_BLOCKING);
    }

    @Override
    public List<ModifierEntry> displayModifiers(ModifierEntry entry) {
        return List.of(new ModifierEntry(TinkerModifiers.overslime.get(), 1));
    }

    @Override
    public @Nullable Component requirementsError(ModifierEntry entry) {
        return Component.translatable("recipe.tconstruct.modifier.overslime");
    }

    @Override
    public int onBlocking(IToolStackView tool, ModifierEntry modifier, LivingEntity blocker, ShieldBlockEvent event, int amount) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        if (overslime.getShield(tool) >= 10 * modifier.getLevel()){
            overslime.addOverslime(tool, modifier, -10 * modifier.getLevel());
            amount -= (int) (amount * 0.3 * modifier.getLevel());
        }
        return amount;
    }
}
