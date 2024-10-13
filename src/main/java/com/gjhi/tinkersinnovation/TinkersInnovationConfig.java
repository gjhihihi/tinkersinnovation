package com.gjhi.tinkersinnovation;


import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class TinkersInnovationConfig {
    public static final CommonConfig COMMON;
    public static ForgeConfigSpec config;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = pair.getLeft();
        config = pair.getRight();
    }

    public static class CommonConfig {
        public final OreConfig voidcrystalOre;
        //public final MaterialConfig createMaterial;
        /*public final ForgeConfigSpec.BooleanValue icelandsparGeodes;
        public final ForgeConfigSpec.BooleanValue topazGeodes;
        public final ForgeConfigSpec.BooleanValue lizaniteGeodes;
        public final ForgeConfigSpec.BooleanValue cordieriteGeodes;
        public final ForgeConfigSpec.BooleanValue prehniteGeodes;*/
        CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Void Crystal Ore Worldgen").push("void_crystal_ore");
            voidcrystalOre = new VoidCrystalOreConfig(builder);
            builder.pop();/*
            builder.comment("Whether to link Create").push("create");
            createMaterial = new CreateMaterialConfig(builder);
            builder.pop();*/

            /*builder.comment("World generation, mainly geodes").push("worldgen");
            {
                this.icelandsparGeodes = builder
                        .comment("If true, iceland spar geodes generate in icy biomes.")
                        .define("iceland_spar", true);
                this.topazGeodes = builder
                        .comment("If true, topaz geodes generate at the bottom of the sea.")
                        .define("topaz", true);
                this.lizaniteGeodes = builder
                        .comment("If true, lizanite geodes generate in the overworld.")
                        .define("lizanite", true);
                this.cordieriteGeodes = builder
                        .comment("If true, cordierite geodes generate in the overworld.")
                        .define("cordierite", true);
                this.prehniteGeodes = builder
                        .comment("If true, prehnite geodes generate in the overworld.")
                        .define("prehnite", true);
                builder.pop();
            }*/
        }
    }

    public static class OreConfig {
        public ForgeConfigSpec.BooleanValue enabled;
        public ForgeConfigSpec.IntValue minY;
        public ForgeConfigSpec.IntValue maxY;
        public ForgeConfigSpec.IntValue count;
        public ForgeConfigSpec.IntValue size;
        public OreConfig(ForgeConfigSpec.Builder builder) {
        }
        public boolean isEnabled() {
            return enabled.get();
        }
        public int getCount() {
            return count.get();
        }
        public int getSize() {
            return size.get();
        }
        public int getMaxY() {
            return maxY.get();
        }
        public int getMinY() {
            return minY.get();
        }
    }
    /*public static class MaterialConfig {
        public ForgeConfigSpec.BooleanValue enabled;
        public boolean isEnabled() {
            return enabled.get();
        }
        public MaterialConfig(ForgeConfigSpec.Builder builder) {
        }
    }*/

    public static class VoidCrystalOreConfig extends OreConfig {
        public VoidCrystalOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Void Crystal ore").define("VoidCrystalOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -64, -64, -20);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -54, -60, 0);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }
    /*public static class CreateMaterialConfig extends MaterialConfig{
        public CreateMaterialConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Create's Material").define("CreateMaterialEnabled", false);
        }
    }*/
  /*  public static class TitaniumOreConfig extends OreConfig {

        public TitaniumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Titanium ore").define("titaniumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class AltairiumOreConfig extends OreConfig {

        public AltairiumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Altairium ore").define("altairiumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 10, -10, 50);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 30, 20, 60);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class HalleiumOreConfig extends OreConfig {

        public HalleiumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Halleium ore").define("halleiumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class HothiumOreConfig extends OreConfig {

        public HothiumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Hothium ore").define("hothiumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 10, -10, 50);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 60, 40, 90);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 30, 10, 50);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 5, 1, 40);
        }
    }

    public static class ImmersedSilverOreConfig extends OreConfig {

        public ImmersedSilverOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable ImmersedSilver ore").define("immersedSilverOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 30, 10, 50);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 40, 20, 70);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class InertwitheriumOreConfig extends OreConfig {

        public InertwitheriumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Inertwitherium ore").define("inertwitheriumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 5, 0, 60);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 10, 2, 85);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 8, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 7);
        }
    }

    public static class MagigaOreConfig extends OreConfig {

        public MagigaOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Magiga ore").define("magigaOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class SteamiumOreConfig extends OreConfig {

        public SteamiumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Steamium ore").define("steamiumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class StellariumOreConfig extends OreConfig {

        public StellariumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Stellarium ore").define("stellariumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }
    public static class ToniumOreConfig extends OreConfig {

        public ToniumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Tonium ore").define("toniumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }
    public static class CorundumOreConfig extends OreConfig {

        public CorundumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Corundum ore").define("corundumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -10, -30, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 10, 0, 30);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }
    public static class AventurineOreConfig extends OreConfig {

        public AventurineOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Aventurine ore").define("aventurineOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 30, 10, 40);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 70, 50, 90);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 10, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 8, 1, 40);
        }
    }
    public static class RedinsOreConfig extends OreConfig {

        public RedinsOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Redins ore").define("redinsOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -10, -30, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 30, 10, 40);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 15, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 5, 1, 40);
        }
    }
    public static class VibratingOreConfig extends OreConfig {

        public VibratingOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Vibrating Crystal ore").define("vibratingcrystalOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -30, -50, -10);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 0, -10, 10);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }*/
}