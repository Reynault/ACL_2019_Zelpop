package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerMonster;

public class Ghost extends Entity {

    protected Ghost(int hp, boolean passThrought, int damage, int score, int value, Position position, Behavior behavior) {
        super(hp, passThrought, damage, score, value, position, behavior, new SpriteManagerMonster(TextureFactory.getTextureFactory().getGhost()));
    }

    @Override
    public Boolean isHero() {
        return false;
    }

    @Override
    public void setImage() {
        //change to getGhost
        spriteManager = new SpriteManagerMonster(TextureFactory.getTextureFactory().getGhost());
    }
}
