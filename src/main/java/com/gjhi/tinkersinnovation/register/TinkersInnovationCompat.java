package com.gjhi.tinkersinnovation.register;

import net.minecraftforge.fml.ModList;

import static com.gjhi.tinkersinnovation.TinkersInnovation.tinkers_logger;

public class TinkersInnovationCompat {
    public static class L2Complements {
        public static String getModId(){
            return "l2complements";
        }
        public static boolean isLoaded(){
            return ModList.get().isLoaded(getModId());
        }
        public static void init(){
            if (L2Complements.isLoaded()) {
                tinkers_logger.info("Found L2Complements, integration initializing……");
                TinkersInnovationModifiers.L2ComplementsModifier.init();
            }
        }
    }

    public static class L2Hostility {
        public static String getModId(){
            return "l2hostility";
        }
        public static boolean isLoaded(){
            return ModList.get().isLoaded(getModId());
        }
        public static void init(){
            if (L2Hostility.isLoaded()){
                tinkers_logger.info("Found L2Hostility, integration initializing……");
                TinkersInnovationModifiers.L2HostilityModifiers.init();
            }
        }
    }

    public static class TinkersIngenuity {
        public static String getModId(){
            return "tinkers_ingenuity";
        }
        public static boolean isLoaded(){
            return ModList.get().isLoaded(getModId());
        }
        public static void init(){
            if (TinkersIngenuity.isLoaded()){
                tinkers_logger.info("Found Tinkers' Ingenuity, integration initializing……");
                TinkersInnovationModifiers.TinkersIngenuityModifiers.init();
            }
        }
    }

    public static class IceAndFire {
        public static String getModId(){
            return "iceandfire";
        }
        public static boolean isLoaded(){
            return ModList.get().isLoaded(getModId());
        }
        public static void init(){
            if (IceAndFire.isLoaded()){
                tinkers_logger.info("Found Ice And Fire, integration initializing……");
                TinkersInnovationModifiers.IceAndFireModifiers.init();
            }
        }
    }

    public static class AlexsMobs {
        public static String getModId(){
            return "alexsmobs";
        }
        public static boolean isLoaded(){
            return ModList.get().isLoaded(getModId());
        }
        public static void init(){
            if (AlexsMobs.isLoaded()){
                tinkers_logger.info("Found Alex's Mobs, integration initializing……");
                TinkersInnovationModifiers.AlexsMobsModifiers.init();
            }
        }
    }

    public static class Create {
        public static String getModId(){
            return "create";
        }
        public static boolean isLoaded(){
            return ModList.get().isLoaded(getModId());
        }
        public static void init(){
            if (Create.isLoaded()){
                tinkers_logger.info("Found Create, integration initializing……");
            }
        }
    }
}
