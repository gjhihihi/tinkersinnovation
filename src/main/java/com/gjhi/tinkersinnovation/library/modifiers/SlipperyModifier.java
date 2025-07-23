package com.gjhi.tinkersinnovation.library.modifiers;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;

public class SlipperyModifier extends Modifier implements ToolDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_DAMAGE);
    }

    public void spawnSlime(Entity summoner) {
        Slime slime = EntityType.SLIME.create(summoner.level);
        if (slime != null) {
            summoner.level.addFreshEntity(slime);
            int size;
            float ran = RANDOM.nextFloat();
            if (ran < 0.5){
                size = 1;
            } else if (ran < 0.85){
                size = 2;
            } else {
                size = 3;
            }
            slime.setSize(size, true);
            slime.moveTo(
                    summoner.getX() + RANDOM.nextFloat(-1, 1),
                    summoner.getY(),
                    summoner.getZ() + RANDOM.nextFloat(-1, 1)
            );
        }
    }

    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        if (holder != null && RANDOM.nextFloat() < 0.02){
            int level = modifier.getLevel();
            level = RANDOM.nextInt(level, 2 * level);
            //ModifierUtil.dropItem(holder, new ItemStack(Items.SLIME_BALL));
            for (int i = 0; i < level; i++){
                spawnSlime(holder);
            }
        }
        return amount;
    }
}
