package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class FallingAttackModifier extends NoLevelsModifier {
    @Override
    public float getEntityDamage(@NotNull IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage){
        Player player = context.getPlayerAttacker();
        if (player != null) {
            return damage + player.fallDistance * 3;
        }
        return damage;
    }
}