package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sprite.spriteManager.SpriteManagerMonster;
import sprite.TextureFactory;

public class Monster extends Entity {

    protected Monster(int hp, boolean passThrought, Position position, Behavior behavior) {
        super(hp, passThrought, position, behavior, new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster()));
    }

    /**
     * Give an image for the hero (used after a load)
     */
    public void setImage() {
        spriteManager = new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster());
    }
}
