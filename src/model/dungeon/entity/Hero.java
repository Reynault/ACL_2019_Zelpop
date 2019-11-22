package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sprite.spriteManager.SpriteManagerHero;
import sprite.TextureFactory;

public class Hero extends Entity {


    protected Hero(int hp, boolean passThrought, int damage, int score, int value, Position position, Behavior behavior) {
        super(hp, passThrought, damage, score, value, position, behavior, new SpriteManagerHero(TextureFactory.getTextureFactory().getHero()));
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
}
