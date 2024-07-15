package com.gjhi.tinkersinnovation.hooks;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;

public interface ModifyDamageSourceModifierHook {
    DamageSource modifyDamageSource(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, DamageSource source);
    record AllMerger(Collection<ModifyDamageSourceModifierHook> modules) implements ModifyDamageSourceModifierHook {
        @Override
        public DamageSource modifyDamageSource(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, DamageSource source) {
            for (ModifyDamageSourceModifierHook module : this.modules) {
                source = module.modifyDamageSource(tool, modifier, entity, source);
            }
            return source;
        }
    }
}
