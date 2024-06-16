package com.gjhi.tinkersinnovation.register;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.function.IntFunction;
import java.util.function.Supplier;

public class TinkersInnovationEffect extends TinkerModule {
    private static final IntFunction<Supplier<TinkerEffect>> MARKER_EFFECT = color -> () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, color, true);
    //public static RegistryObject<TinkerEffect> impregnableEffect = MOB_EFFECTS.register("impregnable", MARKER_EFFECT.apply(0xDADBD2));
    public static RegistryObject<TinkerEffect> cruseEffect = MOB_EFFECTS.register("cruse", MARKER_EFFECT.apply(0xDADBD2));
}
