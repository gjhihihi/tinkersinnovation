package com.gjhi.tinkersinnovation.modifiers;

import com.james.tinkerscalibration.Utils;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.FinishHarvestModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.BiConsumer;

import static com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers.double_attack;
import static java.lang.Math.min;
import static slimeknights.tconstruct.tools.data.material.MaterialIds.manyullyn;

public class DoubleAttackModifier extends Modifier implements MeleeHitModifierHook, MeleeDamageModifierHook, ToolDamageModifierHook, BlockBreakModifierHook, FinishHarvestModifierHook, BreakSpeedModifierHook, AttributesModifierHook, InventoryTickModifierHook, ModifierRemovalHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.MELEE_HIT, TinkerHooks.MELEE_DAMAGE, TinkerHooks.TOOL_DAMAGE, TinkerHooks.BLOCK_BREAK, TinkerHooks.BREAK_SPEED, TinkerHooks.FINISH_HARVEST, TinkerHooks.INVENTORY_TICK, TinkerHooks.ATTRIBUTES, TinkerHooks.REMOVE);
    }
    private final ResourceLocation KEY = new ResourceLocation("tinkersinnovation", "double_attack");
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute,AttributeModifier> consumer) {
        if (slot == EquipmentSlot.MAINHAND) {
            if (tool.getPersistentData().getBoolean(KEY)) {
                consumer.accept(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(UUID.fromString("70cf6c97-410d-4bc4-a341-495aa6f2c994"), ForgeMod.ATTACK_RANGE.get().getDescriptionId(), 1, AttributeModifier.Operation.MULTIPLY_BASE));
                consumer.accept(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("0dd9a257-7513-4e84-ac78-1f576b89675d"), ForgeMod.REACH_DISTANCE.get().getDescriptionId(), 1, AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
    }
    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!world.isClientSide && holder.getUseItem() != stack && isSelected) {
            ModDataNBT persistentData = tool.getPersistentData();
            ToolStack offtool = getHeldTool(holder, InteractionHand.OFF_HAND);
            if (offtool != null) {
                if (offtool.getDefinition().equals(tool.getDefinition())) {
                    persistentData.putBoolean(KEY, true);
                }else {
                    persistentData.remove(KEY);
                }
            }else {
                persistentData.remove(KEY);
            }
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }
    @Override
    public int onDamageTool(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        int offDamage = 1;
        if (holder != null){
            ToolStack offtool = getHeldTool(holder, InteractionHand.OFF_HAND);
            if (offtool != null && !(offtool.equals(tool)) && offtool.getDefinition().equals(tool.getDefinition())) {
                List<ModifierEntry> modifierList = new ArrayList<>(offtool.getModifierList());
                modifierList.sort((o1, o2) -> o2.getModifier().getPriority() - o1.getModifier().getPriority());
                for (ModifierEntry mod : modifierList) {
                    if (!(mod.getId().equals(double_attack.getId())))
                        offDamage = mod.getHook(TinkerHooks.TOOL_DAMAGE).onDamageTool(offtool, mod, offDamage, holder);
                }
                offDamage += offtool.getDamage();
                offDamage = (int) min(offDamage, offtool.getStats().get(ToolStats.DURABILITY));
                if (!offtool.isUnbreakable() && holder instanceof Player player && !player.isCreative()){
                    offtool.setDamage(offDamage);
                }
            }
        }
        return amount;
    }
    @Override
    public float beforeMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        Player player = context.getPlayerAttacker();
        float offknockback = 0, offbaseknockback;
        if (player != null) {
            ToolStack offtool = getHeldTool(player, InteractionHand.OFF_HAND);
            if (offtool != null && !(offtool.equals(tool)) && offtool.getDefinition().equals(tool.getDefinition())) {
                offknockback = offbaseknockback = baseKnockback;
                List<ModifierEntry> modifierList = new ArrayList<>(offtool.getModifierList());
                modifierList.sort((o1, o2) -> o2.getModifier().getPriority() - o1.getModifier().getPriority());
                for (ModifierEntry mod : modifierList) {
                    if (!(mod.getId().equals(double_attack.getId())))
                        offknockback = mod.getHook(TinkerHooks.MELEE_HIT).beforeMeleeHit(offtool, mod, context, damage, offbaseknockback, offknockback);
                }
            }
        }
        return Math.max(knockback, offknockback);
    }

    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Player player = context.getPlayerAttacker();
        if (player != null) {
            ToolStack offtool = getHeldTool(player, InteractionHand.OFF_HAND);
            if (offtool != null && !(offtool.equals(tool)) && offtool.getDefinition().equals(tool.getDefinition())) {
                List<ModifierEntry> modifierList = new ArrayList<>(offtool.getModifierList());
                modifierList.sort((o1, o2) -> o2.getModifier().getPriority() - o1.getModifier().getPriority());
                for (ModifierEntry mod : modifierList) {
                    if (!(mod.getId().equals(double_attack.getId())))
                        mod.getHook(TinkerHooks.MELEE_HIT).afterMeleeHit(offtool, mod, context, damageDealt);
                }
            }
        }
    }
    @Override
    public void failedMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageAttempted) {
        Player player = context.getPlayerAttacker();
        if (player != null) {
            ToolStack offtool = getHeldTool(player, InteractionHand.OFF_HAND);
            if (offtool != null && !(offtool.equals(tool)) && offtool.getDefinition().equals(tool.getDefinition())) {
                List<ModifierEntry> modifierList = new ArrayList<>(offtool.getModifierList());
                modifierList.sort((o1, o2) -> o2.getModifier().getPriority() - o1.getModifier().getPriority());
                for (ModifierEntry mod : modifierList) {
                    if (!(mod.getId().equals(double_attack.getId())))
                        mod.getHook(TinkerHooks.MELEE_HIT).failedMeleeHit(offtool, mod, context, damageAttempted);
                }
            }
        }
    }
    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        Player player = context.getPlayerAttacker();
        float offdamage = 0, offbasedamage;
        if (player != null) {
            ToolStack offtool = getHeldTool(player, InteractionHand.OFF_HAND);
            if (offtool != null && !(offtool.equals(tool)) && offtool.getDefinition().equals(tool.getDefinition())) {
                offdamage = offbasedamage = offtool.getStats().get(ToolStats.ATTACK_DAMAGE);
                List<ModifierEntry> modifierList = new ArrayList<>(offtool.getModifierList());
                modifierList.sort((o1, o2) -> o2.getModifier().getPriority() - o1.getModifier().getPriority());
                for (ModifierEntry mod : modifierList) {
                    if (!(mod.getId().equals(double_attack.getId())))
                        offdamage = mod.getHook(TinkerHooks.MELEE_DAMAGE).getMeleeDamage(offtool, mod, context, offdamage, offbasedamage);
                }
            }
        }
        return damage + offdamage;
    }
    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            ToolStack offtool = getHeldTool(player, InteractionHand.OFF_HAND);
            if (offtool != null && !(offtool.equals(tool)) && offtool.getDefinition().equals(tool.getDefinition())) {
                List<ModifierEntry> modifierList = new ArrayList<>(offtool.getModifierList());
                modifierList.sort((o1, o2) -> o2.getModifier().getPriority() - o1.getModifier().getPriority());
                for (ModifierEntry mod : modifierList) {
                    if (!(mod.getId().equals(double_attack.getId())))
                        mod.getHook(TinkerHooks.BLOCK_BREAK).afterBlockBreak(offtool, mod, context);
                }
            }
        }
    }
    @Override
    public void onBreakSpeed(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, PlayerEvent.BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        // the speed is reduced when not on the ground, cancel out
        Player player = event.getPlayer();
        if (player != null) {
            ToolStack offtool = getHeldTool(player, InteractionHand.OFF_HAND);
            if (!(offtool.equals(tool)) && offtool.getDefinition().equals(tool.getDefinition())) {
                List<ModifierEntry> modifierList = new ArrayList<>(offtool.getModifierList());
                modifierList.sort((o1, o2) -> o2.getModifier().getPriority() - o1.getModifier().getPriority());
                for (ModifierEntry mod : modifierList) {
                    if (!(mod.getId().equals(double_attack.getId())))
                        mod.getHook(TinkerHooks.BREAK_SPEED).onBreakSpeed(offtool, mod, event, sideHit, isEffective, miningSpeedModifier);
                }
            }
        }
    }

    @Override
    public void finishHarvest(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            ToolStack offtool = getHeldTool(player, InteractionHand.OFF_HAND);
            if (offtool != null && !(offtool.equals(tool)) && offtool.getDefinition().equals(tool.getDefinition())) {
                List<ModifierEntry> modifierList = new ArrayList<>(offtool.getModifierList());
                modifierList.sort((o1, o2) -> o2.getModifier().getPriority() - o1.getModifier().getPriority());
                for (ModifierEntry mod : modifierList) {
                    if (!(mod.getId().equals(double_attack.getId())))
                        mod.getHook(TinkerHooks.FINISH_HARVEST).finishHarvest(offtool, mod, context);
                }
            }
        }
    }
}