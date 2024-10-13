package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import java.util.List;

public class SoulLoverModifier extends NoLevelsModifier implements MeleeHitModifierHook, MeleeDamageModifierHook, TooltipModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.MELEE_HIT, ModifierHooks.TOOLTIP, ModifierHooks.PROJECTILE_HIT);
    }

    private final ResourceLocation KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "soul_lover");
    public void setSoulLevel(IToolStackView tool, int value){
        tool.getPersistentData().putInt(KEY, Math.min(Math.max(value, 0), 5));
    }
    public int getSoulLevel(IToolStackView tool){
        return tool.getPersistentData().contains(KEY, Tag.TAG_INT) ? tool.getPersistentData().getInt(KEY) : 0;
    }
    public void addSoulLevel(IToolStackView tool, int value){
        setSoulLevel(tool, getSoulLevel(tool) + value);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity attacker = context.getAttacker();
        if (attacker.getHealth() <= attacker.getMaxHealth() - damageDealt && getSoulLevel(tool) < 5){
            attacker.heal(damageDealt / 2);
            addSoulLevel(tool, 1);
        }
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (!persistentData.contains(KEY, Tag.TAG_INT)) {
            persistentData.putInt(KEY, 0);
        }
        if (target != null && persistentData.getInt(KEY) == 5 && projectile instanceof AbstractArrow arrow) {
            arrow.setBaseDamage(arrow.getBaseDamage() * 1.5);
            persistentData.putInt(KEY, 0);
        }
        if (persistentData.getInt(KEY) < 5 && attacker != null && projectile instanceof AbstractArrow arrow) {
            float damageDealt = (float) arrow.getBaseDamage();
            if (attacker.getHealth() <= attacker.getMaxHealth() - damageDealt){
                attacker.heal(damageDealt / 2);
                persistentData.putInt(KEY, persistentData.getInt(KEY) + 1);
            }
        }
        return false;
    }

    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        if (target != null && getSoulLevel(tool) == 5) {
            damage *= 1.5f;
            setSoulLevel(tool, 0);
        }
        return damage;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (tooltipKey.equals(TooltipKey.SHIFT)) {
            tooltip.add(Component.translatable("tooltip.tinkersinnovation.soul_level", getSoulLevel(tool)));
        }
    }
}
