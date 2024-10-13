package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.library.entitys.entitys.efln_arrow.EEFLNArrow;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<EntityType<EBomb>> BOMB_ENTITY = ENTITY_TYPES.register("tinker_bomb", ()-> EntityType.Builder.of(((EntityType<EBomb> type, Level level) -> new EBomb(type, level)), MobCategory.MISC).sized(0.5f, 0.5f).build(TinkersInnovation.getResource("tinker_bomb").toString()));
    public static final RegistryObject<EntityType<EEFLNArrow>> EFLN_ARROW_ENTITY = ENTITY_TYPES.register("efln_arrow", ()-> EntityType.Builder.of(((EntityType<EEFLNArrow> type, Level level) -> new EEFLNArrow(type, level)), MobCategory.MISC).sized(0.5f, 0.5f).build(TinkersInnovation.getResource("efln_arrow").toString()));
}
