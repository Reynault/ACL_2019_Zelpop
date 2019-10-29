package model.dungeon.tile;

import model.dungeon.entity.Entity;

public class Trap extends Effect {
    Trap(Tile decore) {
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
        return true;
    }
}
