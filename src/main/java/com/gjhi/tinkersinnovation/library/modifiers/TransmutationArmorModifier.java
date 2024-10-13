package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.gjhi.tinkersinnovation.library.modifiers.TransmutationModifier.TRANSMUTATION_DAMAGE;

public class TransmutationArmorModifier extends Modifier implements InventoryTickModifierHook, OnAttackedModifierHook, ModifierRemovalHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.ON_ATTACKED, ModifierHooks.REMOVE);
    }

    private final ResourceLocation EXP_KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "transmutation_explode_armor");


    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        ModDataNBT data = tool.getPersistentData();
        if (!tool.isBroken()){
            data.putBoolean(EXP_KEY, true);
        }
        if (tool.isBroken() && data.getBoolean(EXP_KEY)){
            holder.hurt(TRANSMUTATION_DAMAGE, Integer.MAX_VALUE);
            holder.level.explode(null, holder.getX(), holder.getY(), holder.getZ(), 2, Explosion.BlockInteraction.DESTROY);
            data.putBoolean(EXP_KEY, false);
        }
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity wearer = context.getEntity();
        if (wearer instanceof Player player){
            player.giveExperiencePoints(2 * modifier.getLevel());
            ToolDamageUtil.damageAnimated(tool, modifier.getLevel(), player, slotType);
        }
    }

    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }
}
