package com.gjhi.tinkersinnovation.events;

import com.gjhi.tinkersinnovation.library.modifiers.ShieldAmountModifier;
import com.gjhi.tinkersinnovation.register.TinkersInnovationHooks;
import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import com.gjhi.tinkersinnovation.register.TinkersInnovationUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import static com.gjhi.tinkersinnovation.TinkersInnovation.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class TinkerHooksEvents {
    @SubscribeEvent
    public static void onSource(LivingHurtEvent event){
        Entity entity = event.getSource().getEntity();
        if (entity instanceof LivingEntity attacker){
            ToolStack tool = TinkersInnovationUtils.getToolFromHand(attacker);
            if (tool != null) {
                for (ModifierEntry modifier : tool.getModifierList()) {
                    modifier.getHook(TinkersInnovationHooks.MODIFY_SOURCE).modifyDamageSource(tool, modifier, attacker, event.getEntity(), event.getSource());
                }
            }
        }
    }
    @SubscribeEvent
    public static void onBlocking(ShieldBlockEvent event){
        int amount = (int) event.getOriginalBlockedDamage();
        LivingEntity blocker = event.getEntity();
        ToolStack tool = ToolStack.from(blocker.getUseItem());
        if (tool.getDefinition() != ToolDefinition.EMPTY){
            for (ModifierEntry modifier : tool.getModifierList()){
                amount = modifier.getHook(TinkersInnovationHooks.ON_BLOCKING).onBlocking(tool, modifier, blocker, event, amount);
            }
            ModifierEntry shield = tool.getModifier(TinkersInnovationModifiers.shield_amount.get());
            if (shield.getLevel() > 0 && shield.getModifier() instanceof ShieldAmountModifier shield_amount){
                shield_amount.addShieldAmount(tool, shield, -amount);
            }
        }
    }
}
