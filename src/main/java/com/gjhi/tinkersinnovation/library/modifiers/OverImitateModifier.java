package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import java.util.List;

public class OverImitateModifier extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook, MeleeDamageModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT, ModifierHooks.MELEE_DAMAGE, ModifierHooks.TOOLTIP);
    }
    private final ResourceLocation KEY_ATTACK_DAMAGE = new ResourceLocation(TinkersInnovation.MOD_ID, "over_imitate_attack_damage");
    void modifyImitateDamage(ModDataNBT data, float factor){
        data.putFloat(KEY_ATTACK_DAMAGE, data.getFloat(KEY_ATTACK_DAMAGE) * factor);
    }
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        ModDataNBT data = tool.getPersistentData();
        if (target != null){
            AttributeInstance attribute_damage = target.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attribute_damage != null){
                data.putFloat(KEY_ATTACK_DAMAGE, (float) attribute_damage.getValue() * 0.2f * modifier.getLevel());
            }
        }
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null){
            int time = target.invulnerableTime;
            target.hurt(DamageSource.indirectMobAttack(projectile, attacker).setProjectile(), persistentData.getFloat(KEY_ATTACK_DAMAGE));
            target.invulnerableTime = time;
            AttributeInstance attribute = target.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attribute != null){
                persistentData.putFloat(KEY_ATTACK_DAMAGE, (float) attribute.getValue() * 0.2f * modifier.getLevel());
            }
        }
        return false;
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        return damage + tool.getPersistentData().getFloat(KEY_ATTACK_DAMAGE);
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("tooltip.tinkersinnovation.over_imitate_damage", tool.getPersistentData().getFloat(KEY_ATTACK_DAMAGE)));
    }
}
