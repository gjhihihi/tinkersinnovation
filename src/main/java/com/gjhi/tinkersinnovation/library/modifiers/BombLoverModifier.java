package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.library.hooks.TinkersBombHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import java.util.List;
import java.util.Map;

public class BombLoverModifier extends NoLevelsModifier implements TinkersBombHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkersInnovationHooks.TINKER_BOMB);
    }
    @Override
    public void beforeBombPiecesHit(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, List<LivingEntity> targets, Map<LivingEntity, Integer> hitted) {
        int count = hitted.get(attacker);
        hitted.remove(attacker);
        TinkersBombHook.pieceHitting(hitted, targets, count);
    }
}
