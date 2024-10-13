package com.gjhi.tinkersinnovation.library.modifiers;

import com.xiaoyue.tinkers_ingenuity.content.library.init.TIHooks;
import com.xiaoyue.tinkers_ingenuity.generic.Interface.curio.TinkerCurioHook;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import top.theillusivec4.curios.api.SlotContext;

public class WitherCurioModifier extends Modifier implements TinkerCurioHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        builder.addHook(this, TIHooks.TINKER_CURIO_HOOK);
    }
    @Override
    public void onCurioTick(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack stack) {
        entity.removeEffect(MobEffects.WITHER);
    }

    @Override
    public void onCurioToDamage(IToolStackView curio, LivingDamageEvent event, LivingEntity attacker, LivingEntity target, int level) {
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 50 * level));
    }
}
