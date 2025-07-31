package com.gjhi.tinkersinnovation.register;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TinkersInnovationUtils {
    protected static Random RANDOM = new Random();
    public static boolean isInArmorSlots(EquipmentSlot slot){
        return slot.getType().equals(EquipmentSlot.Type.ARMOR);
    }
    public static boolean isInHandSlots(EquipmentSlot slot){
        return slot.getType().equals(EquipmentSlot.Type.HAND);
    }
    public static boolean isInArmorSlots(LivingEntity entity, ItemStack item){
        for (ItemStack i : entity.getArmorSlots()){
            if (i.equals(item)){
                return true;
            }
        }
        return false;
    }
    public static boolean isInHandSlots(LivingEntity entity, ItemStack item){
        for (ItemStack i : entity.getHandSlots()){
            if (i.equals(item)){
                return true;
            }
        }
        return false;
    }
    public static boolean isShieldInHandSlots(IToolStackView tool, EquipmentSlot slot){
        return tool.hasTag(TinkerTags.Items.SHIELDS) && isInHandSlots(slot);
    }
    public static boolean isShieldInHandSlots(IToolStackView tool, LivingEntity entity, ItemStack item){
        return tool.hasTag(TinkerTags.Items.SHIELDS) && isInHandSlots(entity, item);
    }
    public static boolean isInSlots(EquipmentSlot slot){
        return isInHandSlots(slot) || isInArmorSlots(slot);
    }
    public static boolean isInSlots(LivingEntity entity, ItemStack item){
        return isInHandSlots(entity, item) || isInArmorSlots(entity, item);
    }
    @Nullable
    public static EquipmentSlot inWhichSlot(LivingEntity entity, ItemStack item){
        for (EquipmentSlot slot : EquipmentSlot.values()){
            if (entity.getItemBySlot(slot).equals(item)){
                return slot;
            }
        }
        return null;
    }
    @Nullable
    public static ToolStack getToolFromHand(LivingEntity entity){
        ItemStack mainItem = entity.getMainHandItem();
        ItemStack offItem = entity.getOffhandItem();
        ItemStack stack = mainItem.isEmpty() ? (offItem.isEmpty() ? null : offItem) : mainItem;
        return stack != null ? (ToolStack.from(stack).getDefinition() != ToolDefinition.EMPTY ? ToolStack.from(stack) : null) : null;
    }
    public static void updateEffect(LivingEntity entity, MobEffect effect, int addLevel, int maxLevel, int time){
        MobEffectInstance mobeffect = entity.getEffect(effect);
        if (mobeffect != null){
            entity.addEffect(new MobEffectInstance(effect, time, Math.min(mobeffect.getAmplifier() + addLevel, maxLevel - 1)));
        }else {
            entity.addEffect(new MobEffectInstance(effect, time, Math.min(addLevel - 1, maxLevel - 1)));
        }
    }

    public static List<LivingEntity> getLivingEntitiesInRange(Entity center, double range, boolean includeCenter) {
        List<LivingEntity> list = center.level().getEntitiesOfClass(LivingEntity.class, center.getBoundingBox().inflate(range), (entity) -> entity instanceof LivingEntity);
        if (!includeCenter && center instanceof LivingEntity) {
            list.remove(center);
        }
        return list;
    }
    public static List<Player> getPlayersInRange(Entity center, double range, boolean includeCenter) {
        List<Player> list = center.level().getEntitiesOfClass(Player.class, center.getBoundingBox().inflate(range), (entity) -> entity instanceof Player);
        if (!includeCenter && center instanceof Player) {
            list.remove(center);
        }
        return list;
    }
    public static List<Monster> getMonstersInRange(Entity center, double range, boolean includeCenter) {
        List<Monster> list = center.level().getEntitiesOfClass(Monster.class, center.getBoundingBox().inflate(range), (entity) -> entity instanceof Monster);
        if (!includeCenter && center instanceof Monster) {
            list.remove(center);
        }
        return list;
    }

    @Nullable
    public static <T> T getRandomInList(List<T> list) {
        return list.isEmpty()? null: list.get(RANDOM.nextInt(list.size()));
    }
    public static float manhattanDistance(float x1, float y1, float z1, float x2, float y2, float z2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2);
    }
    @SafeVarargs
    public static <T> List<T> without(List<T> origin, T...members){
        List<T> list = new ArrayList<>(origin);
        for (T member : members){
            list.remove(member);
        }
        return list;
    }
    public static boolean noMixinDirectDamage(IToolStackView tool, int amount, @Nullable LivingEntity entity, @Nullable ItemStack stack) {
        if (entity instanceof Player && ((Player)entity).isCreative()) {
            return false;
        } else {
            int durability = tool.getStats().getInt(ToolStats.DURABILITY);
            int damage = tool.getDamage();
            int current = durability - damage;
            amount = Math.min(amount, current);
            if (amount > 0) {
                int newDamage = damage + amount;
                if (entity instanceof ServerPlayer) {
                    if (stack == null) {
                        stack = entity.getMainHandItem();
                    }
                    CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger((ServerPlayer)entity, stack, newDamage);
                }
                tool.setDamage(newDamage);
                return newDamage >= durability;
            } else {
                return false;
            }
        }
    }
    public static boolean spawnLightningBolt(Level world, int x, int y, int z, MobSpawnType type, float damage){
        if (world instanceof ServerLevel level) {
            LightningBolt flash = EntityType.LIGHTNING_BOLT.spawn(level, new BlockPos(x, y, z), type);
            if (flash != null) {
                flash.setDamage(damage);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static boolean spawnLightningBolt(Entity entity, MobSpawnType type, float damage){
        return spawnLightningBolt(entity.level(), entity.getBlockX(), entity.getBlockY(), entity.getBlockZ(), type, damage);
    }
    public static boolean spawnLightningBolt(Level world, int x, int y, int z, MobSpawnType type){
        return spawnLightningBolt(world, x, y, z, type, getLightningBoltDamage());
    }
    public static boolean spawnLightningBolt(Entity entity, MobSpawnType type){
        return spawnLightningBolt(entity.level(), entity.getBlockX(), entity.getBlockY(), entity.getBlockZ(), type, getLightningBoltDamage());
    }
    public static float getLightningBoltDamage(){
        return 5;
    }
}
