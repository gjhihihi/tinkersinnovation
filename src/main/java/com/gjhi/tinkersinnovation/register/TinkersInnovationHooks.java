package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.library.hooks.ModifyDamageSourceModifierHook;
import com.gjhi.tinkersinnovation.library.hooks.OnBlockingModifierHook;
import com.gjhi.tinkersinnovation.library.hooks.TinkersBombHook;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import java.util.List;

public class TinkersInnovationHooks {
    public static void init(){
    }
    public static final ModuleHook<ModifyDamageSourceModifierHook> MODIFY_SOURCE = ModifierHooks.register(TinkersInnovation.getResource("modify_source"), ModifyDamageSourceModifierHook.class, ModifyDamageSourceModifierHook.AllMerger::new, (tool, modifier, attacker, target, source)-> {
    });
    public static final ModuleHook<OnBlockingModifierHook> ON_BLOCKING = ModifierHooks.register(TinkersInnovation.getResource("on_blocking"), OnBlockingModifierHook.class, OnBlockingModifierHook.AllMerger::new, (tool, modifier, blocker, event, amount)-> amount);
    public static final ModuleHook<TinkersBombHook> TINKER_BOMB = ModifierHooks.register(TinkersInnovation.getResource("tinker_bomb"), TinkersBombHook.class, TinkersBombHook.AllMerger::new, new TinkersBombHook() {
    });
}
