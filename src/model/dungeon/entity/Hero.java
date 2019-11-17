package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sprite.spriteManager.SpriteManagerHero;
import sprite.TextureFactory;

public class Hero extends Entity {

    public Hero(int hp, boolean passThrought, Position position, Behavior behavior) {
        super(hp, passThrought, position, behavior, new SpriteManagerHero(TextureFactory.getTextureFactory().getHero()));
    }

    /**
     * Give an image for the hero (used after a load)
     */
    public void setImage() {
        spriteManager = new SpriteManagerHero(TextureFactory.getTextureFactory().getHero());
    }
}
