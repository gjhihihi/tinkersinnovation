package com.gjhi.tinkersinnovation.library.entitys.behavior.efln_arrow;

import com.gjhi.tinkersinnovation.library.entitys.entitys.efln_arrow.EEFLNArrow;
import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BEFLNArrow extends AbstractProjectileDispenseBehavior {
    @Override
    protected Projectile getProjectile(Level level, Position position, ItemStack itemStack) {
        EEFLNArrow arrow = new EEFLNArrow(position.x(), position.y(), position.z(), level);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}