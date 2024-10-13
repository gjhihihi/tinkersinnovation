package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.RequirementsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static java.lang.Math.pow;

public class MountedStrikeModifier extends Modifier implements MeleeDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }
    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        Player player = context.getPlayerAttacker();
        if (player != null && player.isPassenger())
            return (float) (damage * pow(1.75,modifier.getLevel()));
        return damage;
    }
}
