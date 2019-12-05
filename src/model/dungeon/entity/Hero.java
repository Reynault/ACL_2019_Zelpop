package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sprite.spriteManager.SpriteManagerHero;
import sprite.TextureFactory;

public class Hero extends Entity {

    public Hero(Stats stats, boolean b, int score, int value, Position position, Behavior behavior) {
        super(stats, b, score, value, position, behavior,  new SpriteManagerHero(TextureFactory.getTextureFactory().getHero()));
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

    public void setVitality(double i) {
        stats.setVitality(i);
    }

    public void setHP(int i) {
        stats.setCurrentHp(i);
    }
}
