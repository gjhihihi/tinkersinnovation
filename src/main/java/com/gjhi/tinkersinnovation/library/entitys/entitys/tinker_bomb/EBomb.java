package com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb;

import com.gjhi.tinkersinnovation.register.TinkersInnovationEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Deprecated
public class EBomb extends AbstractArrow{
    public EBomb(Level level, LivingEntity livingEntity) {
        super(TinkersInnovationEntityTypes.BOMB_ENTITY.get(), livingEntity, level);
        this.setBaseDamage(2.0);
    }
    public EBomb(EntityType<EBomb> type, Level level) {
        super(type, level);
        this.setBaseDamage(2.0);
    }

    public EBomb(double x, double y, double z, Level level) {
        super(TinkersInnovationEntityTypes.BOMB_ENTITY.get(), x, y, z, level);
        this.setBaseDamage(2.0);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}
