package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.Iterator;


public class DilapidationModifier extends Modifier implements BreakSpeedModifierHook, BlockBreakModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.BREAK_SPEED, ModifierHooks.BLOCK_BREAK);
    }

    private final ResourceLocation X = new ResourceLocation(TinkersInnovation.MOD_ID, "dilapidation_x");
    private final ResourceLocation Y = new ResourceLocation(TinkersInnovation.MOD_ID, "dilapidation_y");
    private final ResourceLocation Z = new ResourceLocation(TinkersInnovation.MOD_ID, "dilapidation_z");
    private final ResourceLocation WORLD = new ResourceLocation(TinkersInnovation.MOD_ID, "dilapidation_dimension");

    @Override
    public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (event.getPosition().isPresent()) {
            String world = event.getEntity().level.dimension().location().getPath();
            BlockPos pos = event.getPosition().get();
            ModDataNBT data = tool.getPersistentData();
            if (data.contains(X, Tag.TAG_FLOAT) && data.contains(Y, Tag.TAG_FLOAT) && data.contains(Z, Tag.TAG_FLOAT) && data.contains(WORLD, Tag.TAG_STRING)) {
                if (data.getString(WORLD).equals(world))
                    if (TinkersInnovationUtils.manhattanDistance(data.getFloat(X), data.getFloat(Y), data.getFloat(Z), pos.getX(), pos.getY(), pos.getZ()) <= 1 + modifier.getLevel()) {
                        event.setNewSpeed(1.5f * event.getNewSpeed());
                    }
            }
        }
    }
    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        BlockPos pos = context.getTargetedPos();
        ModDataNBT data = tool.getPersistentData();
        data.putFloat(X, pos.getX());
        data.putFloat(Y, pos.getY());
        data.putFloat(Z, pos.getZ());
        data.putString(WORLD, context.getLiving().level.dimension().location().getPath());
    }
}
