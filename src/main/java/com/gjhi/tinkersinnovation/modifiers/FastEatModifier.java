package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class FastEatModifier extends Modifier implements MeleeHitModifierHook, BlockBreakModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.MELEE_HIT,TinkerHooks.BLOCK_BREAK);
    }
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        Player player = context.getPlayerAttacker();
        if (target != null && !target.isAlive() && player != null && target.getKillCredit() == player) {
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + modifier.getLevel());
        }
    }
    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + modifier.getLevel());
        }
    }
    @Override
    public @NotNull List<ItemStack> processLoot(@NotNull IToolStackView tool, int level, List<ItemStack> generatedLoot, @NotNull LootContext context) {
        generatedLoot.clear();
        return generatedLoot;
    }

}
