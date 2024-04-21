package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class RadianceModifier extends Modifier implements ProjectileHitModifierHook {
    @Override
    public int getPriority() {
        return 80;
    }
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder){
        hookBuilder.addHook(this, TinkerHooks.PROJECTILE_HIT);
    }
    @Override
    public @NotNull List<ItemStack> processLoot(@NotNull IToolStackView tool, int level, List<ItemStack> generatedLoot, @NotNull LootContext context) {
        if(RANDOM.nextFloat() < 0.05 *(1 + level))
            generatedLoot.addAll(generatedLoot);
        return generatedLoot;
    }
}
