package com.gjhi.tinkersinnovation.library.stats;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public record BombCoreStats(float durability, float bomb_radius, float velocity, float accuracy) implements IMaterialStats {
    public static final MaterialStatsId ID = new MaterialStatsId(TinkersInnovation.getResource("bomb_core"));
    public static final MaterialStatType<ShieldMaterialStats> TYPE = new MaterialStatType(ID, new BombCoreStats(0, 1, 0, 0), RecordLoadable.create(FloatLoadable.ANY.defaultField("durability",0f, true, BombCoreStats::durability), FloatLoadable.FROM_ZERO.defaultField("bomb_radius",1f, true, BombCoreStats::bomb_radius), FloatLoadable.ANY.defaultField("velocity",0f, true, BombCoreStats::velocity), FloatLoadable.ANY.defaultField("accuracy",0f, true, BombCoreStats::accuracy), BombCoreStats::new));
    private static final List<Component> DESCRIPTION = ImmutableList.of(ToolStats.DURABILITY.getDescription(), TinkersInnovationToolStats.BOMB_RADIUS.getDescription(), ToolStats.VELOCITY.getDescription(), ToolStats.ACCURACY.getDescription());


    @Override
    public MaterialStatType<?> getType() {
        return TYPE;
    }

    @Override
    public List<Component> getLocalizedInfo() {
        List<Component> info = Lists.newArrayList();
        info.add(ToolStats.DURABILITY.formatValue(this.durability));
        info.add(TinkersInnovationToolStats.BOMB_RADIUS.formatValue(this.bomb_radius));
        info.add(ToolStats.VELOCITY.formatValue(this.velocity));
        info.add(ToolStats.ACCURACY.formatValue(this.accuracy));
        return info;
    }

    @Override
    public @NotNull List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {
        ToolStats.DURABILITY.percent(builder, this.durability * scale);
        TinkersInnovationToolStats.BOMB_RADIUS.update(builder, this.bomb_radius * scale);
        ToolStats.VELOCITY.percent(builder, this.velocity * scale);
        ToolStats.ACCURACY.percent(builder, this.accuracy * scale);
    }

}
