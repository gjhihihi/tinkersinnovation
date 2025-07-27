package com.gjhi.tinkersinnovation.events;

import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.shared.TinkerEffects;
import slimeknights.tconstruct.tools.TinkerModifiers;

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
}
