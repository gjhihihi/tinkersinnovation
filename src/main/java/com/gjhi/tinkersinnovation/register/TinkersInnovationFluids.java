package com.gjhi.tinkersinnovation.register;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;


public class TinkersInnovationFluids {
     public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MOD_ID);
//     public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TinkersCreation.MOD_ID);
     private static FluidObject<ForgeFlowingFluid> register(String name, int temp) {
          String still = String.format("%s:block/fluid/%s/still", MOD_ID, name);
          String flow = String.format("%s:block/fluid/%s/flowing", MOD_ID, name);
          return FLUIDS.register(name, FluidAttributes.builder(new ResourceLocation(still), new ResourceLocation(flow)).density(2000).viscosity(10000).temperature(temp).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA), Material.LAVA, 15);
     }

     public static FluidObject<ForgeFlowingFluid> polychrome_alloy = register("polychrome_alloy", 1200);
     public static FluidObject<ForgeFlowingFluid> experience = register("experience", 600);
     public static FluidObject<ForgeFlowingFluid> andesite_alloy = register("andesite_alloy", 900);
     public static FluidObject<ForgeFlowingFluid> void_crystal = register("void_crystal", 600);
     public static FluidObject<ForgeFlowingFluid> enchantment_essence = register("enchantment_essence", 600);
     public static FluidObject<ForgeFlowingFluid> life_essence = register("life_essence", 600);
     public static FluidObject<ForgeFlowingFluid> ocean_essence = register("ocean_essence", 600);
     public static FluidObject<ForgeFlowingFluid> slimton = register("slimton", 1000);
     public static FluidObject<ForgeFlowingFluid> ether = register("ether", 1200);

/*     public static final RegistryObject<Fluid> * =  FLUIDS.register("*", () -> new ForgeFlowingFluid.Source(TinkersCreationFluids.*_PROPERTIES ));
     public static final RegistryObject<Fluid> *_flowing =  FLUIDS.register("*_flowing", () -> new ForgeFlowingFluid.Flowing(TinkersCreationFluids.*_PROPERTIES ));
     public static final ForgeFlowingFluid.Properties *_PROPERTIES = new ForgeFlowingFluid.Properties(() -> *.get(),() -> *_flowing.get(), FluidAttributes.builder(Molten,Molten_Flowing)
             .color(0XFFD25204).density(2000).luminosity(6).viscosity(10000).sound(SoundEvents.BUCKET_EMPTY_LAVA,SoundEvents.BUCKET_FILL_LAVA)).block(() -> TinkersCreationFluids.*_block.get()).bucket(() -> TinkersCreationItems.*_bucket.get());
     public static final RegistryObject<LiquidBlock> *_block = TinkersCreationBlocks.BLOCKS.register("*_block", () -> new LiquidBlock(() -> (FlowingFluid) TinkersCreationFluids.*.get(), BlockBehaviour.Properties.of(Material.LAVA).noCollission().strength(100f)
             .noDrops()));*/
}
