package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sprite.spriteManager.SpriteManagerMonster;
import sprite.TextureFactory;

public class Monster extends Entity {

    protected Monster(Stats stats, boolean b, int score, int value, Position position, Behavior behavior) {
        super(stats, b, score, value, position, behavior,
                new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster()));
    }

    @Override
    public Boolean isHero() {
        return false;
    }

    /**
     * Give an image for the hero (used after a load)
     */
    public void setRessources() {
        spriteManager = new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster());
    }
}
