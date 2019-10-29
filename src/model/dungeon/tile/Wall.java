package model.dungeon.tile;

import model.dungeon.entity.Entity;

public class Wall extends Effect {
    Wall(Tile decore) {
        super(decore);
    }

    @Override
    public void draw() {

    }

    @Override
    public void action(Entity e) {
        
    }

    @Override
    public boolean canBeCrossed() {
        return false;
    }
}
