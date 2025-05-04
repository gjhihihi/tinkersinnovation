package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.xiaoyue.tingenuity_library.library.hook.curio.CurioBuilderHook;
import com.xiaoyue.tingenuity_library.library.logic.context.AttributeData;
import com.xiaoyue.tingenuity_library.register.LibraryHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class PoseiditeBlessingCurioModifier extends Modifier implements ModifierRemovalHook, CurioBuilderHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, LibraryHooks.CURIO_BUILDER, ModifierHooks.REMOVE);
    }
    private final ResourceLocation KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "poseidite_blessing_curio");
    @Override
    public void onCurioTick(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack stack){
        entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        if (entity.isInWaterOrBubble()) {
            curio.getPersistentData().putBoolean(KEY, true);
            entity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 20));
        }else {
            curio.getPersistentData().remove(KEY);
        }
    }

    @Override
    public void addCurioAttribute(IToolStackView curio, SlotContext context, LivingEntity entity, int level, AttributeData attr) {
        if (curio.getPersistentData().getBoolean(KEY)){
            attr.map().put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("076d0b94-36c3-401d-addc-1dcda3ac15db"), Attributes.ATTACK_SPEED.getDescriptionId(), 0.1 * level, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }
    @Override
    public Component onRemoved(IToolStackView curio, Modifier modifier) {
        curio.getPersistentData().remove(modifier.getId());
        return null;
    }
}
