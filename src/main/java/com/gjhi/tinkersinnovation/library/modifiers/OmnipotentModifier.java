package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.hooks.ModifyDamageSourceModifierHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import com.gjhi.tinkersinnovation.register.TinkersInnovationSlots;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.RepairFactorModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.DamageDealtModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.*;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

import static com.gjhi.tinkersinnovation.register.TinkersInnovationMaterials.*;
import static net.minecraft.world.level.Level.OVERWORLD;
import static slimeknights.tconstruct.tools.data.material.MaterialIds.*;

public class OmnipotentModifier extends Modifier implements VolatileDataModifierHook, MeleeDamageModifierHook, ToolStatsModifierHook, MeleeHitModifierHook, ProjectileHitModifierHook, ConditionalStatModifierHook, BreakSpeedModifierHook, ToolDamageModifierHook, OnAttackedModifierHook, ModifyDamageModifierHook, DamageDealtModifierHook, DamageBlockModifierHook, InventoryTickModifierHook, ModifyDamageSourceModifierHook, RepairFactorModifierHook, TooltipModifierHook, AttributesModifierHook {
    private static final String[] list = {
            //this mod
            polychrome_alloy.toString(),
            slimton.toString(),
            shulkerate.toString(),
            sculkium.toString(),
            eternium.toString(),
            decline.toString(),
            miracle.toString(),
            hostilium.toString(),
            clonate.toString(),
            //farseeing_alloy.toString(),
            sunsoul_alloy.toString(),
            //straddlite_alloy.toString(),
            //soul_bone.toString(),
            //hemolymph_bone.toString(),
            //tconstruct
            manyullyn.toString(),
            queensSlime.toString(),
            hepatizon.toString(),
            ancientHide.toString(),
            blazingBone.toString(),
            blazewood.toString(),
            enderslimeVine.toString(),
            //tinkers calibration
            "tinkerscalibration:mangobberslime",
            "tinkerscalibration:mandite",
            "tinkerscalibration:darkmatter",
            "tinkerscalibration:redmatter",
            "tinkerscalibration:emperorslime",
            "tinkerscalibration:netherite",
            "tinkerscalibration:bnetherite",
            "tinkerscalibration:fnetherite",
            "tinkerscalibration:gnetherite",
            "tinkerscalibration:wnetherite",
            "tinkerscalibration:snetherite",
            "tinkerscalibration:pnetherite",
            "tinkerscalibration:prnetherite",
            "tinkerscalibration:enetherite",
            "tinkerscalibration:fazelle",
            "tinkerscalibration:breashell",
            "tinkerscalibration:gobbernether",
            "tinkerscalibration:oraclium",
            "tinkerscalibration:soulgold",
            "tinkerscalibration:jazz",
            "tinkerscalibration:wither",
            "tinkerscalibration:gravity",
            "tinkerscalibration:lindsteel",
            //tinkers thinking
            "tinkers_thinking:stewium",
            //tinkers ingenuity
            "tinkers_ingenuity:bedrock_alloy_material",
            "tinkers_ingenuity:blood_binding_material",
            "tinkers_ingenuity:blood_steel_material",
            "tinkers_ingenuity:blue_sky_material",
            "tinkers_ingenuity:crocell_material",
            "tinkers_ingenuity:crystal_matrix_material",
            "tinkers_ingenuity:dye_fire_material",
            "tinkers_ingenuity:etherium_material",
            "tinkers_ingenuity:evil_material",
            "tinkers_ingenuity:fire_steel_material",
            "tinkers_ingenuity:gaia_material",
            "tinkers_ingenuity:glasya_material",
            "tinkers_ingenuity:gleiter_material",
            "tinkers_ingenuity:ice_steel_material",
            "tinkers_ingenuity:ignitium_material",
            "tinkers_ingenuity:infinity_material",
            "tinkers_ingenuity:knight_crystal_material",
            "tinkers_ingenuity:lighting_steel_material",
            "tinkers_ingenuity:neutronium_material",
            "tinkers_ingenuity:ocean_alloy_material",
            "tinkers_ingenuity:prince_slime_material",
            "tinkers_ingenuity:sea_dream_material",
            "tinkers_ingenuity:shine_alloy_material",
            "tinkers_ingenuity:shine_gold_material",
            "tinkers_ingenuity:simir_material",
            "tinkers_ingenuity:sunlit_material",
            "tinkers_ingenuity:teslin_alloy_material",
            "tinkers_ingenuity:twilight_material",
            "tinkers_ingenuity:xuan_ming_material",
            "tinkers_ingenuity:zesley_material",
            //"tinkers_ingenuity:star_alloy_material",
            //"tinkers_ingenuity:splendid_material"
            //"tinkers_ingenuity:dread_steel_material"
            //"tinkers_ingenuity:frost_alloy_material"
    };

    private String PATH = "tooltip.tinkersinnovation.omnipotent.material.";

    public OmnipotentModifier() {
        //MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
    }
/*
    private void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        if (player != null && player.getMainHandItem().is(TinkerTags.Items.MODIFIABLE)) {
            ToolStack tool = ToolStack.from(player.getMainHandItem());
            if (tool.getModifierLevel(this) > 0) {
            }
        }
    }*/

    @Override
    public int getPriority() {
        return 20;
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT, ModifierHooks.TOOL_STATS, ModifierHooks.VOLATILE_DATA, ModifierHooks.CONDITIONAL_STAT, ModifierHooks.BREAK_SPEED, ModifierHooks.TOOL_DAMAGE, ModifierHooks.ON_ATTACKED, ModifierHooks.MODIFY_DAMAGE, ModifierHooks.DAMAGE_DEALT, ModifierHooks.DAMAGE_BLOCK, ModifierHooks.INVENTORY_TICK, TinkersInnovationHooks.MODIFY_SOURCE, ModifierHooks.REPAIR_FACTOR, ModifierHooks.ATTRIBUTES);
    }

    @Override
    public float getRepairFactor(IToolStackView tool, ModifierEntry entry, float factor) {
        int partnum;
        if ((partnum = getMaterialCount(tool, "tinkerscalibration:lindsteel")) > 0) {
            factor += 0.25f * entry.getLevel() * partnum;
        };
        return factor;
    }

    @Override
    public void modifyDamageSource(IToolStackView tool, ModifierEntry modifier, LivingEntity attacker, LivingEntity target, DamageSource source) {
        if (getMaterialCount(tool, "tinkerscalibration:darkmatter", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED) > 0) {
            source.bypassEnchantments();
        }
        if (getMaterialCount(tool, "tinkerscalibration:redmatter", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED) > 0) {
            source.bypassMagic();
        }
    }

    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        LivingEntity target = context.getLivingTarget();
        Player player = context.getPlayerAttacker();
        int partnum;
        if (player != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:etherium_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (player.level.dimension().equals(OVERWORLD)) {
                knockback += partnum;
            }
        }
        if (player != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:sea_dream_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,partnum*100,modifier.getLevel()-1));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,partnum*100,modifier.getLevel()-1));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,partnum*100,modifier.getLevel()-1));
        }
        return knockback;
    }

    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        LivingEntity player = context.getAttacker();
        int partnum;
        if (target != null && (partnum = getMaterialCount(tool, blazingBone.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            target.setSecondsOnFire(5 * partnum * modifier.getLevel());
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:blood_steel_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (RANDOM.nextFloat() > 0.1 * partnum * modifier.getLevel())
                tool.setDamage(Math.max(0, tool.getDamage() - 1));
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkerscalibration:breashell", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20 * partnum));
            target.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40 * partnum));
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20 * partnum));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40 * partnum));
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:blue_sky_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (!player.isOnGround())
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20 * partnum));
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:shine_gold_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100 * partnum, modifier.getLevel() - 1));
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:simir_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100 * partnum, modifier.getLevel() - 1));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100 * partnum, modifier.getLevel() - 1));
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:teslin_alloy_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100 * partnum, modifier.getLevel() - 1));
        }
        if ((partnum = getMaterialCount(tool, "tinkerscalibration:soulgold", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100 * partnum, modifier.getLevel() - 1));
        }
        if ((partnum = getMaterialCount(tool, "tinkerscalibration:wither", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (RANDOM.nextFloat() < 0.1f * modifier.getLevel()) {
                player.invulnerableTime = 20 * partnum;
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:sunlit_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (player.level.isDay() && player.level.canSeeSky(player.blockPosition()) && !player.level.isRaining() && !player.level.isThundering()) {
                if (target != null) {
                    target.hurt(DamageSource.MAGIC, damageDealt * 0.1f);
                }
            }
        }
        if (target != null && getMaterialCount(tool, "tinkerscalibration:mandite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED) > 0) {
            target.invulnerableTime = 0;
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:crocell_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (target != null && damageDealt < 0.05 * partnum * modifier.getLevel() * target.getMaxHealth()) {
                player.heal(5);
            }
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:evil_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED )) > 0) {
            if (target instanceof Shulker || target instanceof EnderMan){
                target.kill();
            }
        }
        partnum = getMaterialCount(tool, "tinkers_ingenuity:fire_steel_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED);
        partnum += getMaterialCount(tool, "tinkers_ingenuity:ice_steel_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED);
        partnum += getMaterialCount(tool, "tinkers_ingenuity:lighting_steel_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED);
        if (target != null && partnum > 0) {
            target.addEffect(new MobEffectInstance(MobEffects.HARM));
        }
        if (target != null && (partnum = getMaterialCount(tool, sculkium.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            List<LivingEntity> entities = TinkersInnovationUtils.getLivingEntitiesInRange(target, modifier.getLevel() + 1, false);
            entities.remove(player);
            for (LivingEntity entity : entities){
                target.hurt(DamageSource.sonicBoom(entity), 2 * partnum);
            }
        }
        if (target != null && getMaterialCount(tool, sunsoul_alloy.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED) > 0) {
            target.setSecondsOnFire(3 + target.getRemainingFireTicks() / 20);
        }
        if ((partnum = getMaterialCount(tool, clonate.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            OverImitateModifier imitate = TinkersInnovationModifiers.over_imitate.get();
            imitate.modifyImitateDamage(tool.getPersistentData(), 1 + modifier.getLevel() * 0.5f);
        }
    }

    @Override
    public int onDamageTool(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, int amount, @org.jetbrains.annotations.Nullable LivingEntity player) {
        int partnum;
        if (player != null && (partnum = getMaterialCount(tool, "tinkerscalibration:fazelle")) > 0) {
            player.setSpeed(player.getSpeed() + player.getSpeed() * modifier.getLevel() * partnum * 0.1f);
        }
        if (player != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:blood_binding_material")) > 0) {
            if (RANDOM.nextFloat() < 0.1 * partnum * modifier.getLevel())
                player.heal(1);
        }
        if (player != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:prince_slime_material")) > 0) {
            OverslimeModifier overslime = TinkerModifiers.overslime.get();
            if (RANDOM.nextFloat() < 0.8 * ((double) overslime.getShield(tool) /overslime.getShield(tool)))
                overslime.addOverslime(tool, modifier, 2 * partnum);
        }
        if (player != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:neutronium_material")) > 0) {
            if (RANDOM.nextFloat() < 0.75)
                return 0;
        }
        return amount;
    }

    @Override
    public void addToolStats(@NotNull IToolContext context, @NotNull ModifierEntry modifier, @NotNull ModifierStatsBuilder builder) {
        int partnum;
        if ((partnum = getMaterialCount(context, manyullyn.toString())) > 0) {
            ToolStats.ACCURACY.add(builder,  0.15 * modifier.getLevel() * partnum);
        }
        if ((partnum = getMaterialCount(context, hepatizon.toString())) > 0) {
            ToolStats.DRAW_SPEED.multiply(builder, 1 + modifier.getLevel() * partnum * 0.12f);
            ToolStats.MINING_SPEED.multiply(builder, 1 + modifier.getLevel() * partnum * 0.12f);
            ToolStats.KNOCKBACK_RESISTANCE.multiply(builder, 1 + modifier.getLevel() * partnum * 0.12f);
            ToolStats.ARMOR_TOUGHNESS.multiply(builder, 1 + modifier.getLevel() * partnum * 0.12f);
        }
        if (getMaterialCount(context, ancientHide.toString()) > 0) {
            ToolStats.VELOCITY.add(builder, modifier.getLevel() * 0.5);
            ToolStats.MINING_SPEED.add(builder, modifier.getLevel() * 0.5);
            ToolStats.ATTACK_DAMAGE.add(builder, modifier.getLevel() * 1.5);
            ToolStats.ARMOR_TOUGHNESS.add(builder, modifier.getLevel() * 3);;
        }
        if (getMaterialCount(context, enderslimeVine.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED) > 0) {
            ToolStats.VELOCITY.multiply(builder, 1 + modifier.getLevel() * 0.15);
            ToolStats.MINING_SPEED.multiply(builder, 1 + modifier.getLevel() * 0.15);
        }
        if ((partnum = getMaterialCount(context)) > 0) {
            ToolStats.DURABILITY.add(builder, modifier.getLevel() * partnum * 100);
            ToolStats.ATTACK_DAMAGE.add(builder, modifier.getLevel() * partnum);
            ToolStats.PROJECTILE_DAMAGE.add(builder, modifier.getLevel() * partnum * 0.5);
            ToolStats.ARMOR.add(builder, modifier.getLevel() * partnum * 2);
            ToolStats.ARMOR_TOUGHNESS.add(builder, modifier.getLevel() * partnum);
        }
        if ((partnum = getMaterialCount(context, "tinkers_ingenuity:gaia_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            ToolStats.ATTACK_SPEED.multiply(builder,  1 + 0.25 * modifier.getLevel());
        }
        if ((partnum = getMaterialCount(context, "tinkers_ingenuity:glasya_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            ToolStats.ATTACK_DAMAGE.multiply(builder, 1 + 0.2 * partnum);
        }
        if ((partnum = getMaterialCount(context, "tinkers_ingenuity:gleiter_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            ToolStats.ATTACK_DAMAGE.multiply(builder, 1 + 0.2 * partnum);
        }
        if ((partnum = getMaterialCount(context, "tinkers_ingenuity:twilight_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            ToolStats.ATTACK_SPEED.add(builder, 0.5);
        }
        if ((partnum = getMaterialCount(context, queensSlime.toString())) > 0) {
            OverslimeModifier.OVERSLIME_STAT.add(builder, 200 * partnum * modifier.getLevel());
        }
        if ((partnum = getMaterialCount(context, "tinkerscalibration:emperorslime")) > 0) {
            OverslimeModifier.OVERSLIME_STAT.multiply(builder, 1 + 0.2 * modifier.getLevel() * partnum);
        }
        if ((partnum = getMaterialCount(context, "tinkerscalibration:mandite")) > 0) {
            ToolStats.DRAW_SPEED.multiply(builder,  1 + 0.15 * modifier.getLevel() * partnum);
        }
        if ((partnum = getMaterialCount(context, shulkerate.toString())) > 0) {
            ToolStats.DURABILITY.add(builder, 200 * modifier.getLevel() * partnum);
            ToolStats.ATTACK_DAMAGE.add(builder, modifier.getLevel() * partnum);
            ToolStats.VELOCITY.multiply(builder, 1 + 0.1 * modifier.getLevel() * partnum);
            ToolStats.PROJECTILE_DAMAGE.add(builder, 0.5 * modifier.getLevel() * partnum);
        }
        if ((partnum = getMaterialCount(context, sculkium.toString())) > 0) {
            ToolStats.VELOCITY.multiply(builder, 1 + 0.2 * modifier.getLevel() * partnum);
        }
        partnum = getMaterialCount(context, "tinkers_ingenuity:fire_steel_material");
        partnum += getMaterialCount(context, "tinkers_ingenuity:ice_steel_material");
        partnum += getMaterialCount(context, "tinkers_ingenuity:lighting_steel_material");
        if (partnum > 0) {
            ToolStats.ATTACK_DAMAGE.add(builder, modifier.getLevel() * partnum * 2);
            ToolStats.PROJECTILE_DAMAGE.add(builder, modifier.getLevel() * partnum);
            ToolStats.ARMOR.add(builder, modifier.getLevel() * partnum * 2);
        }
        if ((partnum = getMaterialCount(context, "tinkers_ingenuity:bedrock_alloy_material")) > 0) {
            ToolStats.DURABILITY.multiply(builder, 2);
            ToolStats.ATTACK_DAMAGE.multiply(builder, 1.2);
        }
    }

    @Override
    public float modifyStat(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        int partnum;
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        if ((partnum = getMaterialCount(tool, slimton.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            float current = (float) overslime.getShield(tool);
            if (stat == ToolStats.PROJECTILE_DAMAGE) {
                return (float) (baseValue + Math.sqrt(current) * modifier.getLevel() * partnum * 0.01 * tool.getMultiplier(ToolStats.PROJECTILE_DAMAGE));
            }
        }
        return baseValue;
    }

    @Override
    public void onBreakSpeed(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, PlayerEvent.BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        // the speed is reduced when not on the ground, cancel out
        Player player = event.getEntity();
        int partnum;
        partnum = getMaterialCount(tool, "tinkerscalibration:netherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        partnum += getMaterialCount(tool, "tinkerscalibration:bnetherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        partnum += getMaterialCount(tool, "tinkerscalibration:fnetherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        partnum += getMaterialCount(tool, "tinkerscalibration:gnetherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        partnum += getMaterialCount(tool, "tinkerscalibration:wnetherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        partnum += getMaterialCount(tool, "tinkerscalibration:snetherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        partnum += getMaterialCount(tool, "tinkerscalibration:pnetherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        partnum += getMaterialCount(tool, "tinkerscalibration:prnetherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        partnum += getMaterialCount(tool, "tinkerscalibration:enetherite", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST);
        if (partnum > 0 && player != null && player.getY() < 64) {
            event.setNewSpeed((float) (event.getNewSpeed() + event.getNewSpeed() * partnum * modifier.getLevel() * 0.01 * (64 - player.getY())));
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:etherium_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST)) > 0) {
            if (player != null && player.level.dimension().equals(OVERWORLD)) {
                event.setNewSpeed(event.getNewSpeed() + event.getNewSpeed() * 0.5f * partnum);
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:shine_alloy_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST)) > 0) {
            if (player != null && player.level.isDay()) {
                event.setNewSpeed(event.getNewSpeed() + event.getNewSpeed() * 0.1f * partnum * modifier.getLevel());
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:zesley_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST)) > 0) {
            if (player != null) {
                float num = (player.getMaxHealth() - player.getHealth()) / 2;
                event.setNewSpeed(event.getNewSpeed() + event.getNewSpeed() * Math.min( 0.75f, 0.075f * num) * partnum);
            }
        }
    }

    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        int partnum;
        LivingEntity target = context.getLivingTarget();
        Player player = context.getPlayerAttacker();
        if (target != null && (partnum = getMaterialCount(tool, manyullyn.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            damage += (target.getMaxHealth() - target.getHealth()) * (0.1f + 0.05f * modifier.getLevel() * partnum);
        }
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        if ((partnum = getMaterialCount(tool, slimton.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            float current = (float) overslime.getShield(tool);
            damage += (float) (Math.sqrt(current) * modifier.getLevel() * partnum * 0.01 * damage);
        }
        if (target != null && (partnum = getMaterialCount(tool, blazingBone.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (target.isOnFire())
                damage *= 1.2f;
        }
        if (player != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:knight_crystal_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            damage += (player.getMaxHealth() - player.getHealth()) * (0.25f * modifier.getLevel() * partnum);
        }
        if (player != null && target != null && (partnum = getMaterialCount(tool, "tinkerscalibration:gravity", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            damage += target.fallDistance * partnum;
            damage += player.fallDistance * partnum;
            player.resetFallDistance();
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkerscalibration:jazz", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            damage += damage * RANDOM.nextFloat();
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:crystal_matrix_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (tool.getDamage() * 2 > tool.getCurrentDurability())
                damage += damage * 0.2f * partnum;
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:ocean_alloy_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (target.isInWaterOrBubble()){
                damage += damage * 0.2f * partnum;
            }
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:dye_fire_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (target.isOnFire() || target.isInWaterRainOrBubble())
                damage += 3 * partnum;
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkers_ingenuity:ignitium_material", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (target.isOnFire()){
                damage += 4 * partnum * modifier.getLevel();
            }
        }
        if (target != null && (partnum = getMaterialCount(tool, "tinkerscalibration:oraclium", TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            for (MobEffectInstance instance : target.getActiveEffects()){
                if (instance.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)){
                    List<ItemStack> list = new ArrayList<>();
                    for (ItemStack stack : instance.getCurativeItems()) {
                        if (!stack.getItem().equals(Items.MILK_BUCKET)){
                            list.add(stack);
                        }
                    }
                    instance.setCurativeItems(list);
                }
            }
        }
        if (target != null && getMaterialCount(tool, "tinkerscalibration:mandite") > 0) {
            target.invulnerableTime = 0;
        }
        if ((partnum = getMaterialCount(tool, decline.toString(), TinkerTags.Items.MELEE, TinkerTags.Items.HARVEST, TinkerTags.Items.RANGED)) > 0) {
            if (target != null){
                MobEffectInstance effect = target.getEffect(MobEffects.WITHER);
                if (effect != null){
                    damage += 2 * partnum * modifier.getLevel() * (effect.getAmplifier() + 1);
                }
                if (target instanceof WitherSkeleton){
                    damage += 2 * partnum * modifier.getLevel();
                }
                if (target instanceof WitherBoss){
                    damage += 4 * partnum * modifier.getLevel();
                }
            }
        }
        return damage;
    }

    @Override
    public void addVolatileData(@NotNull IToolContext context, @NotNull ModifierEntry modifier, @NotNull ModDataNBT volatileData) {
        int partnum;
        if ((partnum = getMaterialCount(context, "tinkerscalibration:mangobberslime")) > 0) {
            volatileData.addSlots(SlotType.ABILITY, partnum * modifier.getLevel());
            volatileData.addSlots(SlotType.UPGRADE, partnum * modifier.getLevel());
            if (context.hasTag(TinkerTags.Items.ARMOR)) {
                volatileData.addSlots(SlotType.DEFENSE, partnum * modifier.getLevel());
            }
        }
        if (getMaterialCount(context, "tinkerscalibration:gobbernether") > 0) {
            volatileData.addSlots(SlotType.ABILITY, 1);
        }
        if ((partnum = getMaterialCount(context, eternium.toString())) > 0) {
            volatileData.addSlots(SlotType.UPGRADE, partnum);
        }
        if ((partnum = getMaterialCount(context, miracle.toString())) > 0) {
            volatileData.addSlots(TinkersInnovationSlots.HOSTILITY, partnum * modifier.getLevel());
        }
        if (getMaterialCount(context, "tinkers_ingenuity:infinity_material") > 0) {
            volatileData.addSlots(SlotType.ABILITY, 2);
        }
    }
    @Override
    public void onAttacked(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity wearer = context.getEntity();
        LivingEntity target = null;
        if (source.getEntity() instanceof LivingEntity entity)
            target = entity;
        int partnum;
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:blue_sky_material", TinkerTags.Items.ARMOR)) > 0) {
            wearer.resetFallDistance();
            if (target != null) {
                target.fallDistance *= 1.5f;
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:knight_crystal_material", TinkerTags.Items.ARMOR)) > 0) {
            //wearer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,200 * modifier.getLevel(), partnum - 1));
            wearer.setAbsorptionAmount(wearer.getAbsorptionAmount() + 4 * partnum);
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:simir_material", TinkerTags.Items.ARMOR)) > 0) {
            //wearer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, partnum - 1));
            wearer.setAbsorptionAmount(wearer.getAbsorptionAmount() + 4 * partnum);
        }
        if ((partnum = getMaterialCount(tool, blazewood.toString(), TinkerTags.Items.ARMOR)) > 0) {
            if (target != null) {
                target.setSecondsOnFire(5 * modifier.getLevel() * partnum);
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:sunlit_material", TinkerTags.Items.ARMOR)) > 0) {
            if (RANDOM.nextFloat() < 0.1 * partnum * modifier.getLevel()) {
                wearer.setSecondsOnFire(10);
                if (target != null) {
                    target.setSecondsOnFire(10);
                }
            }
        }
        if (target != null && getMaterialCount(tool, "tinkerscalibration:oraclium", TinkerTags.Items.ARMOR) > 0) {
            for (MobEffectInstance instance : target.getActiveEffects()){
                if (instance.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)){
                    List<ItemStack> list = new ArrayList<>();
                    for (ItemStack stack : instance.getCurativeItems()) {
                        if (!stack.getItem().equals(Items.MILK_BUCKET)){
                            list.add(stack);
                        }
                    }
                    instance.setCurativeItems(list);
                }
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:teslin_alloy_material", TinkerTags.Items.ARMOR)) > 0) {
            if (RANDOM.nextFloat() < 0.1 * partnum * modifier.getLevel()){
                tool.setDamage(tool.getDamage()-10);
            }
        }
        if (target != null && getMaterialCount(tool, sunsoul_alloy.toString(), TinkerTags.Items.ARMOR) > 0) {
            target.setSecondsOnFire(3 + target.getRemainingFireTicks() / 20);
        }
    }
    @Override
    public void onDamageDealt(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, @NotNull LivingEntity target, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity wearer = context.getEntity();
        int partnum;
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:crocell_material", TinkerTags.Items.ARMOR)) > 0) {
            if (amount < 0.05 * partnum * modifier.getLevel() * target.getMaxHealth()){
                wearer.heal(5);
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:shine_gold_material", TinkerTags.Items.ARMOR)) > 0) {
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100 * partnum, modifier.getLevel() - 1));
        }
        if ((partnum = getMaterialCount(tool, "tinkerscalibration:soulgold", TinkerTags.Items.ARMOR)) > 0) {
            wearer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100 * partnum, modifier.getLevel() - 1));
        }
        if ((partnum = getMaterialCount(tool, "tinkerscalibration:wither", TinkerTags.Items.ARMOR)) > 0) {
            if (RANDOM.nextFloat() < 0.1f * modifier.getLevel()) {
                wearer.invulnerableTime = 20 * partnum;
            }
        }
        if ((partnum = getMaterialCount(tool, clonate.toString(), TinkerTags.Items.ARMOR)) > 0) {
            OverImitateArmorModifier imitate = TinkersInnovationModifiers.over_imitate_armor.get();
            imitate.modifyImitateArmor(tool.getPersistentData(), 1 + modifier.getLevel() * 0.5f);
            imitate.modifyImitateToughness(tool.getPersistentData(), 1 + modifier.getLevel() * 0.5f);
        }
    }
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        LivingEntity wearer = context.getEntity();
        int partnum;
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:dye_fire_material", TinkerTags.Items.ARMOR)) > 0) {
            return source.isFire();
        }
        if ((partnum = getMaterialCount(tool, "tinkerscalibration:redmatter", TinkerTags.Items.ARMOR)) > 0) {
            return RANDOM.nextFloat() < 0.2;
        }else if ((partnum = getMaterialCount(tool, "tinkerscalibration:darkmatter", TinkerTags.Items.ARMOR)) > 0) {
            return RANDOM.nextFloat() < 0.1;
        }
        return false;
    }
    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        int partnum;
        LivingEntity wearer = context.getEntity();
        LivingEntity target = null;
        if (source.getEntity() instanceof LivingEntity entity)
            target = entity;
        partnum = getMaterialCount(tool, "tinkerscalibration:netherite", TinkerTags.Items.ARMOR);
        partnum += getMaterialCount(tool, "tinkerscalibration:bnetherite", TinkerTags.Items.ARMOR);
        partnum += getMaterialCount(tool, "tinkerscalibration:fnetherite", TinkerTags.Items.ARMOR);
        partnum += getMaterialCount(tool, "tinkerscalibration:gnetherite", TinkerTags.Items.ARMOR);
        partnum += getMaterialCount(tool, "tinkerscalibration:wnetherite", TinkerTags.Items.ARMOR);
        partnum += getMaterialCount(tool, "tinkerscalibration:snetherite", TinkerTags.Items.ARMOR);
        partnum += getMaterialCount(tool, "tinkerscalibration:pnetherite", TinkerTags.Items.ARMOR);
        partnum += getMaterialCount(tool, "tinkerscalibration:prnetherite", TinkerTags.Items.ARMOR);
        partnum += getMaterialCount(tool, "tinkerscalibration:enetherite", TinkerTags.Items.ARMOR);
        if (partnum > 0 && source.isFire()){
            amount /= 2;
        }
        if ((partnum = getMaterialCount(tool, "tinkersinnovation:slimton", TinkerTags.Items.ARMOR)) > 0) {
            if (wearer.getHealth() / wearer.getMaxHealth() < 0.2 * partnum){
                amount /= 2;
            }
        }
        if (target != null && (partnum = getMaterialCount(tool, manyullyn.toString(), TinkerTags.Items.ARMOR)) > 0) {
            amount -= (target.getMaxHealth() - target.getHealth()) * (0.1f + 0.05f * modifier.getLevel() * partnum);
        }
        if ((partnum = getMaterialCount(tool, "tinkerscalibration:jazz", TinkerTags.Items.ARMOR)) > 0) {
            if (amount / wearer.getMaxHealth() > 0.4 /(partnum * modifier.getLevel())){
                amount = wearer.getMaxHealth() * 0.4f /(partnum * modifier.getLevel());
            }
        }
        if (target != null && (partnum = getMaterialCount(tool, sculkium.toString(), TinkerTags.Items.ARMOR)) > 0) {
            if (target instanceof Warden)
                amount /= 2;
        }
        if (target != null && (partnum = getMaterialCount(tool, decline.toString(), TinkerTags.Items.ARMOR)) > 0) {
            if (target instanceof WitherBoss)
                amount /= 2;
        }
        return Math.max(amount, 0);
    }
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){
        int partnum;
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:ocean_alloy_material", TinkerTags.Items.ARMOR)) > 0) {
            if (holder.isInWater() && !holder.hasEffect(MobEffects.WATER_BREATHING)){
                holder.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20));
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:shine_alloy_material", TinkerTags.Items.ARMOR)) > 0) {
            if (world.isDay() && world.canSeeSky(holder.blockPosition()) && !world.isRaining() && !world.isThundering()){
                if (RANDOM.nextFloat() < 0.005){
                    holder.heal(partnum);
                }
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_ingenuity:xuan_ming_material", TinkerTags.Items.ARMOR)) > 0) {
            if (holder.getMaxHealth() == holder.getHealth()){
                if (RANDOM.nextFloat() < 0.005){
                    tool.setDamage(Math.max(0, tool.getDamage() - partnum));
                }
            }
        }
        if ((partnum = getMaterialCount(tool, "tinkers_thinking:stewium")) > 0) {
            if (RANDOM.nextFloat() < 0.001){
                OverslimeModifier overslime = TinkerModifiers.overslime.get();
                overslime.addOverslime(tool, modifier, 1);
            }
        }
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        int partnum;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (tooltipKey.equals(TooltipKey.SHIFT)) {
            if (getMaterialCount(tool) > 0){
                tooltip.add(Component.translatable(PATH + "default"));
            }
            if (getMaterialCount(tool, manyullyn.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tconstruct.manyullyn"));
            }
            if (getMaterialCount(tool, hepatizon.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tconstruct.hepatizon"));
            }
            if (getMaterialCount(tool, queensSlime.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tconstruct.queen_slime"));
            }
            if (getMaterialCount(tool, ancientHide.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tconstruct.ancient_hide"));
            }
            if (getMaterialCount(tool, blazingBone.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tconstruct.blazing_bone"));
            }
            if (getMaterialCount(tool, blazewood.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tconstruct.blazing_wood"));
            }
            if (getMaterialCount(tool, enderslimeVine.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tconstruct.enderslime_vine"));
            }
            if (getMaterialCount(tool, slimton.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tinkersinnovation.slimton"));
            }
            if (getMaterialCount(tool, shulkerate.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tinkersinnovation.shulkerate"));
            }
            if (getMaterialCount(tool, sculkium.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tinkersinnovation.sculkium"));
            }
            if (getMaterialCount(tool, eternium.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tinkersinnovation.eternium"));
            }
            if (getMaterialCount(tool, clonate.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tinkersinnovation.clonate"));
            }
            if (getMaterialCount(tool, decline.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tinkersinnovation.decline"));
            }
            if (getMaterialCount(tool, hostilium.toString()) > 0) {
                tooltip.add(Component.translatable(PATH + "tinkersinnovation.hostilium"));
            }
        }
    }

    private static int getMaterialCount(IToolStackView tool, String id) {
        return getMaterialCount(tool, id, TinkerTags.Items.MODIFIABLE);
    }

    private static int getMaterialCount(IToolContext tool, String id) {
        return getMaterialCount(tool, id, TinkerTags.Items.MODIFIABLE);
    }

    @SafeVarargs
    private static int getMaterialCount(IToolStackView tool, String id, TagKey<Item>... tags) {
        for (TagKey<Item> tag :tags) {
            if (tool.hasTag(tag)) {
                int level = 0;
                for (MaterialVariant material : tool.getMaterials().getList()){
                    if (material.getId().toString().equals(id)) {
                        level++;
                    }
                }
                return level;
            }
        }
        return 0;
    }

    @SafeVarargs
    private static int getMaterialCount(IToolContext tool, String id, TagKey<Item>... tags) {
        for (TagKey<Item> tag :tags) {
            if (tool.hasTag(tag)) {
                int level = 0;
                for (MaterialVariant material : tool.getMaterials().getList()){
                    if (material.getId().toString().equals(id)) {
                        level++;
                    }
                }
                return level;
            }
        }
        return 0;
    }

    private static int getMaterialCount(IToolStackView tool) {
        return getMaterialCount(tool, TinkerTags.Items.MODIFIABLE);
    }

    private static int getMaterialCount(IToolContext tool) {
        return getMaterialCount(tool, TinkerTags.Items.MODIFIABLE);
    }

    @SafeVarargs
    private static int getMaterialCount(IToolStackView tool, TagKey<Item>... tags) {
        int level = 0;
        for (TagKey<Item> tag :tags) {
            if (tool.hasTag(tag)) {
                for (MaterialVariant material : tool.getMaterials().getList()) {
                    if (material.get().getTier() >= 4 && notfind(material.getId().toString())) {
                        level++;
                    }
                }
                break;
            }
        }
        return level;
    }

    @SafeVarargs
    private static int getMaterialCount(IToolContext tool, TagKey<Item>... tags) {
        int level = 0;
        for (TagKey<Item> tag :tags) {
            if (tool.hasTag(tag)) {
                for (MaterialVariant material : tool.getMaterials().getList()) {
                    if (material.get().getTier() >= 4 && notfind(material.getId().toString())) {
                        level++;
                    }
                }
                break;
            }
        }
        return level;
    }

    private static boolean notfind(String name) {
        for (String s : OmnipotentModifier.list) {
            if (name.equals(s))
                return false;
        }
        return true;
    }

}
