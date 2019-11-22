package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Effect extends Tile implements Serializable {
    protected Tile decore;

    Effect(int hp, Tile decore){
        super(hp);
        this.decore = decore;
    }

    public abstract void setImage();

    @Override
    public abstract void draw(BufferedImage img, int x, int y, int scale);

    @Override
    public abstract void action(Maze maze, Entity e);

    @Override
    public abstract boolean canBeCrossed();

    @Override
    public abstract boolean isStairs();

    @Override
    public abstract int getGold();

    @Override
    public void takeDamage(int damage) {
        // Taking damage
        super.takeDamage(damage);
    }

    @Override
    public Tile getAncestor(){
        return decore;
    }
}
