package com.gjhi.tinkersinnovation.events;

import com.gjhi.tinkersinnovation.register.TinkersInnovationEffects;
import com.gjhi.tinkersinnovation.register.TinkersInnovationFluids;
import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.shared.TinkerEffects;

import java.util.Random;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class WorldEvents {
    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();
        ItemStack item = player.getMainHandItem();
        if (player.hasEffect(TinkerEffects.bleeding.get()) && item.is(Items.GLASS_BOTTLE) && !player.getCooldowns().isOnCooldown(item.getItem())){
            item.shrink(1);
            ItemStack newitem = new ItemStack(TinkersInnovationItems.blood_bottle.get());
            Inventory inventory = player.getInventory();
            if (!inventory.add(newitem)){
                ModifierUtil.dropItem(player, newitem);
            }
            player.hurt(TinkerDamageTypes.source(player.level().registryAccess(), TinkerDamageTypes.BLEEDING), 5f);
            player.getCooldowns().addCooldown(item.getItem(), 20);
        }
    }
    @SubscribeEvent
    public static void KillingCreeper(LivingDeathEvent event){
        if (event.getEntity() instanceof Creeper creeper){
            if (creeper.isPowered()){
                DamageSource source = event.getSource();
                AreaEffectCloud cloud = new AreaEffectCloud(creeper.level(), creeper.getX(), creeper.getY(), creeper.getZ());
                cloud.setOwner(creeper);
                cloud.setRadius(2);
                cloud.setRadiusOnUse(-0.5F);
                cloud.setWaitTime(10);
                cloud.setRadiusPerTick(-cloud.getRadius() / (float)cloud.getDuration());
                cloud.addEffect(new MobEffectInstance(TinkersInnovationEffects.thunderTribulationEffect.get(),100));
                creeper.level().addFreshEntity(cloud);
                if (source.is(DamageTypes.LIGHTNING_BOLT) && new Random().nextFloat() < 0.05){
                    LiquidBlock block = TinkersInnovationFluids.lightning.getBlock();
                    if (block != null) {
                        creeper.level().setBlock(new BlockPos((int) creeper.getX(), (int) creeper.getY(), (int) creeper.getZ()), block.defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);
                    }
                }
            }
        }
    }
}
