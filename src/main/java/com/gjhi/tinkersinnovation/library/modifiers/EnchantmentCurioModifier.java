package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.library.modifiers.base.EnchantmentEffectsBase;
import com.xiaoyue.tingenuity_library.library.hook.curio.CurioCombatHook;
import com.xiaoyue.tingenuity_library.register.LibraryHooks;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class EnchantmentCurioModifier extends Modifier implements CurioCombatHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, LibraryHooks.CURIO_COMBAT);
    }

    @Override
    public void onCurioToDamagePre(IToolStackView curio, LivingHurtEvent event, LivingEntity attacker, LivingEntity target, int level) {
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
    public void onCurioTakeDamagePre(IToolStackView curio, LivingHurtEvent event, LivingEntity entity, DamageSource source, int level) {
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
