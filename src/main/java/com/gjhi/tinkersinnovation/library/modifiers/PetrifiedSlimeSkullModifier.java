package com.gjhi.tinkersinnovation.library.modifiers;

import com.github.alexthe666.iceandfire.datagen.tags.IafEntityTags;
import com.github.alexthe666.iceandfire.entity.EntityStoneStatue;
import com.github.alexthe666.iceandfire.entity.util.DragonUtils;
import com.github.alexthe666.iceandfire.entity.util.IBlacklistedFromStatues;
import com.github.alexthe666.iceandfire.misc.IafDamageRegistry;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import com.gjhi.tinkersinnovation.register.TinkersInnovationEffects;
import com.google.common.base.Predicate;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.KeybindInteractModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.Optional;

public class PetrifiedSlimeSkullModifier extends NoLevelsModifier implements KeybindInteractModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.ARMOR_INTERACT);
    }

    @Override
    public boolean startInteract(IToolStackView tool, ModifierEntry modifier, Player player, EquipmentSlot slot, TooltipKey keyModifier) {
        //if (player.getCooldowns().isOnCooldown(tool.getItem()))return false;
        if (player.hasEffect(TinkersInnovationEffects.petrifiedCooldownEffect.get()))return false;
        double dist = 32;
        Level worldIn = player.level;
        Vec3 Vector3d = player.getEyePosition(1.0F);
        Vec3 Vector3d1 = player.getViewVector(1.0F);
        Vec3 Vector3d2 = Vector3d.add(Vector3d1.x * dist, Vector3d1.y * dist, Vector3d1.z * dist);
        Entity pointedEntity = null;
        List<Entity> list = worldIn.getEntities(player, player.getBoundingBox().expandTowards(Vector3d1.x * dist, Vector3d1.y * dist, Vector3d1.z * dist).inflate(1.0D, 1.0D, 1.0D), (Predicate<Entity>) entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                boolean isImmune = livingEntity instanceof IBlacklistedFromStatues blacklisted && !blacklisted.canBeTurnedToStone() || entity.getType().is(IafEntityTags.IMMUNE_TO_GORGON_STONE) || livingEntity.hasEffect(MobEffects.BLINDNESS);
                return !isImmune && entity.isPickable() && !livingEntity.isDeadOrDying() && (entity instanceof Player || DragonUtils.isAlive(livingEntity));
            }
            return false;
        });
        double d2 = dist;
        for (Entity entity1 : list) {
            AABB axisalignedbb = entity1.getBoundingBox().inflate(entity1.getPickRadius());
            Optional<Vec3> optional = axisalignedbb.clip(Vector3d, Vector3d2);
            if (axisalignedbb.contains(Vector3d)) {
                if (d2 >= 0.0D) {
                    d2 = 0.0D;
                }
            } else if (optional.isPresent()) {
                double d3 = Vector3d.distanceTo(optional.get());
                if (d3 < d2 || d2 == 0.0D) {
                    if (entity1.getRootVehicle() == player.getRootVehicle() && !player.canRiderInteract()) {
                        if (d2 == 0.0D) {
                            pointedEntity = entity1;
                        }
                    } else {
                        pointedEntity = entity1;
                        d2 = d3;
                    }
                }
            }
        }
        if (pointedEntity != null) {
            if (pointedEntity instanceof LivingEntity livingEntity) {
                boolean wasSuccessful = true;

                if (pointedEntity instanceof Player) {
                    wasSuccessful = pointedEntity.hurt(IafDamageRegistry.causeGorgonDamage(pointedEntity), Integer.MAX_VALUE);
                } else {
                    if (!worldIn.isClientSide)
                        pointedEntity.remove(Entity.RemovalReason.KILLED);
                }
                if (wasSuccessful) {
                    pointedEntity.playSound(IafSoundRegistry.TURN_STONE, 1, 1);
                    EntityStoneStatue statue = EntityStoneStatue.buildStatueEntity(livingEntity);
                    statue.absMoveTo(pointedEntity.getX(), pointedEntity.getY(), pointedEntity.getZ(), pointedEntity.getYRot(), pointedEntity.getXRot());
                    statue.yBodyRot = pointedEntity.getYRot();
                    if (!worldIn.isClientSide) {
                        worldIn.addFreshEntity(statue);
                    }
                    ToolDamageUtil.damageAnimated(tool, 50, player, slot);
                    //player.getCooldowns().addCooldown(tool.getItem(), 600);
                    player.addEffect(new MobEffectInstance(TinkersInnovationEffects.petrifiedCooldownEffect.get(), 600));
                }
            }
        }
        return true;
    }
}
