package model.dungeon.tile;

import model.dungeon.entity.Entity;

import java.awt.image.BufferedImage;

public class Treasure extends Effect {
    Treasure(Tile decore) {
        super(decore);
    }

    @Override
    public void draw(BufferedImage img, int x, int y) {

    }

    @Override
    public void action(Entity e) {

    }

    @Override
    public boolean canBeCrossed() {
        return true;
    }
}
