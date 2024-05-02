package com.gjhi.tinkersinnovation.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationTags;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static slimeknights.tconstruct.tools.data.material.MaterialIds.manyullyn;

public class SlaughterModifier extends Modifier implements MeleeDamageModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.MELEE_DAMAGE);
    }
    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity entity =context.getLivingTarget();
        if(entity != null && entity.getType().is(TinkersInnovationTags.ANIMALS)){
            damage *= 2;
        }
        return damage;
    }
}