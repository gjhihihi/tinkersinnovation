package com.gjhi.tinkersinnovation.register.tools;

import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.json.predicate.modifier.SingleModifierPredicate;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.definition.module.ToolModuleHooks;
import slimeknights.tconstruct.library.tools.definition.module.interaction.PreferenceSetInteraction;
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
                .multiplier(ToolStats.DURABILITY, 2.3f)
                .startingSlots(SlotType.ABILITY, 1)
                .startingSlots(SlotType.UPGRADE, 4)
        ;
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
                .stat(ToolStats.BLOCK_AMOUNT, 100.0F)
                .stat(ToolStats.BLOCK_ANGLE, 180.0F)
                .stat(ToolStats.ARMOR_TOUGHNESS, 2.0F)
                .startingSlots(SlotType.UPGRADE, 1)
                .startingSlots(SlotType.DEFENSE, 4)
                .startingSlots(SlotType.ABILITY, 1)
                .trait(TinkerModifiers.blocking)
                .module(ToolModuleHooks.INTERACTION, new PreferenceSetInteraction(InteractionSource.RIGHT_CLICK, new SingleModifierPredicate(TinkerModifiers.blocking.getId())))
                //.action(ToolActions.SHIELD_BLOCK)
        ;
        define(TinkersInnovationToolsDefinition.ButcherKnife)
                //part
                .part(TinkerToolParts.broadBlade)
                .part(TinkerToolParts.largePlate)
                .part(TinkerToolParts.toughHandle)
                //stat
                .stat(ToolStats.ATTACK_DAMAGE, 2.5f)
                .stat(ToolStats.ATTACK_SPEED, 1.0f)
                .multiplier(ToolStats.MINING_SPEED, 0.7f)
                .multiplier(ToolStats.ATTACK_DAMAGE, 0.5f)
                .multiplier(ToolStats.ATTACK_SPEED, 1.6f)
                .multiplier(ToolStats.DURABILITY, 2.3f)
        ;
    }

    @Override
    public String getName() {
        return "Tinkers Innovation Tool Definitions";
    }
}
