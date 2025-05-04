package com.gjhi.tinkersinnovation.library.modifiers;

import com.xiaoyue.tingenuity_library.library.hook.curio.CurioCombatHook;
import com.xiaoyue.tingenuity_library.register.LibraryHooks;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class VoidCurioModifier extends NoLevelsModifier implements CurioCombatHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        builder.addHook(this, LibraryHooks.CURIO_COMBAT);
    }

    @Override
    public void onCurioToDamagePre(IToolStackView curio, LivingHurtEvent event, LivingEntity attacker, LivingEntity target, int level) {
        int time = target.invulnerableTime;
        target.hurt(DamageSource.OUT_OF_WORLD,2);
        target.invulnerableTime = time;
    }
}
