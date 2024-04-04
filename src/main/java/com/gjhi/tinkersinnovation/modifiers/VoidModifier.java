package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerCommons;

public class VoidModifier extends NoLevelsModifier implements ProjectileHitModifierHook {
    @Override
    public int getPriority() {
        return 120;
    }
    @Override
    public Boolean removeBlock(IToolStackView tool, int level, ToolHarvestContext context) {
        Block block =context.getState().getBlock();
        if (block.equals(Blocks.BEDROCK)){
            return true;
        }
        return null;
    }

}