package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sound.soundManager.SoundManagerFactory;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerMonster;

public class Gobelin extends Entity{

    protected Gobelin(Stats stats, boolean b, boolean untouchable, int score, int value, Position position, Behavior behavior) {
        super(stats, b, untouchable, score, value, position, behavior,
                new SpriteManagerMonster(TextureFactory.getTextureFactory().getGobelin()),
                        SoundManagerFactory.getGoblinSounds());
    }

    @Override
    public int getMultiplier() {
        return 3;
    }

    @Override
    public void setRessources() {
        spriteManager = new SpriteManagerMonster(TextureFactory.getTextureFactory().getGobelin());
    }
}
