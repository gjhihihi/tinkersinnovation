package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.ArrayList;
import java.util.List;

public class TransmutationModifier extends Modifier implements InventoryTickModifierHook, ProcessLootModifierHook, ModifierRemovalHook {
    @Override
    public int getPriority() {
        return 80;
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.REMOVE, ModifierHooks.PROCESS_LOOT);
    }

    private final ResourceLocation TRAN_KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "transmutation");
    private final ResourceLocation EXP_KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "transmutation_explode");
    static final DamageSource TRANSMUTATION_DAMAGE = new DamageSource(TConstruct.prefix("transmutation")).bypassArmor().bypassMagic().bypassInvul();

    public void setExperienceLevel(IToolStackView tool, int value){
        tool.getPersistentData().putInt(TRAN_KEY, Math.max(value, 0));
    }
    public int getExperienceLevel(IToolStackView tool){
        return tool.getPersistentData().contains(TRAN_KEY, Tag.TAG_INT) ? tool.getPersistentData().getInt(TRAN_KEY) : 0;
    }
    public void addExperienceLevel(IToolStackView tool, int value){
        setExperienceLevel(tool, getExperienceLevel(tool) + value);
    }

    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        if (getExperienceLevel(tool) <= 10){
            int count = 0;
            List<ItemStack> list = new ArrayList<>();
            for (ItemStack stack : generatedLoot) {
                count += stack.getCount();
                if (stack.getItem().canFitInsideContainerItems()){
                    list.add(stack);
                }
            }
            addExperienceLevel(tool, count * modifier.getLevel());
            for (int i = 0; i < modifier.getLevel(); i++) {
                generatedLoot.addAll(list);
            }
        }
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        ModDataNBT data = tool.getPersistentData();
        if (!tool.isBroken()){
            data.putBoolean(EXP_KEY, true);
        }
        if (getExperienceLevel(tool) > 0 && holder instanceof Player player && player.experienceLevel > 0){
            int exp = Math.min(player.experienceLevel, getExperienceLevel(tool));
            player.experienceLevel -= exp;
            addExperienceLevel(tool, -exp);
            ToolDamageUtil.damage(tool, modifier.getLevel(), player, stack);
            if (RANDOM.nextFloat() < 0.05){
                MobEffectInstance effect = new MobEffectInstance(TinkerModifiers.selfDestructiveEffect.get(), 200 - 20 * (modifier.getLevel() - 1));
                effect.setCurativeItems(List.of(new ItemStack(Items.MILK_BUCKET)));
                holder.addEffect(effect);
            }
        }
        if (getExperienceLevel(tool) > 10){
            holder.hurt(TRANSMUTATION_DAMAGE, Integer.MAX_VALUE);
            setExperienceLevel(tool, 10);
        }
        if (tool.isBroken() && data.getBoolean(EXP_KEY)){
            holder.hurt(TRANSMUTATION_DAMAGE, Integer.MAX_VALUE);
            holder.level.explode(null, holder.getX(), holder.getY(), holder.getZ(), 2, Explosion.BlockInteraction.DESTROY);
            data.putBoolean(EXP_KEY, false);
        }
    }

    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }
}
