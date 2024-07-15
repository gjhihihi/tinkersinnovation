package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.modifiers.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
    public static StaticModifier<PoseiditeWeaponModifier> poseidite_weapon = MODIFIERS.register("poseidite_weapon", PoseiditeWeaponModifier::new);
    public static StaticModifier<VoidModifier> void_ = MODIFIERS.register("void", VoidModifier::new);
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
    public static StaticModifier<EnvironmentRejectModifier> environment_reject = MODIFIERS.register("environment_reject", EnvironmentRejectModifier::new);
    public static StaticModifier<FireRejectModifier> fire_reject = MODIFIERS.register("fire_reject", FireRejectModifier::new);
    public static StaticModifier<ExplosionRejectModifier> explosion_reject = MODIFIERS.register("explosion_reject", ExplosionRejectModifier::new);
    public static StaticModifier<MagicRejectModifier> magic_reject = MODIFIERS.register("magic_reject", MagicRejectModifier::new);
    public static StaticModifier<InvincibleModifier> invincible = MODIFIERS.register("invincible", InvincibleModifier::new);
    public static StaticModifier<OwnerProtectionModifier> owner_protection = MODIFIERS.register("owner_protection", OwnerProtectionModifier::new);
    public static StaticModifier<HostilityModifier> hostility = MODIFIERS.register("hostility", HostilityModifier::new);
    public static StaticModifier<MiracleModifier> miracle = MODIFIERS.register("miracle", MiracleModifier::new);
    public static StaticModifier<ShieldAmountModifier> shield_amount = MODIFIERS.register("shield_amount", ShieldAmountModifier::new);
    public static StaticModifier<AppearanceModifier> appearance = MODIFIERS.register("appearance", AppearanceModifier::new);
    public static StaticModifier<GhostSlashModifier> ghost_slash = MODIFIERS.register("ghost_slash", GhostSlashModifier::new);
    public static class L2ComplementsModifier {
        public static ModifierDeferredRegister L2C_MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
        public static void init() {
            L2C_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        public static StaticModifier<HeirophantGreenModifier> heirophant_green = L2C_MODIFIERS.register("heirophant_green", HeirophantGreenModifier::new);
        public static StaticModifier<SoulFlameBladeModifier> soul_flame_blade = L2C_MODIFIERS.register("soul_flame_blade", SoulFlameBladeModifier::new);
        public static StaticModifier<IceBladeModifier> ice_blade = L2C_MODIFIERS.register("ice_blade", IceBladeModifier::new);
        public static StaticModifier<CruseBladeModifier> cruse_blade = L2C_MODIFIERS.register("cruse_blade", CruseBladeModifier::new);
        public static StaticModifier<SharpBladeModifier> sharp_blade = L2C_MODIFIERS.register("sharp_blade", SharpBladeModifier::new);
        public static StaticModifier<SoulFlameThornModifier> soul_flame_thorn = L2C_MODIFIERS.register("soul_flame_thorn", SoulFlameThornModifier::new);
        public static StaticModifier<IceThornModifier> ice_thorn = L2C_MODIFIERS.register("ice_thorn", IceThornModifier::new);
        public static StaticModifier<StoneCageModifier> stone_cage = L2C_MODIFIERS.register("stone_cage", StoneCageModifier::new);
    }
    public static class TinkersIngenuityModifiers{
        public static ModifierDeferredRegister TIY_MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
        public static void init() {
            TIY_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        public static StaticModifier<VitalityCurioModifier> vitality_curio = TIY_MODIFIERS.register("vitality_curio", VitalityCurioModifier::new);
        public static StaticModifier<SculkCurioModifier> sculk_curio = TIY_MODIFIERS.register("sculk_curio", SculkCurioModifier::new);
        public static StaticModifier<WaterCoolModifier> water_cool = TIY_MODIFIERS.register("water_cool", WaterCoolModifier::new);
        public static StaticModifier<VoidCurioModifier> void_curio = TIY_MODIFIERS.register("void_curio", VoidCurioModifier::new);
        public static StaticModifier<PoseiditeBlessingCurioModifier> poseidite_blessing_curio = TIY_MODIFIERS.register("poseidite_blessing_curio", PoseiditeBlessingCurioModifier::new);
        public static StaticModifier<NEOCurioModifier> NEO_curio = TIY_MODIFIERS.register("neo_curio", NEOCurioModifier::new);
        public static StaticModifier<EnchantmentCurioModifier> enchantment_curio = TIY_MODIFIERS.register("enchantment_curio", EnchantmentCurioModifier::new);
        public static StaticModifier<WitherCurioModifier> wither_curio = TIY_MODIFIERS.register("wither_curio", WitherCurioModifier::new);
    }
    public static class L2HostilityModifiers{
        public static ModifierDeferredRegister L2H_MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
        public static void init() {
            L2H_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        public static StaticModifier<ChaosModifier> chaos = L2H_MODIFIERS.register("chaos", ChaosModifier::new);
        public static StaticModifier<ChaosArmorModifier> chaos_armor = L2H_MODIFIERS.register("chaos_armor", ChaosArmorModifier::new);
        public static StaticModifier<ArroganceModifier> arrogance = L2H_MODIFIERS.register("arrogance", ArroganceModifier::new);
        public static StaticModifier<ArroganceArmorModifier> arrogance_armor = L2H_MODIFIERS.register("arrogance_armor", ArroganceArmorModifier::new);
        public static StaticModifier<TankyModifier> tanky = L2H_MODIFIERS.register("tanky", TankyModifier::new);
        public static StaticModifier<SpeedyModifier> speedy = L2H_MODIFIERS.register("speedy", SpeedyModifier::new);
        public static StaticModifier<UndyingModifier> undying = L2H_MODIFIERS.register("undying", UndyingModifier::new);
        public static StaticModifier<RegenerateModifier> regenerate = L2H_MODIFIERS.register("regenerate", RegenerateModifier::new);
        public static StaticModifier<ProtectionModifier> protection = L2H_MODIFIERS.register("protection", ProtectionModifier::new);
        public static StaticModifier<InvisibleModifier> invisible = L2H_MODIFIERS.register("invisible", InvisibleModifier::new);
        public static StaticModifier<CorrosionModifier> corrosion = L2H_MODIFIERS.register("corrosion", CorrosionModifier::new);
        public static StaticModifier<ErosionModifier> erosion = L2H_MODIFIERS.register("erosion", ErosionModifier::new);
        public static StaticModifier<WeakModifier> weak = L2H_MODIFIERS.register("weak", WeakModifier::new);
        public static StaticModifier<StrayModifier> stray = L2H_MODIFIERS.register("stray", StrayModifier::new);
        public static StaticModifier<PoisonousModifier> poisonous = L2H_MODIFIERS.register("poisonous", PoisonousModifier::new);
        public static StaticModifier<WitheringModifier> withering = L2H_MODIFIERS.register("withering", WitheringModifier::new);
        public static StaticModifier<LevitationModifier> levitation = L2H_MODIFIERS.register("levitation", LevitationModifier::new);
        public static StaticModifier<BlindModifier> blind = L2H_MODIFIERS.register("blind", BlindModifier::new);
        public static StaticModifier<NauseaModifier> nausea = L2H_MODIFIERS.register("nausea", NauseaModifier::new);
        public static StaticModifier<CruseModifier> cruse = L2H_MODIFIERS.register("cruse", CruseModifier::new);
        public static StaticModifier<SoulBurnerModifier> soul_burner = L2H_MODIFIERS.register("soul_burner", SoulBurnerModifier::new);
        public static StaticModifier<FreezingModifier> freezing = L2H_MODIFIERS.register("freezing", FreezingModifier::new);
    }
}
