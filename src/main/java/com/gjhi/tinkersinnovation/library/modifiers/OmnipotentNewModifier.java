package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovationConfig;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import com.gjhi.tinkersinnovation.register.TinkersInnovationSlots;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;


public class OmnipotentNewModifier extends Modifier implements VolatileDataModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.VOLATILE_DATA);
    }

    @Override
    public void addVolatileData(@NotNull IToolContext context, @NotNull ModifierEntry modifier, @NotNull ModDataNBT volatileData) {
        volatileData.addSlots(TinkersInnovationSlots.OMNIPOTENT, 2 * Math.min(TinkersInnovationConfig.Common.OmnipotentMaxLevel.get(), modifier.getLevel()));
    }
}
