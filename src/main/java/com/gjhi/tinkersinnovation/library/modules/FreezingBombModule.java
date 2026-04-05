package com.gjhi.tinkersinnovation.library.modules;

import com.gjhi.tinkersinnovation.contexts.BombExplodeContext;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.library.hooks.TinkersBombHook;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.List;

public record FreezingBombModule(LevelingValue time) implements ModifierModule, TinkersBombHook {
    public static final RecordLoadable<FreezingBombModule> LOADER = RecordLoadable.create(
            LevelingValue.LOADABLE.requiredField("seconds", FreezingBombModule::time),
            FreezingBombModule::new
    );

    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return HookProvider.defaultHooks(TinkersInnovationHooks.TINKER_BOMB);
    }

    @Override
    public void onTinkersBombExplosion(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, BombExplodeContext context) {
        context.setFired(false);
    }

    @Override
    public void afterTinkersBombExplode(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, EBomb bomb, LivingEntity attacker, List<LivingEntity> targets) {
        for (LivingEntity target : targets){
            if (target.canFreeze()) {
                target.setTicksFrozen(Math.max(target.getTicksRequiredToFreeze(), target.getTicksFrozen()) + (int)(this.time.compute(modifier.getEffectiveLevel()) * 40.0F));
                target.setRemainingFireTicks(0);
            }
        }
    }

}
