package com.gjhi.tinkersinnovation.events;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.library.entitys.render.efln_arrow.REFLNArrow;
import com.gjhi.tinkersinnovation.library.entitys.render.tinker_bomb.RBomb;
import com.gjhi.tinkersinnovation.register.TinkersInnovationEntityTypes;
import com.gjhi.tinkersinnovation.register.TinkersInnovationMaterials;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.tools.client.SlimeskullArmorModel;
import slimeknights.tconstruct.world.TinkerHeadType;
import slimeknights.tconstruct.world.client.SkullModelHelper;

import java.util.function.Supplier;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        value = {Dist.CLIENT},
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class WorldClientEvents {

    @SubscribeEvent
    static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            SlimeskullArmorModel.registerHeadModel(TinkersInnovationMaterials.gorgon_hair.getId(), makeSkullName("gorgon"), TinkersInnovation.getResource("textures/entity/skull/gorgon.png"));
        });
    }
    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterLayerDefinitions event){
        Supplier<LayerDefinition> normalHead = Lazy.of(SkullModel::createMobHeadLayer);
        event.registerLayerDefinition(makeSkullName("gorgon"), normalHead);
    }
    @SubscribeEvent
    static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(TinkersInnovationEntityTypes.BOMB_ENTITY.get(), RBomb::new);
        event.registerEntityRenderer(TinkersInnovationEntityTypes.EFLN_ARROW_ENTITY.get(), REFLNArrow::new);
    }
    private static ModelLayerLocation makeSkullName(String name){
        return new ModelLayerLocation(TinkersInnovation.getResource(name + "_head"), "main");
    }
}
