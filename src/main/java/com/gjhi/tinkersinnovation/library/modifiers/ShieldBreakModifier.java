package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import com.gjhi.tinkersinnovation.register.TinkersInnovationTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class ShieldBreakModifier extends Modifier implements MeleeHitModifierHook {
    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        LivingEntity target = context.getLivingTarget();
        if (target != null){
            if (target.isBlocking()){
                if (target.getUseItem().getItem() instanceof IModifiable){
                    ToolStack shield = ToolStack.from(target.getUseItem());
                    if (shield.getModifierLevel(TinkersInnovationModifiers.shield_amount.getId()) > 0){
                        ShieldAmountModifier shield_amount = TinkersInnovationModifiers.shield_amount.get();
                        shield_amount.addShieldAmount(shield, modifier, -40 * modifier.getLevel());
                    }else {
                        if (RANDOM.nextFloat() < 0.2 * modifier.getLevel()){
                            if (target instanceof Player player){
                                player.getCooldowns().addCooldown(target.getUseItem().getItem(), 100);
                            }
                            target.stopUsingItem();
                        }
                    }
                }else {
                    if (RANDOM.nextFloat() < 0.2 * modifier.getLevel()){
                        if (target instanceof Player player){
                            player.getCooldowns().addCooldown(target.getUseItem().getItem(), 100);
                        }
                        target.stopUsingItem();
                    }
                }
            }
        }
        return knockback;
    }
}
