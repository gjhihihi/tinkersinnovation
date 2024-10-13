package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.contexts.BombExplodeContext;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.library.hooks.TinkersBombHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class NoBlocksBreakModifier extends NoLevelsModifier implements TinkersBombHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkersInnovationHooks.TINKER_BOMB);
    }

    @Override
    public void onTinkersBombExplosion(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, BombExplodeContext context) {
        context.setType(Explosion.BlockInteraction.NONE);
    }
}
