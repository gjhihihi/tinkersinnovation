package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.Mantle;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.mantle.datagen.MantleTags;
import slimeknights.mantle.registration.MantleRegistrations;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.*;

import java.util.UUID;
import java.util.function.BiConsumer;

import static slimeknights.tconstruct.tools.data.material.MaterialIds.manyullyn;

public class PoseiditeWeaponModifier extends NoLevelsModifier implements MeleeDamageModifierHook, AttributesModifierHook,InventoryTickModifierHook, ModifierRemovalHook {

    private final ResourceLocation KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "poseidite_weapon");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.INVENTORY_TICK, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE);
    }
    @Override
    public float getMeleeDamage(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        Player player = context.getPlayerAttacker();
        if (player != null && target != null){
            if (target.getMobType().equals(MobType.WATER) || target instanceof Blaze || target instanceof EnderMan || target instanceof SnowGolem)
                damage *= 2;
        }
        return damage;
    }
    @Override
    public void onInventoryTick (@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){
        if (!world.isClientSide && holder.getUseItem() != stack && isSelected) {
            ModDataNBT persistentData = tool.getPersistentData();
                if (holder.isInWaterOrBubble()) {
                    persistentData.putBoolean(KEY, true);
                }else {
                    persistentData.putBoolean(KEY, false);
                }
        }
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (slot == EquipmentSlot.MAINHAND) {
            if (tool.getPersistentData().getBoolean(KEY)) {
                consumer.accept(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("2d27092b-2533-426c-b31e-9a362b13807b"), Attributes.ATTACK_SPEED.getDescriptionId(), 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
                consumer.accept(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("17daf24e-2aa8-4aa0-abde-ae5abd848d2e"), Attributes.ATTACK_DAMAGE.getDescriptionId(), 0.5, AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
    }
}
