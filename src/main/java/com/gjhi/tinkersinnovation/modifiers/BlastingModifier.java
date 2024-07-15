package com.gjhi.tinkersinnovation.modifiers;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class BlastingModifier extends NoLevelsModifier implements MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }
    public BlastingModifier(){
        MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
    }

    private void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
            ToolStack tool = getHeldTool(event.getEntity(), InteractionHand.MAIN_HAND);
            if (tool != null && tool.getModifier(this).getLevel() > 0){
                event.getEntity().level.explode(null, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), 4, false, Explosion.BlockInteraction.BREAK);
                if (!event.getEntity().isCreative())
                    ToolDamageUtil.damage(tool, 10, event.getEntity(), null);
            }
    }

    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        LivingEntity target = context.getLivingTarget();
        Player player = context.getPlayerAttacker();
        if (target != null && !tool.isBroken()){
            target.level.explode(target, target.getX(), target.getY(), target.getZ(), 2, false, Explosion.BlockInteraction.NONE);
            if (player != null && !player.isCreative())
                ToolDamageUtil.damage(tool, 10, context.getAttacker(), context.getAttacker().getItemInHand(context.getHand()));
        }
        return knockback;
    }
}
