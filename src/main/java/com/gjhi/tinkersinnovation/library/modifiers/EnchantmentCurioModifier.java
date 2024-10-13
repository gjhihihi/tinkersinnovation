package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.modifiers.base.EnchantmentEffectsBase;
import com.xiaoyue.tinkers_ingenuity.content.library.init.TIHooks;
import com.xiaoyue.tinkers_ingenuity.generic.Interface.curio.TinkerCurioHook;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class EnchantmentCurioModifier extends Modifier implements TinkerCurioHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TIHooks.TINKER_CURIO_HOOK);
    }

    @Override
    public void onCurioToDamage(IToolStackView curio, LivingDamageEvent event, LivingEntity attacker, LivingEntity target, int level) {
        for (MobEffect effect : EnchantmentEffectsBase.getBadEffectsByCopy()){
            if (RANDOM.nextFloat() < 0.1){
                target.addEffect(new MobEffectInstance(effect, 40 * level));
            }
        }
        for (MobEffect effect : EnchantmentEffectsBase.getGoodEffectsByCopy()){
            if (RANDOM.nextFloat() < 0.1){
                attacker.addEffect(new MobEffectInstance(effect, 40 * level));
            }
        }
    }

    @Override
    public void onCurioTakeDamage(IToolStackView curio, LivingDamageEvent event, LivingEntity entity, DamageSource source, int level) {
        LivingEntity target = event.getEntity();
        for (MobEffect effect : EnchantmentEffectsBase.getBadEffectsByCopy()){
            if (RANDOM.nextFloat() < 0.1){
                target.addEffect(new MobEffectInstance(effect, 40 * level));
            }
        }
        for (MobEffect effect : EnchantmentEffectsBase.getGoodEffectsByCopy()){
            if (RANDOM.nextFloat() < 0.1){
                entity.addEffect(new MobEffectInstance(effect, 40 * level));
            }
        }
    }
}
