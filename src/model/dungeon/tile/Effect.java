package model.dungeon.tile;

import model.dungeon.entity.Entity;

import java.awt.image.BufferedImage;

public abstract class Effect extends Tile {
    protected Tile decore;

    Effect(Tile decore){
        this.decore = decore;
    }

    @Override
    public abstract void draw(BufferedImage img, int x, int y, int scale);

    @Override
    public abstract void action(Entity e);

    @Override
    public abstract boolean canBeCrossed();
}
