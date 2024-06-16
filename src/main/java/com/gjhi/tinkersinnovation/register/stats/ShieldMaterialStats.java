package com.gjhi.tinkersinnovation.register.stats;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Tiers;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.json.TinkerLoadables;
import slimeknights.tconstruct.library.materials.stats.IRepairableMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import java.util.List;

public record ShieldMaterialStats(int durability, float armor, float toughness, float knockback_resistance, float block_amount, float shield_amount) implements IRepairableMaterialStats {
    public static final MaterialStatsId ID = new MaterialStatsId(TConstruct.getResource("shield"));
    //public static final MaterialStatType<ShieldMaterialStats> TYPE = new MaterialStatType(ID, new ShieldMaterialStats(1, 0, 0, 0, 0, 5), RecordLoadable.create(IRepairableMaterialStats.DURABILITY_FIELD, FloatLoadable.FROM_ZERO.defaultField("armor", 0f, true, ShieldMaterialStats::armor), FloatLoadable.FROM_ZERO.defaultField("armor_toughness", 0f, true, ShieldMaterialStats::toughness), FloatLoadable.FROM_ZERO.defaultField("armor_toughness", 0f, true, ShieldMaterialStats::toughness), ShieldMaterialStats::new));
    private static final List<Component> DESCRIPTION = ImmutableList.of(ToolStats.DURABILITY.getDescription(), ToolStats.ARMOR.getDescription(), ToolStats.ARMOR_TOUGHNESS.getDescription(), ToolStats.KNOCKBACK_RESISTANCE.getDescription(), ToolStats.BLOCK_AMOUNT.getDescription(), ToolStats.KNOCKBACK_RESISTANCE.getDescription());
    public ShieldMaterialStats(int durability, float armor, float toughness, float knockback_resistance, float block_amount, float shield_amount) {
        this.durability = durability;
        this.armor = armor;
        this.toughness = toughness;
        this.knockback_resistance = knockback_resistance;
        this.block_amount = block_amount;
        this.shield_amount = shield_amount;
    }
    @Override
    public int durability() {
        return 0;
    }

    @Override
    public MaterialStatType<?> getType() {
        return null;//TYPE;
    }

    @Override
    public List<Component> getLocalizedInfo() {
        List<Component> info = Lists.newArrayList();
        info.add(ToolStats.DURABILITY.formatValue((float)this.durability));
        info.add(ToolStats.ARMOR.formatValue((float)this.durability));
        info.add(ToolStats.ARMOR_TOUGHNESS.formatValue(this.armor));
        info.add(ToolStats.KNOCKBACK_RESISTANCE.formatValue(this.toughness));
        info.add(ToolStats.BLOCK_AMOUNT.formatValue(this.block_amount));
        //info.add(ToolStats.DURABILITY.formatValue((float)this.durability));
        return info;
    }

    @Override
    public List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {
        ToolStats.DURABILITY.update(builder, (float)this.durability * scale);
        ToolStats.ARMOR.update(builder, this.armor * scale);
        ToolStats.ARMOR_TOUGHNESS.update(builder, this.toughness * scale);
        ToolStats.KNOCKBACK_RESISTANCE.update(builder, this.knockback_resistance);
        ToolStats.BLOCK_AMOUNT.add(builder, this.block_amount * scale);
        //ToolStats.KNOCKBACK_RESISTANCE.update(builder, this.tier);
    }
}
