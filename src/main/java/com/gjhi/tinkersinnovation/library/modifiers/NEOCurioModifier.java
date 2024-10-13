package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.xiaoyue.tinkers_ingenuity.content.library.context.CurioAttributeContext;
import com.xiaoyue.tinkers_ingenuity.content.library.init.TIHooks;
import com.xiaoyue.tinkers_ingenuity.generic.Interface.curio.TinkerCurioHook;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class NEOCurioModifier extends NoLevelsModifier implements ModifierRemovalHook, TinkerCurioHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.REMOVE, TIHooks.TINKER_CURIO_HOOK);
    }
    private final ResourceLocation KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "neo_curio");


    @Override
    public void addCurioAttribute(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack stack, CurioAttributeContext attr) {
        if (curio.getPersistentData().getBoolean(KEY)) {
            attr.map().put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("3367bbc2-913d-4b6b-9229-6cc3ff6f4bc6"), Attributes.ARMOR_TOUGHNESS.getDescriptionId(), 0.5, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }

    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }

    @Override
    public void onCurioEquip(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack prevStack, ItemStack stack) {
        ModDataNBT persistentData = curio.getPersistentData();
        persistentData.putBoolean(KEY, true);
    }

    @Override
    public void onCurioUnequip(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack newStack, ItemStack stack) {
        ModDataNBT persistentData = curio.getPersistentData();
        persistentData.putBoolean(KEY, false);
    }
}
