package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.contexts.BombExplodeContext;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.library.hooks.TinkersBombHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BombModifier extends NoLevelsModifier implements ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    protected DamageSource BOMB_PIECE = new DamageSource(TConstruct.prefix("tinker_bomb_piece")).bypassMagic();

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (attacker != null && projectile instanceof EBomb bomb) {
            ToolStack tool = getHeldTool(attacker, attacker.getUsedItemHand());
            if (tool != null) {
                float bomb_radius = ConditionalStatModifierHook.getModifiedStat(tool, attacker, TinkersInnovationToolStats.BOMB_RADIUS);
                int piece_count = (int) ConditionalStatModifierHook.getModifiedStat(tool, attacker, TinkersInnovationToolStats.PIECE_COUNT);
                float piece_damage = ConditionalStatModifierHook.getModifiedStat(tool, attacker, TinkersInnovationToolStats.PIECE_DAMAGE);
                BombExplodeContext context = new BombExplodeContext(bomb_radius, piece_count, piece_damage, false, Explosion.BlockInteraction.BREAK);
                for (ModifierEntry mod : modifiers.getModifiers()){
                    mod.getModifier().getHook(TinkersInnovationHooks.TINKER_BOMB).onTinkersBombExplosion(modifiers, persistentData, mod, bomb, attacker, context);
                }
                bomb_radius = context.getRadius();
                piece_count = context.getPieceCount();
                piece_damage = context.getPieceDamage();
                bomb.level.explode(bomb, bomb.getX(), bomb.getY(), bomb.getZ(), bomb_radius, context.isFired(), context.getType());
                List<LivingEntity> entities = TinkersInnovationUtils.getLivingEntitiesInRange(bomb, bomb_radius, false);
                for (ModifierEntry mod : modifiers.getModifiers()){
                    mod.getModifier().getHook(TinkersInnovationHooks.TINKER_BOMB).afterTinkersBombExplode(modifiers, persistentData, mod, bomb, attacker, entities);
                }
                Map<LivingEntity, Integer> hitted = new HashMap<>();
                TinkersBombHook.pieceHitting(hitted, entities, piece_count);
                for (ModifierEntry mod : modifiers.getModifiers()){
                    mod.getModifier().getHook(TinkersInnovationHooks.TINKER_BOMB).beforeBombPiecesHit(modifiers, persistentData, mod, bomb, attacker, entities, hitted);
                }
                for (Map.Entry<LivingEntity, Integer> entity: hitted.entrySet()){
                    for (int i = 0; i < entity.getValue(); i++) {
                        entity.getKey().invulnerableTime = 0;
                        entity.getKey().hurt(BOMB_PIECE, piece_damage);
                    }
                    for (ModifierEntry mod : modifiers.getModifiers()){
                        mod.getModifier().getHook(TinkersInnovationHooks.TINKER_BOMB).afterBombPiecesHit(modifiers, persistentData, mod, bomb, attacker, entity.getKey(), entity.getValue());
                    }
                }
                bomb.discard();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onProjectileHitBlock(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, BlockHitResult hit, @Nullable LivingEntity attacker) {
        if (attacker != null && projectile instanceof EBomb bomb) {
            ToolStack tool = getHeldTool(attacker, attacker.getUsedItemHand());
            if (tool != null) {
                float bomb_radius = ConditionalStatModifierHook.getModifiedStat(tool, attacker, TinkersInnovationToolStats.BOMB_RADIUS);
                int piece_count = (int) ConditionalStatModifierHook.getModifiedStat(tool, attacker, TinkersInnovationToolStats.PIECE_COUNT);
                float piece_damage = ConditionalStatModifierHook.getModifiedStat(tool, attacker, TinkersInnovationToolStats.PIECE_DAMAGE);
                BombExplodeContext context = new BombExplodeContext(bomb_radius, piece_count, piece_damage, false, Explosion.BlockInteraction.BREAK);
                for (ModifierEntry mod : modifiers.getModifiers()){
                    mod.getModifier().getHook(TinkersInnovationHooks.TINKER_BOMB).onTinkersBombExplosion(modifiers, persistentData, mod, bomb, attacker, context);
                }
                bomb_radius = context.getRadius();
                piece_count = context.getPieceCount();
                piece_damage = context.getPieceDamage();
                bomb.level.explode(bomb, bomb.getX(), bomb.getY(), bomb.getZ(), bomb_radius, context.isFired(), context.getType());
                List<LivingEntity> entities = TinkersInnovationUtils.getLivingEntitiesInRange(bomb, bomb_radius, false);
                for (ModifierEntry mod : modifiers.getModifiers()){
                    mod.getModifier().getHook(TinkersInnovationHooks.TINKER_BOMB).afterTinkersBombExplode(modifiers, persistentData, mod, bomb, attacker, entities);
                }
                Map<LivingEntity, Integer> hitted = new HashMap<>();
                for (int i = 0; i < piece_count; i++) {
                    LivingEntity entity = TinkersInnovationUtils.getRandomInList(entities);
                    if (entity != null) {
                        if (hitted.containsKey(entity)){
                            hitted.put(entity, hitted.get(entity) + 1);
                        }else {
                            hitted.put(entity, 1);
                        }
                    }
                }
                for (ModifierEntry mod : modifiers.getModifiers()){
                    mod.getModifier().getHook(TinkersInnovationHooks.TINKER_BOMB).beforeBombPiecesHit(modifiers, persistentData, mod, bomb, attacker, entities, hitted);
                }
                for (Map.Entry<LivingEntity, Integer> entity: hitted.entrySet()){
                    for (int i = 0; i < entity.getValue(); i++) {
                        entity.getKey().invulnerableTime = 0;
                        entity.getKey().hurt(BOMB_PIECE, piece_damage);
                    }
                    for (ModifierEntry mod : modifiers.getModifiers()){
                        mod.getModifier().getHook(TinkersInnovationHooks.TINKER_BOMB).afterBombPiecesHit(modifiers, persistentData, mod, bomb, attacker, entity.getKey(), entity.getValue());
                    }
                }
                bomb.discard();
                return true;
            }
        }
        return false;
    }
}
