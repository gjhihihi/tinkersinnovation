package com.gjhi.tinkersinnovation.events;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;
import static slimeknights.tconstruct.library.modifiers.Modifier.getHeldTool;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class TinkerModifiersEvents {

    @SubscribeEvent
    public void onHealing(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();
        float health = event.getAmount();
        if (health > 0 && entity.isAlive()) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ToolStack tool = getHeldTool(entity, slot);
                if (tool != null)
                    if (tool.getModifierLevel(TinkersInnovationModifiers.health_fixing.get()) > 0) {
                        while (tool.getDamage() > 0) {
                            ToolDamageUtil.repair(tool, (int)Math.pow(2, tool.getModifierLevel(TinkersInnovationModifiers.health_fixing.get()) - 1));
                            --health;
                            if (health <= 0){
                                event.setAmount(0);
                                return;
                            }
                        }
                    }
            }
            event.setAmount(health);
        }
    }
}
