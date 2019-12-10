package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sound.soundManager.SoundManagerFactory;
import sprite.spriteManager.SpriteManagerMonster;
import sprite.TextureFactory;

public class Monster extends Entity {

    protected Monster(Stats stats, boolean b, boolean untouchable, int score, int value, Position position, Behavior behavior) {
        super(stats, b, untouchable, score, value, position, behavior,
                new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster()),
                SoundManagerFactory.getMonsterSounds());
    }

    @Override
    public int getMultiplier() {
        return 1;
    }

    @Override
    public void setRessources() {
        spriteManager = new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster());
    }
}
