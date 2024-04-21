package com.gjhi.tinkersinnovation.modifiers;

import com.google.j2objc.annotations.ReflectionSupport;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

public class ShadowModifier extends Modifier implements ProjectileHitModifierHook {
    private static int experience_size = 0;
    private static final TinkerDataCapability.TinkerDataKey<Integer> EXPERIENCED = TConstruct.createKey("experienced");
    private int boost(int get, int size){
        return get+size*2;
    }
    public ShadowModifier() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onExperienceDrop);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onEntityKilled);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::beforeBlockBreak);
    }

    private void beforeBlockBreak(BreakEvent event) {
        // only support main hand block breaking currently
        int level = 0;
        ToolStack tool = getHeldTool(event.getPlayer(), InteractionHand.MAIN_HAND);
        if (tool != null) {
            level = tool.getModifierLevel(this);
        }
        if (level > 0) {
            experience_size = 2 * level;
            event.setExpToDrop(boost(event.getExpToDrop(), experience_size));
        }
    }
    private void onEntityKilled(LivingDeathEvent event) {
        DamageSource source = event.getSource();
        if (source != null && source.getDirectEntity() instanceof Projectile projectile) {
            ModifierNBT modifiers = EntityModifierCapability.getOrEmpty(projectile);
            // it is very unlikely that we fire an arrow on a bow with no modifiers, if that ever happens though we will not be able to identify its our arrow
            if (!modifiers.isEmpty()) {
                event.getEntityLiving().getCapability(TinkerDataCapability.CAPABILITY).ifPresent(data -> data.put(EXPERIENCED, modifiers.getLevel(this.getId())));
            }
        }
    }

    private void onExperienceDrop(LivingExperienceDropEvent event) {
        // if the entity was killed by one of our arrows, boost the experience from that
        int experienced = event.getEntityLiving().getCapability(TinkerDataCapability.CAPABILITY).resolve().map(data -> data.get(EXPERIENCED)).orElse(-1);
        if (experienced > 0) {
            event.setDroppedExperience(boost(event.getDroppedExperience(), experienced+experience_size));
            // experienced being zero means it was our arrow but it was not modified, do not check the held item in that case
        } else if (experienced != 0) {
            Player player = event.getAttackingPlayer();
            if (player != null) {
                event.setDroppedExperience(boost(event.getDroppedExperience(), experience_size));
            }
        }
    }

    @Override
    protected void registerHooks(Builder hookBuilder){
        hookBuilder.addHook(this, TinkerHooks.PROJECTILE_HIT);
    }
    @Override
    public int getPriority() {
        return 20;
    }
    @Override
    public @NotNull List<ItemStack> processLoot(@NotNull IToolStackView tool, int level, List<ItemStack> generatedLoot, @NotNull LootContext context) {
        experience_size = generatedLoot.size()*level;
        generatedLoot.clear();
        return generatedLoot;
    }
}
