package com.gjhi.tinkersinnovation;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class TinkersInnovationConfig {
    public static class Common {
        public static ForgeConfigSpec.ConfigValue<Integer> voidCrystalOreSize;
        public static ForgeConfigSpec.ConfigValue<Integer> voidCrystalOreMaxHeight;
        public static ForgeConfigSpec.ConfigValue<Integer> voidCrystalOreMinHeight;
        public static ForgeConfigSpec.ConfigValue<Integer> voidCrystalOreCount;
        public Common(ForgeConfigSpec.Builder builder){
            builder.comment("Common Config");
            voidCrystalOreCount =builder.comment("Maximum Count number of refreshes for ore")
                    .defineInRange("Count",32,0,32);
            voidCrystalOreSize = builder.comment("Maximum Size number of refreshes for ore")
                    .defineInRange("Size", 32, 0, 32);
            voidCrystalOreMaxHeight = builder.comment("Upper limit of height")
                    .defineInRange("HeightMax", 128, -64, 128);
            voidCrystalOreMinHeight = builder.comment("lower limit of height")
                    .defineInRange("HeightMin", -64, -64, 128);
        }
    }
    public static class Client {
        public Client(ForgeConfigSpec.Builder builder){
        }
    }
    public static class Server {
        public Server(ForgeConfigSpec.Builder builder){
        }
    }
    private final Pair<Common, ForgeConfigSpec> commonPair;
    private final Pair<Client, ForgeConfigSpec> clientPair;
    private final Pair<Server, ForgeConfigSpec> serverPair;
    public final Common common;
    public final Client client;
    public final Server server;

    public TinkersInnovationConfig(ModLoadingContext context) {
        commonPair = new ForgeConfigSpec.Builder()
                .configure(Common::new);
        clientPair = new ForgeConfigSpec.Builder()
                .configure(Client::new);
        serverPair = new ForgeConfigSpec.Builder()
                .configure(Server::new);

        context.registerConfig(ModConfig.Type.COMMON, commonPair.getRight());
        context.registerConfig(ModConfig.Type.CLIENT, clientPair.getRight());
        context.registerConfig(ModConfig.Type.SERVER, serverPair.getRight());
        common = commonPair.getLeft();
        client = clientPair.getLeft();
        server = serverPair.getLeft();
    }
}
