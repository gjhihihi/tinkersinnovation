package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.hooks.ModifyDamageSourceModifierHook;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHook;

public class TinkersInnovationHooks {
    public static void init(){
    }
    public static final ModuleHook<ModifyDamageSourceModifierHook> MODIFY_SOURCE = ModifierHooks.register(TinkersInnovation.getResource("modify_source"), ModifyDamageSourceModifierHook.class, ModifyDamageSourceModifierHook.AllMerger::new, (tool, modifier, entity, source)-> source);
}
