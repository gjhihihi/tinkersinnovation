package com.gjhi.tinkersinnovation.library.modifiers;

import com.xiaoyue.tingenuity_library.library.hook.curio.CurioBuilderHook;
import com.xiaoyue.tingenuity_library.library.hook.curio.CurioCombatHook;
import com.xiaoyue.tingenuity_library.register.LibraryHooks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import top.theillusivec4.curios.api.SlotContext;

public class WitherCurioModifier extends Modifier implements CurioBuilderHook, CurioCombatHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        builder.addHook(this, LibraryHooks.CURIO_BUILDER, LibraryHooks.CURIO_COMBAT);
    }
    @Override
    public void onCurioTick(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack stack) {
        entity.removeEffect(MobEffects.WITHER);
    }

    @Override
    public void onCurioToDamagePre(IToolStackView curio, LivingHurtEvent event, LivingEntity attacker, LivingEntity target, int level) {
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 50 * level));
    }
}
