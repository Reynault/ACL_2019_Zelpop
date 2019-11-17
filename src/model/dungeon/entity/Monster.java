package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sprite.spriteManager.SpriteManager;
import sprite.spriteManager.SpriteManagerMonster;
import sprite.TextureFactory;

public class Monster extends Entity {

    protected Monster(int hp, boolean passThrought, int damage, int score, int value, Position position, Behavior behavior) {
        super(hp, passThrought, damage, score, value, position, behavior, new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster()));
    }

    @Override
    public Boolean isHero() {
        return false;
    }

    /**
     * Give an image for the hero (used after a load)
     */
    public void setImage() {
        spriteManager = new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster());
    }
}
