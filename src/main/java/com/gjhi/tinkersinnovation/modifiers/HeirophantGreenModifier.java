package com.gjhi.tinkersinnovation.modifiers;

import dev.xkmc.l2complements.init.registrate.LCEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.DamageDealtModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.RequirementsModifierHook;
import slimeknights.tconstruct.library.modifiers.util.LazyModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

import java.util.List;

public class HeirophantGreenModifier extends Modifier implements DamageDealtModifierHook, OnAttackedModifierHook, RequirementsModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED, ModifierHooks.DAMAGE_DEALT, ModifierHooks.REQUIREMENTS);
    }
    @Nullable
    @Override
    public Component requirementsError(ModifierEntry entry) {
        return Component.translatable("recipe.tconstruct.modifier.heirophant_green");
    }

    @Override
    public @NotNull List<ModifierEntry> displayModifiers(ModifierEntry entry) {
        return List.of(new ModifierEntry(ModifierIds.emerald,1));
    }
    @Override
    public void onDamageDealt(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, @NotNull LivingEntity target, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity wearer = context.getEntity();
        if (wearer.hasEffect(LCEffects.EMERALD.get())){
            wearer.addEffect(new MobEffectInstance(LCEffects.EMERALD.get(), 100 * modifier.getLevel(), 1));
        }else{
            wearer.addEffect(new MobEffectInstance(LCEffects.EMERALD.get(), 100 * modifier.getLevel()));
        }
    }
    @Override
    public void onAttacked(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity wearer = context.getEntity();
        if (wearer.hasEffect(LCEffects.EMERALD.get())){
            wearer.addEffect(new MobEffectInstance(LCEffects.EMERALD.get(), 100 * modifier.getLevel(), 1));
        }else{
            wearer.addEffect(new MobEffectInstance(LCEffects.EMERALD.get(), 100 * modifier.getLevel()));
        }
    }
}
