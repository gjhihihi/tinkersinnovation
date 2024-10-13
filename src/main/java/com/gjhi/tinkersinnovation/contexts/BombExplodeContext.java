package com.gjhi.tinkersinnovation.contexts;

import net.minecraft.world.level.Explosion;

public class BombExplodeContext {
    private float bomb_radius;
    private int piece_count;
    private float piece_damage;
    private boolean is_fired;
    private Explosion.BlockInteraction type;

    public BombExplodeContext(float bomb_radius, int piece_count, float piece_damage, boolean is_fired, Explosion.BlockInteraction type) {
        this.bomb_radius = bomb_radius;
        this.piece_count = piece_count;
        this.piece_damage = piece_damage;
        this.is_fired = is_fired;
        this.type = type;
    }

    public float getRadius(){
        return bomb_radius;
    }
    public void setRadius(float radius){
        bomb_radius = radius;
    }

    public float getPieceDamage() {
        return piece_damage;
    }
    public void setPieceDamage(float damage) {
        piece_damage = damage;
    }

    public int getPieceCount() {
        return piece_count;
    }
    public void setPieceCount(int count) {
        piece_count = count;
    }

    public boolean isFired() {
        return is_fired;
    }
    public void setFired(boolean fire) {
        is_fired = fire;
    }

    public Explosion.BlockInteraction getType() {
        return type;
    }
    public void setType(Explosion.BlockInteraction type) {
        this.type = type;
    }
}
