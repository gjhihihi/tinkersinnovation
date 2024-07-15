package com.gjhi.tinkersinnovation.modifiers;

import dev.xkmc.l2hostility.init.data.LHConfig;
import dev.xkmc.l2hostility.init.registrate.LHEffects;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.*;

public class CorrosionModifier extends Modifier implements MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        if (target instanceof Player player){
            List<ItemStack> inv = new ArrayList<>();
            Inventory inventory = player.getInventory();
            inv.addAll(inventory.items);
            inv.addAll(inventory.armor);
            inv.addAll(inventory.offhand);
            List<ItemStack> tools = new ArrayList<>();
            Map<ItemStack, Integer> sametools = new HashMap<>();
            for (ItemStack item : inv) {
                if (item.isDamageableItem() || item.getItem() instanceof IModifiable) {
                    tools.add(item);
                }
            }
            if (!tools.isEmpty()) {
                for (int i = 0; i < modifier.getLevel(); i++) {
                    int amount = 0;
                    ItemStack item = tools.get(RANDOM.nextInt(tools.size()));
                    if (item.getItem() instanceof IModifiable) {
                        ToolStack itool = ToolStack.from(item);
                        if (sametools.containsKey(item)) {
                            target.invulnerableTime = 0;
                            target.hurt(new IndirectEntityDamageSource("corrosion", target, context.getAttacker()), (float) (damageDealt * LHConfig.COMMON.corrosionDamage.get() * modifier.getLevel()));
                        } else {
                            amount = (int) (itool.getDamage() * LHConfig.COMMON.corrosionDurability.get() * modifier.getLevel());
                            sametools.put(item, amount);
                        }
                        ToolDamageUtil.damage(itool, amount, target, item);
                    } else {
                        if (sametools.containsKey(item)) {
                            target.invulnerableTime = 0;
                            target.hurt(new IndirectEntityDamageSource("corrosion", target, context.getAttacker()), (float) (damageDealt * LHConfig.COMMON.corrosionDamage.get() * modifier.getLevel()));
                        } else {
                            amount = (int) (item.getDamageValue() * LHConfig.COMMON.corrosionDurability.get() * modifier.getLevel());
                            sametools.put(item, amount);
                        }
                        item.setDamageValue(item.getDamageValue() + amount);
                    }
                }
            }else {
                target.invulnerableTime = 0;
                target.hurt(new IndirectEntityDamageSource("corrosion", target, context.getAttacker()), (float) (damageDealt * LHConfig.COMMON.corrosionDamage.get() * modifier.getLevel() * modifier.getLevel()));
            }
        }
    }
}
