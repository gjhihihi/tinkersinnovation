package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static java.lang.Math.pow;
import static slimeknights.tconstruct.tools.data.material.MaterialIds.manyullyn;

public class MountedStrikeModifier extends Modifier {
    @Override
    public float getEntityDamage(@NotNull IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        Player player = context.getPlayerAttacker();
        if (player != null && player.isPassenger())
            return (float) (damage * pow(1.25,level));
        return damage;
    }
}
