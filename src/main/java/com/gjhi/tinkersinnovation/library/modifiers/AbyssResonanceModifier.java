package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.hooks.ModifyDamageSourceModifierHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.RequirementsModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.stats.ToolType;

import java.util.List;

public class AbyssResonanceModifier extends NoLevelsModifier implements ModifyDamageSourceModifierHook, RequirementsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkersInnovationHooks.MODIFY_SOURCE, ModifierHooks.REQUIREMENTS);
    }

    @Nullable
    @Override
    public Component requirementsError(ModifierEntry entry) {
        return Component.translatable("recipe.tconstruct.modifier.insatiable");
    }
    @Override
    public @NotNull List<ModifierEntry> displayModifiers(ModifierEntry entry) {
        return List.of(new ModifierEntry(TinkerModifiers.insatiable.getId(),1));
    }

    @Override
    public void modifyDamageSource(IToolStackView tool, ModifierEntry modifier, LivingEntity attacker, LivingEntity target, DamageSource source) {
        MobEffectInstance effect = attacker.getEffect(TinkerModifiers.insatiableEffect.get(ToolType.MELEE));
        if (effect != null) {
            if (effect.getAmplifier() >= 4){
                source.bypassMagic();
            }
        }
    }
}
