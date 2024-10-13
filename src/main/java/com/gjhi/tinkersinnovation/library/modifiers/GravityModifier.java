package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import dev.xkmc.l2hostility.compat.curios.CurioCompat;
import dev.xkmc.l2hostility.init.data.LHConfig;
import dev.xkmc.l2hostility.init.registrate.LHEffects;
import dev.xkmc.l2hostility.init.registrate.LHItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Iterator;

public class GravityModifier extends Modifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        for (LivingEntity target: TinkersInnovationUtils.getLivingEntitiesInRange(holder, LHConfig.COMMON.range.get("gravity").get(), false)){
            if (CurioCompat.hasItem(target, LHItems.RING_REFLECTION.get()))continue;
            if (CurioCompat.hasItem(target, LHItems.ABRAHADABRA.get()))continue;
            target.addEffect(new MobEffectInstance(LHEffects.GRAVITY.get(), 20, modifier.getLevel() - 1));
        }
    }
}
