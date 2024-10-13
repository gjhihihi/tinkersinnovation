package com.gjhi.tinkersinnovation.library.hooks;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;

public interface ModifyDamageSourceModifierHook {
    void modifyDamageSource(IToolStackView tool, ModifierEntry modifier, LivingEntity attacker, LivingEntity target, DamageSource source);
    record AllMerger(Collection<ModifyDamageSourceModifierHook> modules) implements ModifyDamageSourceModifierHook {
        @Override
        public void modifyDamageSource(IToolStackView tool, ModifierEntry modifier, LivingEntity attacker, LivingEntity target, DamageSource source) {
            for (ModifyDamageSourceModifierHook module : this.modules) {
                module.modifyDamageSource(tool, modifier, attacker, target, source);
            }
        }
    }
}
