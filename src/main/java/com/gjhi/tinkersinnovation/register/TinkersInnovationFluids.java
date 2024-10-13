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
    public static FlowingFluidObject<ForgeFlowingFluid> hostilium = register("molten_hostilium", 1750);
    public static FlowingFluidObject<ForgeFlowingFluid> mimicream = register("molten_mimicream", 80);
    public static FlowingFluidObject<ForgeFlowingFluid> gorgon = register("molten_gorgon", 800);
    public static FlowingFluidObject<ForgeFlowingFluid> fools_gold = register("molten_fools_gold", 950);
    public static FlowingFluidObject<ForgeFlowingFluid> void_worm = register("molten_void_worm", 1000);
    public static FlowingFluidObject<ForgeFlowingFluid> farseer = register("molten_farseer", 1250);
    public static FlowingFluidObject<ForgeFlowingFluid> ruby = register("molten_ruby", 1200);
    public static FlowingFluidObject<ForgeFlowingFluid> sapphire = register("molten_sapphire", 1150);
    public static FlowingFluidObject<ForgeFlowingFluid> farseeing_alloy = register("molten_farseeing_alloy", 1650);
    public static FlowingFluidObject<ForgeFlowingFluid> blood = register("molten_blood", 50);
    public static FlowingFluidObject<ForgeFlowingFluid> hemolymph = register("molten_hemolymph", 75);
    public static FlowingFluidObject<ForgeFlowingFluid> active_soul = register("molten_active_soul", 2000);
    public static FlowingFluidObject<ForgeFlowingFluid> capsid = register("molten_capsid", 500);
    public static FlowingFluidObject<ForgeFlowingFluid> sun = register("molten_sun", 5000);
    public static FlowingFluidObject<ForgeFlowingFluid> sunsoul_alloy = register("molten_sunsoul_alloy", 3200);
    public static FlowingFluidObject<ForgeFlowingFluid> straddlite = register("molten_straddlite", 1050);
    public static FlowingFluidObject<ForgeFlowingFluid> straddlite_alloy = register("molten_straddlite_alloy", 1500);

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

    private static FlowingFluidObject<ForgeFlowingFluid> register(String name, int temperature) {
        return FLUIDS.register(name).type(hot(name).temperature(temperature).lightLevel(12)).block(Material.LAVA, 12).bucket().flowing();
    }
}
