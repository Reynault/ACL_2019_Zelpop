package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sound.Sound;
import sound.SoundManager;
import sound.SoundManagerFactory;
import sprite.spriteManager.SpriteManagerHero;
import sprite.TextureFactory;

import java.applet.AudioClip;

public class Hero extends Entity {

    AudioClip lowlife;

    public Hero(Stats stats, boolean b, int score, int value, Position position, Behavior behavior) {
        super(stats, b, score, value, position, behavior,
                new SpriteManagerHero(TextureFactory.getTextureFactory().getHero()),
                SoundManagerFactory.getHeroSounds());
        lowlife = Sound.getLowLife();
    }

    @Override
    public Boolean isHero() {
        return true;
    }

    /**
     * Give an image for the hero (used after a load)
     */
    public void setImage() {
        spriteManager = new SpriteManagerHero(TextureFactory.getTextureFactory().getHero());
        spriteManager.setSprite(position.getCmd());
    }

    @Override
    public void takeDamage(double damage) {
        super.takeDamage(damage);
        if(this.stats.getCurrentHp() < 0.25*getMaxHp()){
            lowlife.play();
        }
    }

    public void setVitality(double i) {
        stats.setVitality(i);
    }

    public void setHP(int i) {
        stats.setCurrentHp(i);
    }
}
