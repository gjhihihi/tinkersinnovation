package com.gjhi.tinkersinnovation.library.modifiers;

import com.xiaoyue.tingenuity_library.library.hook.curio.CurioBuilderHook;
import com.xiaoyue.tingenuity_library.register.LibraryHooks;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import top.theillusivec4.curios.api.SlotContext;

public class VitalityCurioModifier extends Modifier implements CurioBuilderHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        builder.addHook(this, LibraryHooks.CURIO_BUILDER);
    }
    @Override
    public void onCurioTick(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack stack) {
        if (!entity.level.isClientSide && entity.tickCount % 200 == 0){
            if (entity.getHealth() != entity.getMaxHealth()){
                entity.heal(level);
            }
        }
        entity.removeEffect(MobEffects.WITHER);
        entity.removeEffect(MobEffects.HUNGER);
        entity.removeEffect(MobEffects.POISON);
    }
}
