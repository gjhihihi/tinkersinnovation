package com.gjhi.tinkersinnovation.tools;

import com.gjhi.tinkersinnovation.TinkersInnovationItems;
import com.gjhi.tinkersinnovation.TinkersInnovationModifiers;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerToolParts;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

public class TinkersInnovationToolsDefinitionDataProvider extends AbstractToolDefinitionDataProvider {
    public TinkersInnovationToolsDefinitionDataProvider(DataGenerator generator) {
        super(generator, MOD_ID);
    }

    @Override
    protected void addToolDefinitions() {
        define(TinkersInnovationToolsDefinition.PickDart)
                //part
                .part(TinkerToolParts.pickHead)
                .part(TinkerToolParts.smallBlade)
                .part(TinkerToolParts.smallBlade)
                .part(TinkersInnovationItems.handguard)
                //stat
                .stat(ToolStats.ATTACK_DAMAGE, 2.5f)
                .stat(ToolStats.ATTACK_SPEED, 1.0f)
                .multiplier(ToolStats.MINING_SPEED, 0.7f)
                .multiplier(ToolStats.ATTACK_DAMAGE, 0.5f)
                .multiplier(ToolStats.ATTACK_SPEED, 1.6f)
                .multiplier(ToolStats.DURABILITY, 2.3f);
                //trait
        //        .trait(TinkerModifiers.knockback, 1)
                //other
        //        .action(ToolActions.SWORD_DIG);
        define(TinkersInnovationToolsDefinition.HeavyShield)
                //part
                .part(TinkerToolParts.largePlate)
                .part(TinkerToolParts.largePlate)
                .part(TinkersInnovationItems.shield_plate)
                .part(TinkerToolParts.toolHandle)
                //stat
                .stat(ToolStats.ATTACK_DAMAGE, 2.5f)
                .stat(ToolStats.ATTACK_SPEED, 1.0f)
                .multiplier(ToolStats.MINING_SPEED, 0.7f)
                .multiplier(ToolStats.ATTACK_DAMAGE, 0.5f)
                .multiplier(ToolStats.ATTACK_SPEED, 1.6f)
                .multiplier(ToolStats.DURABILITY, 2.3f)
                .trait(TinkersInnovationModifiers.falling_attack, 1);
    }

    @Override
    public String getName() {
        return "Tinkers Innovation Tool Definitions";
    }
}
