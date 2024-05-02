package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public class VitalityModifier extends Modifier implements ToolDamageModifierHook {
    @Override
    public int onDamageTool(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if(holder!=null){
            float health=holder.getMaxHealth()-holder.getHealth();
            if (health>modifier.getLevel()*amount){
                //if(tool.get)
                holder.setHealth(holder.getHealth()+modifier.getLevel()*amount);
                return modifier.getLevel()*amount;
            }else{
                holder.setHealth(holder.getMaxHealth());
                return (int) (amount+health+0.5);
            }
        }
        return amount;
    }
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.TOOL_DAMAGE);
    }
}
