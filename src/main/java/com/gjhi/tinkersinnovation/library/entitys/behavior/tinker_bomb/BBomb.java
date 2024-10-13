package com.gjhi.tinkersinnovation.library.entitys.behavior.tinker_bomb;

import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BBomb extends AbstractProjectileDispenseBehavior {
    @Override
    protected Projectile getProjectile(Level level, Position position, ItemStack itemStack) {
        EBomb bomb = new EBomb(position.x(), position.y(), position.z(), level);
        bomb.pickup = AbstractArrow.Pickup.DISALLOWED;
        return bomb;
    }
}
