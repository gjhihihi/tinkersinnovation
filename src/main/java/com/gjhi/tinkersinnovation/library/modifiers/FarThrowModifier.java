package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class FarThrowModifier extends Modifier implements ConditionalStatModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.CONDITIONAL_STAT);
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.VELOCITY) {
            baseValue *= 1 + 0.5f * modifier.getLevel();
        }else if (stat == ToolStats.PROJECTILE_DAMAGE) {
            baseValue *= 1 - 0.2f * modifier.getLevel();
        }else if (stat == TinkersInnovationToolStats.BOMB_RADIUS) {
            baseValue *= 1 - 0.1f * modifier.getLevel();
        }
        return baseValue;
    }
}
