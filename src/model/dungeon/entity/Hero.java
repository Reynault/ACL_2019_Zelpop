package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sound.soundManager.SoundManagerFactory;
import sprite.spriteManager.SpriteManagerHero;
import sprite.TextureFactory;

public class Hero extends Entity {


    public Hero(Stats stats, boolean b, boolean untouchable, int score, int value, Position position, Behavior behavior) {
        super(stats, b, untouchable, score, value, position, behavior,
                new SpriteManagerHero(TextureFactory.getTextureFactory().getHero()),
                SoundManagerFactory.getHeroSounds());
    }

    @Override
    public boolean isHero() {
        return true;
    }

    @Override
    public int getMultiplier() {
        return 0;
    }

    /**
     * Give an image for the hero (used after a load)
     */
    public void setRessources() {
        spriteManager = new SpriteManagerHero(TextureFactory.getTextureFactory().getHero());
        spriteManager.setSprite(position.getCmd());
    }

    @Override
    public void takeDamage(double damage) {
        super.takeDamage(damage);
    }

    public void setVitality(double i) {
        stats.setVitality(i);
    }

    public void setHP(int i) {
        stats.setCurrentHp(i);
    }
}
