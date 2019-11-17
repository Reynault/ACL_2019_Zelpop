package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

import java.awt.image.BufferedImage;

public class Stairs extends Effect{

    Stairs(Tile decore) {
        super(decore);
    }

    @Override
    public void setImage() {

    }

    @Override
    public void draw(BufferedImage img, int x, int y, int scale) {

    }

    @Override
    public void action(Maze maze, Entity e) {

    }

    @Override
    public boolean canBeCrossed() {
        return true;
    }

    @Override
    public boolean isStairs() {
        return true;
    }

    @Override
    public int getGold() {
        return 0;
    }
}
