package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.contexts.BombExplodeContext;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.library.hooks.TinkersBombHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.RequirementsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.List;

public class FiredExplosionModifier extends Modifier implements TinkersBombHook, RequirementsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkersInnovationHooks.TINKER_BOMB, ModifierHooks.REQUIREMENTS);
    }

    @Override
    public List<ModifierEntry> displayModifiers(ModifierEntry entry) {
        return List.of(new ModifierEntry(TinkerModifiers.fiery.getId(), entry.getLevel()));
    }

    @Override
    public @Nullable Component requirementsError(ModifierEntry entry) {
        return Component.translatable("recipe.tconstruct.modifier.fiery.each_level");
    }

    @Override
    public void onTinkersBombExplosion(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, BombExplodeContext context) {
        context.setFired(true);
    }

    @Override
    public void afterTinkersBombExplode(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, List<LivingEntity> targets) {
        for (LivingEntity target : targets){
            target.setSecondsOnFire(5 * modifier.getLevel());
        }
    }
}
