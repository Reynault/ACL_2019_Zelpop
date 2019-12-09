package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sound.Sound;
import sound.soundManager.SoundManagerFactory;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerProjectile;

public class Projectile extends Entity {

    public Projectile(Stats stats, boolean b, int score, int value, Position position, Behavior behavior) {
        super(stats, b, score, value, position, behavior,
                new SpriteManagerProjectile(TextureFactory.getTextureFactory().getFireBall()),
                SoundManagerFactory.getProjectileSound());
        Sound.playSound(Sound.FIRE_BALL);
    }

    @Override
    public boolean isProjectile() {
        return true;
    }

    @Override
    public void setRessources() {
        spriteManager = new SpriteManagerProjectile(TextureFactory.getTextureFactory().getFireBall());
        spriteManager.setSprite(position.getCmd());
    }
}
