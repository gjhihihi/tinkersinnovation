package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.contexts.BombExplodeContext;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.library.hooks.TinkersBombHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class DisintegratedModifier extends Modifier implements TinkersBombHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkersInnovationHooks.TINKER_BOMB);
    }

    @Override
    public void onTinkersBombExplosion(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, BombExplodeContext context) {
        context.setPieceCount(context.getPieceCount() * (int)Math.pow(2, modifier.getLevel()));
        context.setPieceDamage(context.getPieceDamage() * (float)Math.pow(0.5, modifier.getLevel()));
    }
}
