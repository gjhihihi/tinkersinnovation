package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.library.hooks.TinkersBombHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class LaceratingBombModifier extends Modifier implements TinkersBombHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkersInnovationHooks.TINKER_BOMB);
    }

    @Override
    public void afterBombPiecesHit(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, LivingEntity target, int hitcount) {
        TinkerModifiers.bleeding.get().apply(target, 200, modifier.getLevel() * hitcount - 1);
    }
}
