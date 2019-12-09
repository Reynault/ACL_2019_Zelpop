package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;
import sound.soundManager.SoundManagerFactory;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerMonster;

public class Ghost extends Entity {

    protected Ghost(Stats stats, boolean b, boolean untouchable, int score, int value, Position position, Behavior behavior) {
        super(stats, b, untouchable, score, value, position, behavior,
                new SpriteManagerMonster(TextureFactory.getTextureFactory().getGhost()),
                SoundManagerFactory.getGhostSounds());
    }

    @Override
    public void setRessources() {
        //change to getGhost
        spriteManager = new SpriteManagerMonster(TextureFactory.getTextureFactory().getGhost());
    }
}
