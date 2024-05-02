package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import javax.annotation.Nullable;
import java.util.ArrayList;

import static com.gjhi.tinkersinnovation.register.TinkersInnovationMaterials.*;
//import static com.james.tinkerscalibration.Utils.*;
import static slimeknights.tconstruct.tools.data.material.MaterialIds.*;

public class OmnipotentModifier extends Modifier implements VolatileDataModifierHook, MeleeDamageModifierHook, ToolStatsModifierHook, MeleeHitModifierHook, ProjectileHitModifierHook {
    private static final String[] list = {
            //this mod
            polychrome_alloy.toString(),
            slimton.toString(),
            //tconstruct
            manyullyn.toString(),
            queensSlime.toString(),
            hepatizon.toString(),
            //tinkerscalibration
            "tinkerscalibration:mangobberslime",
            "tinkerscalibration:mandite",
            "tinkerscalibration:darkmatter",
            "tinkerscalibration:redmatter",
            "tinkerscalibration:emperorslime",
            "tinkerscalibration:netherite"
    };

    @Override
    public int getPriority() {
        return 1;
    }
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.MELEE_DAMAGE, TinkerHooks.MELEE_HIT, TinkerHooks.PROJECTILE_HIT, TinkerHooks.TOOL_STATS, TinkerHooks.VOLATILE_DATA);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if(target != null && comp(tool,"tinkerscalibration:redmatter")>0){
            target.setHealth(target.getHealth()*0.8f);
        }else if(target != null && comp(tool,"tinkerscalibration:darkmatter")>0){
            target.setHealth(target.getHealth()*0.9f);
        }
        if (target != null && comp(tool,"tinkerscalibration:mandite") > 0) {
            target.invulnerableTime = 0;
        }
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        boolean result = false;
        for (ModifierEntry entry : modifiers.getModifiers()) {
            if (entry.getId().toString().equals("tinkerscalibration:bloodthirsty"))
                result = true;
        }
        if (target != null && result && projectile instanceof AbstractArrow arrow) {
            target.invulnerableTime = 0;
        }
        return false;
    }

    @Override
    public void addToolStats(@NotNull ToolRebuildContext context, @NotNull ModifierEntry modifier, @NotNull ModifierStatsBuilder builder) {
        int partnum;
        if ((partnum = comp(context, hepatizon.toString())) > 0) {
            if (context.getBaseStats().hasStat(ToolStats.VELOCITY))
                ToolStats.VELOCITY.add(builder, context.getBaseStats().get(ToolStats.VELOCITY) * modifier.getLevel() * partnum * 0.07f);
            if (context.getBaseStats().hasStat(ToolStats.MINING_SPEED))
                ToolStats.MINING_SPEED.add(builder, context.getBaseStats().get(ToolStats.VELOCITY) * modifier.getLevel() * partnum * 0.07f);
        }
        if ((partnum = comp(context)) > 0) {
            if (context.getBaseStats().hasStat(ToolStats.DURABILITY))
                ToolStats.DURABILITY.add(builder, modifier.getLevel() * partnum * 50);
            if (context.getBaseStats().hasStat(ToolStats.ATTACK_DAMAGE))
                ToolStats.ATTACK_DAMAGE.add(builder, modifier.getLevel() * partnum * 2);
        }
    }

    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        int partnum;
        LivingEntity target = context.getLivingTarget();
        if ((partnum = comp(tool, manyullyn.toString())) > 0) {
            if (target != null)
                damage += (target.getMaxHealth() - target.getHealth()) * (0.05f + 0.05f * modifier.getLevel() * partnum);
        }
        return damage;
    }

    @Override
    public void addVolatileData(@NotNull ToolRebuildContext context, @NotNull ModifierEntry modifier, @NotNull ModDataNBT volatileData) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        overslime.setFriend(volatileData);
        int partnum;
        if ((partnum = comp(context, "tinkerscalibration:mangobberslime")) > 0) {
            volatileData.addSlots(SlotType.ABILITY, partnum);
            volatileData.addSlots(SlotType.UPGRADE, partnum);
        }
        if ((partnum = comp(context, queensSlime.toString())) > 0) {
            overslime.addCapacity(volatileData, 200 * partnum * modifier.getLevel());
        }
        if ((partnum = comp(context, "tinkerscalibration:emperorslime")) > 0) {
            overslime.addCapacity(volatileData, overslime.getCapacity(volatileData)/2);
        }
    }

    private static int comp(IToolStackView tool, String id) {
        int level = 0;
        for (int i = 1; i <= tool.getMaterials().size(); i++) {
            if (tool.getMaterial(i).getId().toString().equals(id)) {
                level++;
            }
        }
        return level;
    }

    private static int comp(ToolRebuildContext tool, String id) {
        int level = 0;
        for (int i = 1; i <= tool.getMaterials().size(); i++) {
            if (tool.getMaterial(i).getId().toString().equals(id)) {
                level++;
            }
        }
        return level;
    }

    private static int comp(IToolStackView tool) {
        int level = 0;
        for (int i = 1; i <= tool.getMaterials().size(); i++) {
            if (tool.getMaterial(i).get().getTier() >= 4 && notfind(tool.getMaterial(i).getId().toString())) {
                level++;
            }
        }
        return level;
    }

    private static int comp(ToolRebuildContext tool) {
        int level = 0;
        for (int i = 1; i <= tool.getMaterials().size(); i++) {
            if (tool.getMaterial(i).get().getTier() >= 4 && notfind(tool.getMaterial(i).getId().toString())) {
                level++;
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
