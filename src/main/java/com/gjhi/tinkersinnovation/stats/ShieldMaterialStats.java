package com.gjhi.tinkersinnovation.stats;

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

public record ShieldMaterialStats(int durability, float armor, float toughness, float knockback_resistance, float block_amount, int shield_amount) implements IRepairableMaterialStats {
    public static final MaterialStatsId ID = new MaterialStatsId(TinkersInnovation.getResource("shield_plate"));
    public static final MaterialStatType<ShieldMaterialStats> TYPE = new MaterialStatType(ID, new ShieldMaterialStats(1, 0, 0, 0, 0, 1), RecordLoadable.create(IRepairableMaterialStats.DURABILITY_FIELD,FloatLoadable.FROM_ZERO.defaultField("armor",0f, true, ShieldMaterialStats::armor),FloatLoadable.FROM_ZERO.defaultField("toughness", 0f, true, ShieldMaterialStats::toughness),FloatLoadable.FROM_ZERO.defaultField("knockback_resistance", 0f, true, ShieldMaterialStats::knockback_resistance),FloatLoadable.ANY.defaultField("block_amount", 0f, true, ShieldMaterialStats::block_amount), IntLoadable.FROM_ZERO.defaultField("shield_amount", 5, true, ShieldMaterialStats::shield_amount), ShieldMaterialStats::new));
    private static final List<Component> DESCRIPTION = ImmutableList.of(ToolStats.DURABILITY.getDescription(), ToolStats.ARMOR.getDescription(), ToolStats.ARMOR_TOUGHNESS.getDescription(), ToolStats.KNOCKBACK_RESISTANCE.getDescription(), ToolStats.BLOCK_AMOUNT.getDescription(), TinkersInnovationToolStats.SHIELD_AMOUNT.getDescription());
    public ShieldMaterialStats(int durability, float armor, float toughness, float knockback_resistance, float block_amount, int shield_amount) {
        this.durability = durability;
        this.armor = armor;
        this.toughness = toughness;
        this.knockback_resistance = knockback_resistance;
        this.block_amount = block_amount;
        this.shield_amount = shield_amount;
    }
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
        info.add(ToolStats.ARMOR.formatValue((float)this.armor));
        info.add(ToolStats.ARMOR_TOUGHNESS.formatValue(this.toughness));
        info.add(ToolStats.KNOCKBACK_RESISTANCE.formatValue(this.knockback_resistance));
        info.add(ToolStats.BLOCK_AMOUNT.formatValue(this.block_amount));
        info.add(TinkersInnovationToolStats.SHIELD_AMOUNT.formatValue(this.shield_amount));
        return info;
    }

    @Override
    public @NotNull List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {
        ToolStats.DURABILITY.update(builder, (float)this.durability * scale);
        ToolStats.ARMOR.update(builder, this.armor * scale);
        ToolStats.ARMOR_TOUGHNESS.update(builder, this.toughness * scale);
        ToolStats.KNOCKBACK_RESISTANCE.update(builder, this.knockback_resistance * scale);
        ToolStats.BLOCK_AMOUNT.percent(builder, this.block_amount * scale);
        TinkersInnovationToolStats.SHIELD_AMOUNT.update(builder, (float) this.shield_amount * scale);
    }

    @Override
    public float armor() {
        return armor;
    }
    @Override
    public float toughness() {
        return toughness;
    }
}
