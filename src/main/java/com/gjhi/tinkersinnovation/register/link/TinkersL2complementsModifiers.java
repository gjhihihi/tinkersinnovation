package com.gjhi.tinkersinnovation.register.link;

import com.gjhi.tinkersinnovation.modifiers.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TinkersL2complementsModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create("tinkersinnovation");
    public static void init() {
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static StaticModifier<HeirophantGreenModifier> heriophant_green = MODIFIERS.register("heriophant_green", HeirophantGreenModifier::new);
    public static StaticModifier<SoulFlameBladeModifier> soul_flame_blade = MODIFIERS.register("soul_flame_blade", SoulFlameBladeModifier::new);
    public static StaticModifier<IceBladeModifier> ice_blade = MODIFIERS.register("ice_blade", IceBladeModifier::new);
    public static StaticModifier<CruseBladeModifier> cruse_blade = MODIFIERS.register("cruse_blade", CruseBladeModifier::new);
    public static StaticModifier<SharpBladeModifier> sharp_blade = MODIFIERS.register("sharp_blade", SharpBladeModifier::new);
    public static StaticModifier<SoulFlameThornModifier> soul_flame_thorn = MODIFIERS.register("soul_flame_thorn", SoulFlameThornModifier::new);
    public static StaticModifier<IceThornModifier> ice_thorn = MODIFIERS.register("ice_thorn", IceThornModifier::new);
    public static StaticModifier<StoneCageModifier> stone_cage = MODIFIERS.register("stone_cage", StoneCageModifier::new);
}
