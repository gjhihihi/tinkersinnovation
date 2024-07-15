package com.gjhi.tinkersinnovation.modifiers;

import com.gjhi.tinkersinnovation.modifiers.base.ChaosDamageSourcesBase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ChaosModifier extends Modifier implements MeleeDamageModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public int getPriority() {
        return 120;
    }

    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        float chaosDamage = damage * 0.1f * modifier.getLevel();
        if (target != null) {
            for (float i = Math.min(chaosDamage, 1); chaosDamage > 0; chaosDamage -= Math.min(chaosDamage, 1), i=Math.min(chaosDamage, 1)){
                int time = target.invulnerableTime;
                target.hurt(ChaosDamageSourcesBase.randomSource(),i);
                target.invulnerableTime = time;
            }
        }
        return damage;
    }
}

