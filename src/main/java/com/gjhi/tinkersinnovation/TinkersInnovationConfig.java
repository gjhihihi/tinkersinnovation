package com.gjhi.tinkersinnovation;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class TinkersInnovationConfig {
    public static class Common {
        public static ForgeConfigSpec.ConfigValue<Integer> OmnipotentMaxLevel;
        public Common(ForgeConfigSpec.Builder builder){
            builder.comment("Common Config");
            OmnipotentMaxLevel = builder.comment("The max level of omnipotent modifier")
                    .defineInRange("MaxLevel", 2, 1, Integer.MAX_VALUE);
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
