package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.library.modifiers.*;
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
    public static StaticModifier<ChaosModifier> chaos = MODIFIERS.register("chaos", ChaosModifier::new);
    public static StaticModifier<ChaosArmorModifier> chaos_armor = MODIFIERS.register("chaos_armor", ChaosArmorModifier::new);
    public static StaticModifier<MiracleModifier> miracle = MODIFIERS.register("miracle", MiracleModifier::new);
    public static StaticModifier<ShieldAmountModifier> shield_amount = MODIFIERS.register("shield_amount", ShieldAmountModifier::new);
    public static StaticModifier<AppearanceModifier> appearance = MODIFIERS.register("appearance", AppearanceModifier::new);
    public static StaticModifier<GhostSlashModifier> ghost_slash = MODIFIERS.register("ghost_slash", GhostSlashModifier::new);
    public static StaticModifier<OverImitateModifier> over_imitate = MODIFIERS.register("over_imitate", OverImitateModifier::new);
    public static StaticModifier<OverImitateArmorModifier> over_imitate_armor = MODIFIERS.register("over_imitate_armor", OverImitateArmorModifier::new);
    public static StaticModifier<BacktrackModifier> backtrack = MODIFIERS.register("backtrack", BacktrackModifier::new);
    public static StaticModifier<TeleportModifier> teleport = MODIFIERS.register("teleport", TeleportModifier::new);
    public static StaticModifier<AbyssResonanceModifier> abyss_resonance = MODIFIERS.register("abyss_resonance", AbyssResonanceModifier::new);
    public static StaticModifier<SuspendModifier> suspend = MODIFIERS.register("suspend", SuspendModifier::new);
    public static StaticModifier<BombModifier> bomb = MODIFIERS.register("bomb", BombModifier::new);
    public static StaticModifier<StressModifier> stress = MODIFIERS.register("stress", StressModifier::new);
    public static StaticModifier<FocusingModifier> focusing = MODIFIERS.register("focusing", FocusingModifier::new);
    public static StaticModifier<FlameBurningModifier> flame_burning = MODIFIERS.register("flame_burning", FlameBurningModifier::new);
    public static StaticModifier<MoisturizingModifier> moisturizing = MODIFIERS.register("moisturizing", MoisturizingModifier::new);
    public static StaticModifier<DilapidationModifier> dilapidation = MODIFIERS.register("dilapidation", DilapidationModifier::new);
    public static StaticModifier<AbsorbedModifier> absorbed = MODIFIERS.register("absorbed", AbsorbedModifier::new);
    public static StaticModifier<PrecisionModifier> precision = MODIFIERS.register("precision", PrecisionModifier::new);
    public static StaticModifier<RagingModifier> raging = MODIFIERS.register("raging", RagingModifier::new);
    public static StaticModifier<NotHungryModifier> not_hungry = MODIFIERS.register("not_hungry", NotHungryModifier::new);
    public static StaticModifier<SweetModifier> sweet = MODIFIERS.register("sweet", SweetModifier::new);
    public static StaticModifier<SoulDevouringModifier> soul_devouring = MODIFIERS.register("soul_devouring", SoulDevouringModifier::new);
    public static StaticModifier<SoulFixingModifier> soul_fixing = MODIFIERS.register("soul_fixing", SoulFixingModifier::new);
    public static StaticModifier<ElasticModifier> elastic = MODIFIERS.register("elastic", ElasticModifier::new);
    public static StaticModifier<InflammationModifier> inflammation = MODIFIERS.register("inflammation", InflammationModifier::new);
    public static StaticModifier<SlipperyModifier> slippery = MODIFIERS.register("slippery", SlipperyModifier::new);
    public static StaticModifier<SplitModifier> split = MODIFIERS.register("split", SplitModifier::new);
    public static StaticModifier<TransmutationModifier> transmutation = MODIFIERS.register("transmutation", TransmutationModifier::new);
    public static StaticModifier<TransmutationArmorModifier> transmutation_armor = MODIFIERS.register("transmutation_armor", TransmutationArmorModifier::new);
    public static StaticModifier<FarseeingModifier> farseeing = MODIFIERS.register("farseeing", FarseeingModifier::new);
    public static StaticModifier<FarseeingArmorModifier> farseeing_armor = MODIFIERS.register("farseeing_armor", FarseeingArmorModifier::new);
    public static StaticModifier<SoulLoverModifier> soul_lover = MODIFIERS.register("soul_lover", SoulLoverModifier::new);
    public static StaticModifier<MountKillerModifier> mount_killer = MODIFIERS.register("mount_killer", MountKillerModifier::new);
    public static StaticModifier<FallOutModifier> fall_out = MODIFIERS.register("fall_out", FallOutModifier::new);
    public static StaticModifier<StraddliteModifier> straddlite = MODIFIERS.register("straddlite", StraddliteModifier::new);
    public static StaticModifier<EternalModifier> eternal_weaker = MODIFIERS.register("eternal_weaker", EternalModifier::new);
    public static StaticModifier<DisintegratedModifier> disintegrated = MODIFIERS.register("disintegrated", DisintegratedModifier::new);
    public static StaticModifier<NoBlocksBreakModifier> no_blocks_break = MODIFIERS.register("no_blocks_break", NoBlocksBreakModifier::new);
    public static StaticModifier<BombLoverModifier> bomb_lover = MODIFIERS.register("bomb_lover", BombLoverModifier::new);
    public static StaticModifier<FarThrowModifier> far_throw = MODIFIERS.register("far_throw", FarThrowModifier::new);
    public static StaticModifier<LaceratingBombModifier> lacerating_bomb = MODIFIERS.register("lacerating_bomb", LaceratingBombModifier::new);
    public static StaticModifier<FiredExplosionModifier> fired_explosion = MODIFIERS.register("fired_explosion", FiredExplosionModifier::new);
    public static StaticModifier<HoldSteadyModifier> thick_shield = MODIFIERS.register("thick_shield", HoldSteadyModifier::new);
    public static StaticModifier<OverCushionModifier> over_cushion = MODIFIERS.register("over_cushion", OverCushionModifier::new);
    public static StaticModifier<ShieldBreakModifier> shield_break = MODIFIERS.register("shield_break", ShieldBreakModifier::new);
    public static class L2ComplementsModifier {
        public static ModifierDeferredRegister L2C_MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
        public static void init() {
            L2C_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        public static StaticModifier<HeirophantGreenModifier> heirophant_green = L2C_MODIFIERS.register("heirophant_green", HeirophantGreenModifier::new);
        public static StaticModifier<SoulFlameBladeModifier> soul_flame_blade = L2C_MODIFIERS.register("soul_flame_blade", SoulFlameBladeModifier::new);
        public static StaticModifier<IceBladeModifier> ice_blade = L2C_MODIFIERS.register("ice_blade", IceBladeModifier::new);
        public static StaticModifier<CurseBladeModifier> curse_blade = L2C_MODIFIERS.register("curse_blade", CurseBladeModifier::new);
        public static StaticModifier<SharpBladeModifier> sharp_blade = L2C_MODIFIERS.register("sharp_blade", SharpBladeModifier::new);
        public static StaticModifier<SoulFlameThornModifier> soul_flame_thorn = L2C_MODIFIERS.register("soul_flame_thorn", SoulFlameThornModifier::new);
        public static StaticModifier<IceThornModifier> ice_thorn = L2C_MODIFIERS.register("ice_thorn", IceThornModifier::new);
        public static StaticModifier<StoneCageModifier> stone_cage = L2C_MODIFIERS.register("stone_cage", StoneCageModifier::new);
    }
    public static class TinkersIngenuityModifiers {
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
    public static class L2HostilityModifiers {
        public static ModifierDeferredRegister L2H_MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
        public static void init() {
            L2H_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        public static StaticModifier<ArroganceModifier> arrogance = L2H_MODIFIERS.register("arrogance", ArroganceModifier::new);
        public static StaticModifier<ArroganceArmorModifier> arrogance_armor = L2H_MODIFIERS.register("arrogance_armor", ArroganceArmorModifier::new);
        public static StaticModifier<TankyTraitModifier> tanky = L2H_MODIFIERS.register("tanky", TankyTraitModifier::new);
        public static StaticModifier<SpeedyTraitModifier> speedy = L2H_MODIFIERS.register("speedy", SpeedyTraitModifier::new);
        public static StaticModifier<UndyingTraitModifier> undying = L2H_MODIFIERS.register("undying", UndyingTraitModifier::new);
        public static StaticModifier<RegenerateTraitModifier> regenerate = L2H_MODIFIERS.register("regenerate", RegenerateTraitModifier::new);
        public static StaticModifier<ProtectionTraitModifier> protection = L2H_MODIFIERS.register("protection", ProtectionTraitModifier::new);
        public static StaticModifier<InvisibleTraitModifier> invisible = L2H_MODIFIERS.register("invisible", InvisibleTraitModifier::new);
        public static StaticModifier<CorrosionTraitModifier> corrosion = L2H_MODIFIERS.register("corrosion", CorrosionTraitModifier::new);
        public static StaticModifier<ErosionTraitModifier> erosion = L2H_MODIFIERS.register("erosion", ErosionTraitModifier::new);
        public static StaticModifier<WeakTraitModifier> weak = L2H_MODIFIERS.register("weak", WeakTraitModifier::new);
        public static StaticModifier<StrayTraitModifier> stray = L2H_MODIFIERS.register("stray", StrayTraitModifier::new);
        public static StaticModifier<PoisonousTraitModifier> poisonous = L2H_MODIFIERS.register("poisonous", PoisonousTraitModifier::new);
        public static StaticModifier<WitheringTraitModifier> withering = L2H_MODIFIERS.register("withering", WitheringTraitModifier::new);
        public static StaticModifier<LevitationTraitModifier> levitation = L2H_MODIFIERS.register("levitation", LevitationTraitModifier::new);
        public static StaticModifier<BlindTraitModifier> blind = L2H_MODIFIERS.register("blind", BlindTraitModifier::new);
        public static StaticModifier<NauseaTraitModifier> nausea = L2H_MODIFIERS.register("nausea", NauseaTraitModifier::new);
        public static StaticModifier<CurseTraitModifier> curse = L2H_MODIFIERS.register("curse", CurseTraitModifier::new);
        public static StaticModifier<SoulBurnerTraitModifier> soul_burner = L2H_MODIFIERS.register("soul_burner", SoulBurnerTraitModifier::new);
        public static StaticModifier<FreezingTraitModifier> freezing = L2H_MODIFIERS.register("freezing", FreezingTraitModifier::new);
        public static StaticModifier<ReflectTraitModifier> reflect = L2H_MODIFIERS.register("reflect", ReflectTraitModifier::new);
        public static StaticModifier<FieryTraitModifier> fiery = L2H_MODIFIERS.register("fiery", FieryTraitModifier::new);
        public static StaticModifier<NoSealedModifier> no_sealed = L2H_MODIFIERS.register("no_sealed", NoSealedModifier::new);
        public static StaticModifier<RagnarokTraitModifier> ragnarok = L2H_MODIFIERS.register("ragnarok", RagnarokTraitModifier::new);
    }
    public static class IceAndFireModifiers {
        public static ModifierDeferredRegister IAF_MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
        public static void init() {
            IAF_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        public static StaticModifier<PetrifiedModifier> petrified = IAF_MODIFIERS.register("petrified", PetrifiedModifier::new);
        public static StaticModifier<PetrifiedSlimeSkullModifier> petrified_slimeskull = IAF_MODIFIERS.register("petrified_slimeskull", PetrifiedSlimeSkullModifier::new);
    }
    public static class AlexsMobsModifiers {
        public static ModifierDeferredRegister ASM_MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
        public static void init() {
            ASM_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        public static StaticModifier<NoKnockbackModifier> no_knockback = ASM_MODIFIERS.register("no_knockback", NoKnockbackModifier::new);
        public static StaticModifier<NoKnockbackArmorModifier> no_knockback_armor = ASM_MODIFIERS.register("no_knockback_armor", NoKnockbackArmorModifier::new);
        public static StaticModifier<InfectedModifier> infected = ASM_MODIFIERS.register("infected", InfectedModifier::new);
        public static StaticModifier<InfectedArmorModifier> infected_armor = ASM_MODIFIERS.register("infected_armor", InfectedArmorModifier::new);
        public static StaticModifier<SusceptibleModifier> susceptible = ASM_MODIFIERS.register("susceptible", SusceptibleModifier::new);
        public static StaticModifier<SunshineModifier> sunshine = ASM_MODIFIERS.register("sunshine", SunshineModifier::new);
        public static StaticModifier<SunshineArmorModifier> sunshine_armor = ASM_MODIFIERS.register("sunshine_armor", SunshineArmorModifier::new);
        public static StaticModifier<SoulLoverArmorModifier> soul_lover_armor = ASM_MODIFIERS.register("soul_lover_armor", SoulLoverArmorModifier::new);
    }
}
