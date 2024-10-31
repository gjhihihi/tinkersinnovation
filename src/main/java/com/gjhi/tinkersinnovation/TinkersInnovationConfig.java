package com.gjhi.tinkersinnovation;

import net.minecraftforge.common.ForgeConfigSpec;

public class TinkersInnovationConfig {
    // 声明配置文件和配置项
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;
    public static ForgeConfigSpec.BooleanValue someBoolean;
    static {
        BUILDER.comment("TinkersInnovationConfig");
        //配置文件头
        someBoolean = BUILDER.comment("test")
                .define("test", true);

        //配置文件尾
        CONFIG = BUILDER.build();
    }
}
