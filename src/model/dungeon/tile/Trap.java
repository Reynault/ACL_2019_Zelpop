package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sound.soundManager.SoundManagerFactory;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

public class Trap extends Effect {

    private int trapDamage;
    private boolean triggered;

    /**
     * Default constructor
     * @param decore decorated tile
     */
    Trap(int hp, Tile decore, int trapDamage) {
        super(hp, decore, SoundManagerFactory.getTrapSounds());
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTraps());
        this.trapDamage = trapDamage;
        this.triggered = false;
    }

    @Override
    public void setImage() {
        decore.setImage();
        if(triggered){
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTiles());
        }else {
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTraps());
        }
    }

    @Override
    public void action(Maze maze, Entity e) {
        if(!triggered) {
            sound.playActionSound();
            e.takeDamage(trapDamage);
            triggered = true;
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTiles());
            if (!e.isAlive()) {
                maze.removeEntity(e);
            }
        }
    }

    @Override
    public boolean canBeCrossed() {
        return true;
    }

    @Override
    public boolean isStairs() {
        return false;
    }

    @Override
    public int getGold() {
        return 0;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }
}
