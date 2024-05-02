package com.gjhi.tinkersinnovation.modifiers;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.data.FloatMultiplier;
import slimeknights.tconstruct.library.modifiers.hook.KeybindInteractModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.capability.TinkerDataKeys;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;


public class TeleportModifier extends NoLevelsModifier implements KeybindInteractModifierHook, GeneralInteractionModifierHook {
    /*public TeleportModifier(){
        MinecraftForge.EVENT_BUS.addListener(this::teleport);
    }
    private void teleport(InputEvent.ClickInputEvent event) {
        InputConstants.Key key = event.
    }*/
    private static final ResourceLocation TP = TConstruct.getResource("teleport");
    @Override
    public boolean startInteract(@NotNull IToolStackView tool, ModifierEntry modifier, Player player, EquipmentSlot slot, TooltipKey keyModifier) {
        if (player.isShiftKeyDown()) {/*
            player.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
            if (player.level.isClientSide()) {
                player.getCapability(TinkerDataCapability.CAPABILITY).ifPresent(data -> data.computeIfAbsent(TinkerDataKeys.FOV_MODIFIER).set(TP, 0.1f));
            }
            return true;*/
            //if (keyModifier.compareTo())
        }
        return false;
    }
    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK) {
            player.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
            player.startUsingItem(hand);
            if (player.level.isClientSide) {
                player.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((data) -> {
                    ((FloatMultiplier)data.computeIfAbsent(TinkerDataKeys.FOV_MODIFIER)).set(TP, 0.1F);
                });
            }

            tool.getPersistentData().putBoolean(TP, true);
            return InteractionResult.CONSUME;
        } else {
            return InteractionResult.PASS;
        }
    }
}
