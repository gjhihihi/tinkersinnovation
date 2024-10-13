package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;

public class MoisturizingModifier extends Modifier implements ToolDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_DAMAGE);
    }

    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (holder != null && holder.isInWaterRainOrBubble()){
            holder.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100 * modifier.getLevel()));
            if (RANDOM.nextFloat() < 0.2) {
                holder.heal(modifier.getLevel());
            }
        }
        return amount;
    }
}
