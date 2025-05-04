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
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
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
        if (player.hasEffect(TinkerModifiers.bleeding.get()) && item.is(Items.GLASS_BOTTLE)){
            item.shrink(1);
            ItemStack newitem = new ItemStack(TinkersInnovationItems.blood_bottle.get());
            Inventory inventory = player.getInventory();
            if (!inventory.add(newitem)){
                ModifierUtil.dropItem(player, newitem);
            }
            player.hurt(new DamageSource(TConstruct.prefix("bleed")).bypassArmor().bypassMagic(), 5f);
        }
    }
}
