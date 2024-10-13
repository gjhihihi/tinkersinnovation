package com.gjhi.tinkersinnovation.library.hooks;

import com.gjhi.tinkersinnovation.contexts.BombExplodeContext;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TinkersBombHook {
    default void onTinkersBombExplosion(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, BombExplodeContext context) {
    }
    default void afterTinkersBombExplode(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, final List<LivingEntity> targets) {
    }
    default void beforeBombPiecesHit(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, final List<LivingEntity> targets, Map<LivingEntity, Integer> hitted) {
    }
    default void afterBombPiecesHit(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, LivingEntity target, int hitcount) {
    }
    static void pieceHitting(Map<LivingEntity, Integer> hitted, final List<LivingEntity> targets, int count){
        for (int i = 0; i < count; i++) {
            LivingEntity entity = TinkersInnovationUtils.getRandomInList(targets);
            if (entity != null) {
                if (hitted.containsKey(entity)){
                    hitted.put(entity, hitted.get(entity) + 1);
                }else {
                    hitted.put(entity, 1);
                }
            }
        }
    }
    record AllMerger(Collection<TinkersBombHook> modules) implements TinkersBombHook {
        @Override
        public void onTinkersBombExplosion(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, BombExplodeContext context) {
            for (TinkersBombHook module : this.modules) {
                module.onTinkersBombExplosion(modifiers, persistentData, modifier, bomb, attacker, context);
            }
        }

        @Override
        public void afterTinkersBombExplode(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, List<LivingEntity> targets) {
            for (TinkersBombHook module : this.modules) {
                module.afterTinkersBombExplode(modifiers, persistentData, modifier, bomb, attacker, targets);
            }
        }

        @Override
        public void beforeBombPiecesHit(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, List<LivingEntity> targets, Map<LivingEntity, Integer> hitted) {
            for (TinkersBombHook module : this.modules) {
                module.beforeBombPiecesHit(modifiers, persistentData, modifier, bomb, attacker, targets, hitted);
            }
        }

        @Override
        public void afterBombPiecesHit(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, LivingEntity target, int hitcount) {
            for (TinkersBombHook module : this.modules) {
                module.afterBombPiecesHit(modifiers, persistentData, modifier, bomb, attacker, target, hitcount);
            }
        }
    }
}
