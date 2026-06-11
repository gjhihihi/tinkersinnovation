package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.fluid.InvertedFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.common.registration.FluidDeferredRegisterExtension;
import slimeknights.tconstruct.fluids.block.MobEffectLiquidBlock;
import slimeknights.tconstruct.fluids.fluids.SlimeFluid;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;


public class TinkersInnovationFluids {
    public static final FluidDeferredRegisterExtension FLUIDS = new FluidDeferredRegisterExtension(MOD_ID);

    public static FlowingFluidObject<ForgeFlowingFluid> polychrome_alloy = FLUIDS
            .registerMetal("molten_polychrome_alloy")
            .type(hot("molten_polychrome_alloy").temperature(1200))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> experience = FLUIDS
            .registerGlass("molten_experience")
            .type(hot("molten_experience").temperature(600))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> andesite_alloy = FLUIDS
            .registerMetal("molten_andesite_alloy")
            .type(hot("molten_andesite_alloy").temperature(900))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> void_crystal = FLUIDS
            .registerGem("molten_void_crystal")
            .type(hot("molten_void_crystal").temperature(600))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> enchantment_essence = FLUIDS
            .registerGlass("molten_enchantment_essence")
            .type(hot("molten_enchantment_essence").temperature(600))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> totemic_gold = FLUIDS
            .registerMetal("molten_totemic_gold")
            .type(hot("molten_totemic_gold").temperature(900))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> poseidite = FLUIDS
            .registerMetal("molten_poseidite")
            .type(hot("molten_poseidite").temperature(1000))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> decline = FLUIDS
            .registerMetal("molten_decline")
            .type(hot("molten_decline").temperature(1200))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> slimton = FLUIDS
            .registerMetal("molten_slimton")
            .type(hot("molten_slimton").temperature(1000))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> shulkerate = FLUIDS
            .registerMetal("molten_shulkerate")
            .type(hot("molten_shulkerate").temperature(1500))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> sculkium = FLUIDS
            .registerMetal("molten_sculkium")
            .type(hot("molten_sculkium").temperature(1000))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> eternium = FLUIDS
            .registerMetal("molten_eternium")
            .type(hot("molten_eternium").temperature(1200))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> chaos = FLUIDS
            .registerMetal("molten_chaos")
            .type(hot("molten_chaos").temperature(1000))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> miracle = FLUIDS
            .registerMetal("molten_miracle")
            .type(hot("molten_miracle").temperature(1150))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> hostility = FLUIDS
            .register("molten_hostility")
            .type(hot("molten_hostility").temperature(1200))
            .block(MapColor.COLOR_RED, 0).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> hostilium = FLUIDS
            .registerMetal("molten_hostilium")
            .type(hot("molten_hostilium").temperature(1750))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<SlimeFluid> mimicream = FLUIDS
            .registerSlime("mimicream")
            .type(slime("mimicream").temperature(80))
            .block(MapColor.COLOR_RED, 0).bucket().flowing(SlimeFluid.Source::new, SlimeFluid.Flowing::new);
    public static FlowingFluidObject<ForgeFlowingFluid> fools_gold = FLUIDS
            .registerMetal("molten_fools_gold")
            .type(hot("molten_fools_gold").temperature(950))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> ruby = FLUIDS
            .registerGem("molten_ruby")
            .type(hot("molten_ruby").temperature(1200))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> sapphire = FLUIDS
            .registerGem("molten_sapphire")
            .type(hot("molten_sapphire").temperature(1150))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> farseeing_alloy = FLUIDS
            .registerMetal("molten_farseeing_alloy")
            .type(hot("molten_farseeing_alloy").temperature(1650))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> blood = FLUIDS
            .register("blood")
            .type(hot("blood").temperature(50))
            .block(MapColor.COLOR_RED, 0).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> hemolymph = FLUIDS
            .register("hemolymph")
            .type(hot("hemolymph").temperature(75))
            .block(MapColor.COLOR_RED, 0).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> blazing_soul = FLUIDS
            .register("blazing_soul")
            .type(hot("blazing_soul").temperature(2000))
            .block(MobEffectLiquidBlock.createEffect(MapColor.COLOR_RED, 15,
                    () -> new MobEffectInstance(TinkersInnovationEffects.soulDevouringEffect.get(), 100)
            )).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> capsid = FLUIDS
            .register("molten_capsid")
            .type(hot("molten_capsid").temperature(500))
            .block(MapColor.COLOR_RED, 0).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> sunsoul_alloy = FLUIDS
            .registerMetal("molten_sunsoul_alloy")
            .type(hot("molten_sunsoul_alloy").temperature(3200))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> straddlite = FLUIDS
            .registerGem("molten_straddlite")
            .type(hot("molten_straddlite").temperature(1050))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> straddlite_alloy = FLUIDS
            .registerMetal("molten_straddlite_alloy")
            .type(hot("molten_straddlite_alloy").temperature(1500))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<ForgeFlowingFluid> mudslime = FLUIDS
            .registerMetal("molten_mudslime")
            .type(hot("molten_mudslime").temperature(560))
            .block(MapColor.COLOR_RED, 12).bucket().flowing();
    public static FlowingFluidObject<InvertedFluid> lightning = FLUIDS
            .register("lightning")
            .type(hot("lightning").temperature(2500))
            .block(MobEffectLiquidBlock.createEffect(MapColor.COLOR_RED, 15,
                    () -> new MobEffectInstance(TinkersInnovationEffects.thunderTribulationEffect.get(), 100)
            )).bucket().invertedFlowing();

    private static FluidType.Properties hot(String name) {
        return FluidType.Properties.create()
                .density(2000).viscosity(10000).temperature(1000).lightLevel(12)
                .motionScale(0.007 / 3).canSwim(false).canDrown(false).pathType(BlockPathTypes.LAVA).adjacentPathType(null)
                .descriptionId(TinkersInnovation.makeDescriptionId("fluid", name))
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA);
    }

    private static FluidType.Properties cool(String name) {
        return FluidType.Properties.create()
                .motionScale(0.007 / 3).canExtinguish(true)
                .descriptionId(TinkersInnovation.makeDescriptionId("fluid", name))
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
    }

    private static FluidType.Properties slime(String name) {
        return cool(name).density(1600).viscosity(1600);
    }
}
