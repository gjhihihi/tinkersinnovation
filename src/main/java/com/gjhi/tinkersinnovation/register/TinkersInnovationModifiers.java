package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.modifiers.*;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
    public static StaticModifier<PoseiditeWeaponModifier> poseidite_weapon = MODIFIERS.register("poseidite_weapon", PoseiditeWeaponModifier::new);
    public static StaticModifier<VoidModifier> _void = MODIFIERS.register("void", VoidModifier::new);
    public static StaticModifier<EnchantmentModifier> enchantment = MODIFIERS.register("enchantment", EnchantmentModifier::new);
    public static StaticModifier<EnchantmentArmorModifier> enchantment_armor = MODIFIERS.register("enchantment_armor", EnchantmentArmorModifier::new);
    public static StaticModifier<VitalityModifier> vitality = MODIFIERS.register("vitality", VitalityModifier::new);
    public static StaticModifier<VitalityArmorModifier> vitality_armor = MODIFIERS.register("vitality_armor", VitalityArmorModifier::new);
    public static StaticModifier<OmnipotentModifier> omnipotent = MODIFIERS.register("omnipotent", OmnipotentModifier::new);
    public static StaticModifier<BigHeartModifier> big_heart = MODIFIERS.register("big_heart", BigHeartModifier::new);
    public static StaticModifier<FastEatModifier> fast_eat = MODIFIERS.register("fast_eat", FastEatModifier::new);
    public static StaticModifier<ChromaticAberrationModifier> chromatic_aberration = MODIFIERS.register("chromatic_aberration", ChromaticAberrationModifier::new);
    public static StaticModifier<BerserkerModifier> berserker = MODIFIERS.register("berserker", BerserkerModifier::new);
    public static StaticModifier<MountedStrikeModifier> mounted_strike = MODIFIERS.register("mounted_strike", MountedStrikeModifier::new);
    public static StaticModifier<TraderModifier> trader = MODIFIERS.register("trader", TraderModifier::new);
    public static StaticModifier<BIGSHOTModifier> BIGSHOT = MODIFIERS.register("big_shot", BIGSHOTModifier::new);
    public static StaticModifier<DoubleAttackModifier> double_attack = MODIFIERS.register("double_attack", DoubleAttackModifier::new);
    public static StaticModifier<RipeningModifier> ripening = MODIFIERS.register("ripening", RipeningModifier::new);
    public static StaticModifier<RakutenModifier> rakuten = MODIFIERS.register("rakuten", RakutenModifier::new);
    public static StaticModifier<PoseiditeBlessingModifier> poseidite_blessing = MODIFIERS.register("poseidite_blessing", PoseiditeBlessingModifier::new);
    public static StaticModifier<NEOModifier> NEO = MODIFIERS.register("neo", NEOModifier::new);
    public static StaticModifier<EternalModifier> eternal = MODIFIERS.register("eternal", EternalModifier::new);
    public static StaticModifier<HardenModifier> harden = MODIFIERS.register("harden", HardenModifier::new);
    public static StaticModifier<DampenedModifier> dampened = MODIFIERS.register("dampened", DampenedModifier::new);
    public static StaticModifier<TransparentModifier> transparent = MODIFIERS.register("transparent", TransparentModifier::new);
    public static StaticModifier<ResonanceModifier> resonance = MODIFIERS.register("resonance", ResonanceModifier::new);
    public static StaticModifier<SuperUndeadHitModifier> super_undead = MODIFIERS.register("super_undead", SuperUndeadHitModifier::new);
    public static StaticModifier<HealthFixingModifier> health_fixing = MODIFIERS.register("health_fixing", HealthFixingModifier::new);
    public static StaticModifier<SupersonicSpeedModifier> supersonic_speed = MODIFIERS.register("supersonic_speed", SupersonicSpeedModifier::new);
    public static StaticModifier<WindPowerModifier> wind_power = MODIFIERS.register("wind_power", WindPowerModifier::new);
    public static StaticModifier<HoldSteadyModifier> hold_steady = MODIFIERS.register("hold_steady", HoldSteadyModifier::new);
    public static StaticModifier<BlastingModifier> blasting = MODIFIERS.register("blasting", BlastingModifier::new);
    public static StaticModifier<DetonationModifier> detonation = MODIFIERS.register("detonation", DetonationModifier::new);
    public static StaticModifier<ExplosiveModifier> explosive = MODIFIERS.register("explosive", ExplosiveModifier::new);
    public static StaticModifier<WitherKillerModifier> wither_killer = MODIFIERS.register("wither_killer", WitherKillerModifier::new);
    public static StaticModifier<WitherSkullModifier> wither_skull = MODIFIERS.register("wither_skull", WitherSkullModifier::new);
    public static StaticModifier<WitherProtectionModifier> wither_protection = MODIFIERS.register("wither_protection", WitherProtectionModifier::new);
    public static StaticModifier<SculkModifier> sculk = MODIFIERS.register("sculk", SculkModifier::new);
}
