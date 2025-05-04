package com.gjhi.tinkersinnovation.library.modifiers;

import com.gjhi.tinkersinnovation.TinkersInnovation;
import com.xiaoyue.tingenuity_library.library.hook.curio.CurioArrowHook;
import com.xiaoyue.tingenuity_library.library.hook.curio.CurioBuilderHook;
import com.xiaoyue.tingenuity_library.library.hook.curio.CurioCombatHook;
import com.xiaoyue.tingenuity_library.library.logic.context.AttributeData;
import com.xiaoyue.tingenuity_library.library.logic.context.ImpactData;
import com.xiaoyue.tingenuity_library.register.LibraryHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class SculkCurioModifier extends Modifier implements ModifierRemovalHook, CurioBuilderHook, CurioArrowHook, CurioCombatHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.REMOVE, LibraryHooks.CURIO_BUILDER, LibraryHooks.CURIO_ARROW, LibraryHooks.CURIO_COMBAT);
    }
    private final ResourceLocation KEY = new ResourceLocation(TinkersInnovation.MOD_ID, "sculk_curio");
    @Override
    public void onCurioTick(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack stack) {
        entity.removeEffect(MobEffects.BLINDNESS);
        entity.removeEffect(MobEffects.DARKNESS);
        entity.removeEffect(MobEffects.DIG_SLOWDOWN);
        entity.removeEffect(MobEffects.CONFUSION);
        entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        entity.removeEffect(MobEffects.WEAKNESS);
    }
    @Override
    public Component onRemoved(IToolStackView tool, Modifier modifier) {
        tool.getPersistentData().remove(modifier.getId());
        return null;
    }

    @Override
    public void addCurioAttribute(IToolStackView curio, SlotContext context, LivingEntity entity, int level, AttributeData attr) {
        if (curio.getPersistentData().getBoolean(KEY)){
            attr.map().put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("2603ed26-e762-49ad-b1e5-6902545493cc"), Attributes.MAX_HEALTH.getDescriptionId(), 2 * level, AttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public void onCurioEquip(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack prevStack, ItemStack stack) {
        ModDataNBT persistentData = curio.getPersistentData();
        persistentData.putBoolean(KEY, true);
    }

    @Override
    public void onCurioUnequip(IToolStackView curio, SlotContext context, LivingEntity entity, int level, ItemStack newStack, ItemStack stack) {
        ModDataNBT persistentData = curio.getPersistentData();
        persistentData.putBoolean(KEY, false);
    }

    @Override
    public void onCurioArrowHit(IToolStackView curio, LivingEntity shooter, ImpactData context, int level) {
        Entity entity = context.getTarget();
        if (entity instanceof LivingEntity target) {
            int time = target.invulnerableTime;
            target.hurt(DamageSource.sonicBoom(context.arrow()), level);
            target.invulnerableTime = time;
        }
    }

    @Override
    public void onCurioToDamagePre(IToolStackView curio, LivingHurtEvent event, LivingEntity attacker, LivingEntity target, int level) {
        int time = target.invulnerableTime;
        target.hurt(DamageSource.sonicBoom(attacker), level);
        target.invulnerableTime = time;
    }

}
