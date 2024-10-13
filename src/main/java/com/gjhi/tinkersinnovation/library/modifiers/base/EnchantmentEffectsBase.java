package com.gjhi.tinkersinnovation.library.modifiers.base;

import com.gjhi.tinkersinnovation.register.TinkersInnovationCompat;
import com.gjhi.tinkersinnovation.register.TinkersInnovationEffects;
import com.xiaoyue.tinkers_ingenuity.register.TIEffects;
import dev.xkmc.l2complements.init.registrate.LCEffects;
import dev.xkmc.l2hostility.init.registrate.LHEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentEffectsBase {
    private static final List<MobEffect> GOOD_EFFECTS = new ArrayList<>(List.of(
            MobEffects.LUCK, MobEffects.DIG_SPEED, MobEffects.FIRE_RESISTANCE, MobEffects.MOVEMENT_SPEED, MobEffects.DAMAGE_RESISTANCE, MobEffects.JUMP,
            TinkerModifiers.calcifiedEffect.get(),
            TinkersInnovationEffects.notHungryEffect.get()
    ));
    private static final List<MobEffect> BAD_EFFECTS = new ArrayList<>(List.of(
            MobEffects.DIG_SLOWDOWN, MobEffects.WITHER, MobEffects.WEAKNESS, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.BLINDNESS, MobEffects.POISON, MobEffects.CONFUSION, MobEffects.HUNGER,
            TinkerModifiers.bleeding.get(), TinkerModifiers.enderferenceEffect.get(),
            TinkersInnovationEffects.soulDevouringEffect.get()
    ));
    public static List<MobEffect> getGoodEffectsByCopy(){
        ArrayList<MobEffect> list = new ArrayList<>(GOOD_EFFECTS);
        if (TinkersInnovationCompat.TinkersIngenuity.isLoaded()){
            list.addAll(List.of(
                    TIEffects.LAST_STAND.get(),
                    TIEffects.ACCELERATION.get(),
                    TIEffects.MALIGNANT.get(),
                    TIEffects.REACTIVE.get()
            ));
        }
        if (TinkersInnovationCompat.L2Complements.isLoaded()){
            list.addAll(List.of(
                    LCEffects.EMERALD.get()
            ));
        }
        if (TinkersInnovationCompat.L2Hostility.isLoaded()){
            list.addAll(List.of(
                    LHEffects.MOONWALK.get()
            ));
        }
        return list;
    }
    public static List<MobEffect> getBadEffectsByCopy(){
        ArrayList<MobEffect> list = new ArrayList<>(BAD_EFFECTS);
        if (TinkersInnovationCompat.TinkersIngenuity.isLoaded()){
            list.addAll(List.of(
                    TIEffects.MORTAL_WOUND.get(),
                    TIEffects.FRAGILE.get(),
                    TIEffects.FIERCE_ICE.get()
            ));
        }
        if (TinkersInnovationCompat.L2Complements.isLoaded()){
            list.addAll(List.of(
                    LCEffects.ICE.get(),
                    LCEffects.FLAME.get(),
                    LCEffects.BLEED.get(),
                    LCEffects.CURSE.get(),
                    LCEffects.ARMOR_REDUCE.get()
            ));
        }
        if (TinkersInnovationCompat.L2Hostility.isLoaded()){
            list.addAll(List.of(
                    LHEffects.GRAVITY.get(),
                    LHEffects.ANTIBUILD.get()
            ));
        }
        return list;
    }
}
