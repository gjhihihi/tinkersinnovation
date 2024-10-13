package com.gjhi.tinkersinnovation.library.items.tinker_bomb;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableLauncherItem;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.function.Predicate;

public class TinkerBombItem extends ModifiableLauncherItem {
    public TinkerBombItem(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack item) {
        return UseAnim.NONE;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ProjectileWeaponItem.ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 10;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        ToolStack tool = ToolStack.from(itemStack);
        ModDataNBT data = tool.getPersistentData();
        float velocity;
        if (!tool.isBroken()){
            velocity = ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.VELOCITY);
            float power = 0.75F * velocity;
            if(!level.isClientSide){
                ItemStack ammo = IBomb.setAmmo();
                Item item = ammo.getItem();
                ArrowItem arrowItem;
                if (item instanceof ArrowItem arr){
                    arrowItem = arr;
                }else {
                    arrowItem = (ArrowItem) Items.ARROW;
                }
                float inaccuracy = ModifierUtil.getInaccuracy(tool, player);
                float startAngle = getAngleStart(ammo.getCount());
                int primaryIndex = ammo.getCount() / 2;

                for(int arrowIndex = 0; arrowIndex < ammo.getCount(); ++arrowIndex) {
                    AbstractArrow arrow = arrowItem.createArrow(level, ammo, player);
                    float angle = startAngle + (float)(10 * arrowIndex);
                    arrow.shootFromRotation(player, player.getXRot() + angle, player.getYRot(), 0.0F, power * 3.0F, inaccuracy);
                    float baseArrowDamage = (float)(arrow.getBaseDamage() - 2.0 + (double) tool.getStats().get(ToolStats.PROJECTILE_DAMAGE));
                    arrow.setBaseDamage(ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.PROJECTILE_DAMAGE, baseArrowDamage));
                    ModifierNBT modifiers = tool.getModifiers();
                    arrow.getCapability(EntityModifierCapability.CAPABILITY).ifPresent((cap) -> {
                        cap.setModifiers(modifiers);
                    });
                    NamespacedNBT arrowData = PersistentDataCapability.getOrWarn(arrow);

                    for (ModifierEntry entry : modifiers.getModifiers()) {
                        entry.getHook(ModifierHooks.PROJECTILE_LAUNCH).onProjectileLaunch(tool, entry, player, arrow, arrow, arrowData, arrowIndex == primaryIndex);
                    }

                    level.addFreshEntity(arrow);
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT  , SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) * 0.5F + angle / 10.0F);
                }
                ToolDamageUtil.damageAnimated(tool, ammo.getCount() * 10, player, hand);
                player.getCooldowns().addCooldown(this, 20);
                return InteractionResultHolder.consume(itemStack);
            }
        }else {
            return InteractionResultHolder.fail(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }
}
