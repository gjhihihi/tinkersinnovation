package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.library.effects.AbsorbedEffect;
import com.gjhi.tinkersinnovation.library.effects.NotHungryEffect;
import com.gjhi.tinkersinnovation.library.effects.PetrifiedCooldownEffect;
import com.gjhi.tinkersinnovation.library.effects.SoulDevouringEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.function.IntFunction;
import java.util.function.Supplier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);
    //private static final IntFunction<Supplier<TinkerEffect>> MARKER_NO_MILK_EFFECT = color -> () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, color, true);
    public static RegistryObject<TinkerEffect> petrifiedCooldownEffect = MOB_EFFECTS.register("petrified_cooldown", PetrifiedCooldownEffect::new);
    public static RegistryObject<TinkerEffect> soulDevouringEffect = MOB_EFFECTS.register("soul_devouring", SoulDevouringEffect::new);
    public static RegistryObject<TinkerEffect> absorbedEffect = MOB_EFFECTS.register("absorbed", AbsorbedEffect::new);
    public static RegistryObject<TinkerEffect> notHungryEffect = MOB_EFFECTS.register("not_hungry", NotHungryEffect::new);
}
