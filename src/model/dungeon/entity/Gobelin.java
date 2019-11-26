package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerMonster;

public class Gobelin extends Entity{
    protected Gobelin(int hp, boolean passThrought, int damage, int score, int value, Position position, Behavior behavior) {
        super(hp, passThrought, damage, score, value, position, behavior, new SpriteManagerMonster(TextureFactory.getTextureFactory().getGobelin()));
    }

    @Override
    public Boolean isHero() {
        return false;
    }

    @Override
    public void setImage() {
        // change to getGobelin
        spriteManager = new SpriteManagerMonster(TextureFactory.getTextureFactory().getGobelin());
    }
}
