package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.TConstruct;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;


public class TinkersInnovationFluids {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MOD_ID);


    public static FlowingFluidObject<ForgeFlowingFluid> polychrome_alloy = register("molten_polychrome_alloy", 1200);
    public static FlowingFluidObject<ForgeFlowingFluid> experience = register("molten_experience", 600);
    public static FlowingFluidObject<ForgeFlowingFluid> andesite_alloy = register("molten_andesite_alloy", 900);
    public static FlowingFluidObject<ForgeFlowingFluid> void_crystal = register("molten_void_crystal", 600);
    public static FlowingFluidObject<ForgeFlowingFluid> enchantment_essence = register("molten_enchantment_essence", 600);
    public static FlowingFluidObject<ForgeFlowingFluid> totemic_gold = register("molten_totemic_gold", 900);
    public static FlowingFluidObject<ForgeFlowingFluid> poseidite = register("molten_poseidite", 1000);
    public static FlowingFluidObject<ForgeFlowingFluid> decline = register("molten_decline", 1200);
    public static FlowingFluidObject<ForgeFlowingFluid> slimton = register("molten_slimton", 1000);
    public static FlowingFluidObject<ForgeFlowingFluid> shulkerate = register("molten_shulkerate", 1500);
    public static FlowingFluidObject<ForgeFlowingFluid> sculkium = register("molten_sculkium", 1000);
    public static FlowingFluidObject<ForgeFlowingFluid> eternium = register("molten_eternium", 1200);
    public static FlowingFluidObject<ForgeFlowingFluid> chaos = register("molten_chaos", 1000);
    public static FlowingFluidObject<ForgeFlowingFluid> miracle = register("molten_miracle", 1150);
    public static FlowingFluidObject<ForgeFlowingFluid> hostility = register("molten_hostility", 1200);
    public static FlowingFluidObject<ForgeFlowingFluid> hostilium = register("molten_hostilium", 1500);
    public static FlowingFluidObject<ForgeFlowingFluid> mimicream = register("molten_mimicream", 80);

    private static FluidType.Properties hot(String name) {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
                .descriptionId(TinkersInnovation.makeDescriptionId("fluid", name))
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA);
    }

    private static FluidType.Properties cool(String name) {
        return cool().descriptionId(TinkersInnovation.makeDescriptionId("fluid", name))
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
    }

    private static FluidType.Properties cool() {
        return FluidType.Properties.create()
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
    }

    private static FlowingFluidObject<ForgeFlowingFluid> register(String name, int temp) {
        return FLUIDS.register(name).type(hot(name).temperature(temp).lightLevel(12)).block(Material.LAVA, 12).bucket().flowing();
    }
}
