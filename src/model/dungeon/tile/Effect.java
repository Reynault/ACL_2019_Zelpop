package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sound.SoundManager;

import java.awt.image.BufferedImage;

public abstract class Effect extends Tile {
    protected Tile decore;
    protected SoundManager soundManager;

    Effect(int hp, Tile decore, SoundManager soundManager){
        super(hp);
        this.soundManager = soundManager;
        this.decore = decore;
    }

    public abstract void setImage();

    @Override
    public void draw(BufferedImage img, int x, int y, int scale) throws InterruptedException{
        decore.draw(img, x, y, scale);
        drawPartOfTile(img, x, y, scale, spriteManager);
    }

    @Override
    public abstract void action(Maze maze, Entity e);

    @Override
    public abstract boolean canBeCrossed();

    @Override
    public abstract boolean isStairs();

    @Override
    public abstract int getGold();

    @Override
    public void takeDamage(double damage) {
        // Taking damage
        super.takeDamage(damage);

    }

    @Override
    public Tile getAncestor(){
        return decore;
    }
}
