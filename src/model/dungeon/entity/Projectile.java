package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sound.soundManager.SoundManagerFactory;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerProjectile;

public class Projectile extends Entity {

    private Entity owner;

    public Projectile(Stats stats, boolean b, boolean untouchable, int score, int value, Position position, Behavior behavior, Entity owner) {
        super(stats, b, untouchable, score, value, position, behavior,
                new SpriteManagerProjectile(TextureFactory.getTextureFactory().getFireBall()),
                SoundManagerFactory.getProjectileSound());
        this.owner = owner;
    }

    @Override
    public boolean isProjectile() {
        return true;
    }

    public Entity getOwner(){
        return owner;
    }

    @Override
    public void setRessources() {
        spriteManager = new SpriteManagerProjectile(TextureFactory.getTextureFactory().getFireBall());
        spriteManager.setSprite(position.getCmd());
    }
}
