package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.modifiers.base.EnchantmentEffectsBase;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import javax.annotation.Nullable;
import java.util.List;


public class EnchantmentModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        int level = modifier.getLevel();
        LivingEntity player = context.getAttacker();
        LivingEntity target = context.getLivingTarget();
        if (target != null) {
            for (MobEffect effect : EnchantmentEffectsBase.getBadEffectsByCopy()){
                if (RANDOM.nextFloat() < 0.1){
                    TinkersInnovationUtils.updateEffect(target, effect, 1, 2 * level, 40 * level);
                }
            }
            for (MobEffect effect : EnchantmentEffectsBase.getGoodEffectsByCopy()){
                if (RANDOM.nextFloat() < 0.1){
                    TinkersInnovationUtils.updateEffect(player, effect, 1, 2 * level, 40 * level);
                }
            }
        }
    }
    @Override
    public void failedMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageAttempted) {
        LivingEntity player = context.getAttacker();
        LivingEntity target = context.getLivingTarget();
        if (target != null) {
            MobEffectInstance effect = new MobEffectInstance(MobEffects.CONFUSION, 1200 * modifier.getLevel() ,2);
            //effect.setCurativeItems(List.of());
            target.addEffect(effect);
        }
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @Nullable LivingEntity player, @Nullable LivingEntity target) {
        int level = modifier.getLevel();
        if (player != null && target != null) {
            for (MobEffect effect : EnchantmentEffectsBase.getBadEffectsByCopy()){
                if (RANDOM.nextFloat() < 0.1){
                    TinkersInnovationUtils.updateEffect(target, effect, 1, 2 * level, 40 * level);
                }
            }
            for (MobEffect effect : EnchantmentEffectsBase.getGoodEffectsByCopy()){
                if (RANDOM.nextFloat() < 0.1){
                    TinkersInnovationUtils.updateEffect(player, effect, 1, 2 * level, 40 * level);
                }
            }
        }
        return false;
    }
}
