package com.gjhi.tinkersinnovation.library.fluid_effects;

import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.fluid.EffectLevel;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffect;
import slimeknights.tconstruct.library.modifiers.fluid.FluidEffectContext;

public record SpawnLightningBoltFluidEffect(LevelingValue damage) implements FluidEffect<FluidEffectContext> {
    public static final RecordLoadable<SpawnLightningBoltFluidEffect> LOADER = RecordLoadable.create(
            LevelingValue.LOADABLE.requiredField("damage", SpawnLightningBoltFluidEffect::damage),
            SpawnLightningBoltFluidEffect::new
    );

    @Override
    public Component getDescription(RegistryAccess access) {
        return Component.translatable(FluidEffect.getTranslationKey(this.getLoader()));
    }

    @Override
    public RecordLoadable<? extends FluidEffect<FluidEffectContext>> getLoader() {
        return LOADER;
    }

    @Override
    public float apply(FluidStack fluid, EffectLevel level, FluidEffectContext context, IFluidHandler.FluidAction action) {
        if (action.execute()) {
            TinkersInnovationUtils.spawnLightningBolt(context.getLevel(), (int) context.getLocation().x(), (int) context.getLocation().y(), (int) context.getLocation().z(), MobSpawnType.MOB_SUMMONED, damage.computeForScale(level.value()));
        }
        return level.value();
    }
}
