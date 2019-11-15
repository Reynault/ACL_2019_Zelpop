package model.dungeon.tile;

import model.dungeon.entity.Entity;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Effect extends Tile implements Serializable {
    protected Tile decore;

    Effect(Tile decore){
        this.decore = decore;
    }

    public abstract void setImage();

    @Override
    public abstract void draw(BufferedImage img, int x, int y, int scale);

    @Override
    public abstract void action(Entity e);

    @Override
    public abstract boolean canBeCrossed();
}
