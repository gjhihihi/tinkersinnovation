package com.gjhi.tinkersinnovation.library.stats;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.register.TinkersInnovationToolStats;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.primitive.IntLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.materials.stats.IRepairableMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public record BombShellStats(int durability, int piece_count, float piece_damage, float melee_range_damage) implements IRepairableMaterialStats {
    public static final MaterialStatsId ID = new MaterialStatsId(TinkersInnovation.getResource("bomb_shell"));
    public static final MaterialStatType<ShieldMaterialStats> TYPE = new MaterialStatType(ID, new BombShellStats(1, 1, 1, 1), RecordLoadable.create(IRepairableMaterialStats.DURABILITY_FIELD, IntLoadable.FROM_ZERO.defaultField("piece_count",1, true, BombShellStats::piece_count), FloatLoadable.FROM_ZERO.defaultField("piece_damage",1f, true, BombShellStats::piece_damage), FloatLoadable.FROM_ZERO.defaultField("melee_range_damage",1f, true, BombShellStats::melee_range_damage), BombShellStats::new));
    private static final List<Component> DESCRIPTION = ImmutableList.of(ToolStats.DURABILITY.getDescription(), TinkersInnovationToolStats.PIECE_COUNT.getDescription(), TinkersInnovationToolStats.PIECE_DAMAGE.getDescription(), ToolStats.ATTACK_DAMAGE.getDescription(), ToolStats.PROJECTILE_DAMAGE.getDescription());
    @Override
    public int durability() {
        return durability;
    }

    @Override
    public MaterialStatType<?> getType() {
        return TYPE;
    }

    @Override
    public List<Component> getLocalizedInfo() {
        List<Component> info = Lists.newArrayList();
        info.add(ToolStats.DURABILITY.formatValue((float)this.durability));
        info.add(TinkersInnovationToolStats.PIECE_COUNT.formatValue(this.piece_count));
        info.add(TinkersInnovationToolStats.PIECE_DAMAGE.formatValue(this.piece_damage));
        info.add(ToolStats.ATTACK_DAMAGE.formatValue(this.melee_range_damage));
        info.add(ToolStats.PROJECTILE_DAMAGE.formatValue(this.melee_range_damage));
        return info;
    }

    @Override
    public @NotNull List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {
        ToolStats.DURABILITY.update(builder, (float)this.durability * scale);
        TinkersInnovationToolStats.PIECE_COUNT.update(builder, (float) this.piece_count * scale);
        TinkersInnovationToolStats.PIECE_DAMAGE.update(builder, this.piece_damage * scale);
        ToolStats.ATTACK_DAMAGE.update(builder, this.melee_range_damage * scale);
        ToolStats.PROJECTILE_DAMAGE.update(builder, this.melee_range_damage * scale);
    }

}
