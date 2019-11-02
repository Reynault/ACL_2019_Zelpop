package model.dungeon.tile;

import model.dungeon.entity.Entity;

import java.awt.image.BufferedImage;

public class Wall extends Effect {
    Wall(Tile decore) {
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
        return false;
    }
}
