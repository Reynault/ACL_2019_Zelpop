package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sound.soundManager.SoundManager;
import sound.soundManager.SoundManagerFactory;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

public class Treasure extends Effect {

    private boolean open;
    private int gold;

    /**
     * Default constructor
     * @param decore decorated tile
     */
    Treasure(int hp, Tile decore, int gold) {
        super(hp, decore, SoundManagerFactory.getTreasureSounds());
        open = false;
        this.gold = gold;
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getFilledTreasure());
    }

    @Override
    public void setImage() {
        decore.setImage();
        if(open) {
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getEmptyTreasure());
        }else{
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getFilledTreasure());
        }
    }

    @Override
    public void action(Maze maze, Entity e) {
        if(!open) {
            sound.playActionSound();
            e.increaseScore(maze.getChestScore(this));
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getEmptyTreasure());
        }
        open = true;
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
        return gold;
    }
}
