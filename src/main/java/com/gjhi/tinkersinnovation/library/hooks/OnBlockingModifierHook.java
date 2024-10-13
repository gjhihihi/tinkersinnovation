package com.gjhi.tinkersinnovation.library.hooks;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;

public interface OnBlockingModifierHook {
    int onBlocking(IToolStackView tool, ModifierEntry modifier, LivingEntity blocker, ShieldBlockEvent event, int amount);

    record AllMerger(Collection<OnBlockingModifierHook> modules) implements OnBlockingModifierHook {
        @Override
        public int onBlocking(IToolStackView tool, ModifierEntry modifier, LivingEntity blocker, ShieldBlockEvent event, int amount) {
            for (OnBlockingModifierHook module : this.modules) {
                amount = module.onBlocking(tool, modifier, blocker, event, amount);
            }
            return amount;
        }
    }
}
