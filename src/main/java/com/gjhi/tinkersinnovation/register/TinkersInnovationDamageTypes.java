package com.gjhi.tinkersinnovation.register;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class TinkersInnovationDamageTypes {
    public static ResourceKey<DamageType> BERSERKER = create("berserker");
    public static ResourceKey<DamageType> BOMB_PIECE = create("tinker_bomb_piece");
    public static ResourceKey<DamageType> FARSEE = create("farsee");
    public static ResourceKey<DamageType> RESONANCE = create("resonance");
    public static ResourceKey<DamageType> VOID = create("void");
    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, TinkersInnovation.getResource(name));
    }
}
