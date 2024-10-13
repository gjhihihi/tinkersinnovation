package com.gjhi.tinkersinnovation.library.entitys.entitys.efln_arrow;

import com.gjhi.tinkersinnovation.library.entitys.entitys.tinker_bomb.EBomb;
import com.gjhi.tinkersinnovation.register.TinkersInnovationEntityTypes;
import com.gjhi.tinkersinnovation.register.TinkersInnovationItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class EEFLNArrow extends AbstractArrow {
    public EEFLNArrow(Level level, LivingEntity livingEntity) {
        super(TinkersInnovationEntityTypes.EFLN_ARROW_ENTITY.get(), livingEntity, level);
        this.setBaseDamage(1.0);
    }
    public EEFLNArrow(EntityType<EEFLNArrow> type, Level level) {
        super(type, level);
        this.setBaseDamage(1.0);
    }

    public EEFLNArrow(double x, double y, double z, Level level) {
        super(TinkersInnovationEntityTypes.EFLN_ARROW_ENTITY.get(), x, y, z, level);
        this.setBaseDamage(1.0);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(TinkersInnovationItems.efln_arrow.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 2, Explosion.BlockInteraction.BREAK);
        super.onHitEntity(result);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 2, Explosion.BlockInteraction.BREAK);
        super.onHitBlock(result);
    }
}
