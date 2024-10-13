package com.gjhi.tinkersinnovation.library.modifiers;

import com.xiaoyue.tinkers_ingenuity.content.library.init.TIToolStats;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;

public class WaterCoolModifier extends NoLevelsModifier implements ConditionalStatModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.CONDITIONAL_STAT);
    }

    @Override
    public float modifyStat(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (living.isInWaterOrBubble()) {
            if (stat == TIToolStats.COOLDOWN) {
                return 0.5f * baseValue;
            }
            if (stat == TIToolStats.LASER_RANGE) {
                return 2 * baseValue;
            }
        }
        return baseValue;
    }
}
