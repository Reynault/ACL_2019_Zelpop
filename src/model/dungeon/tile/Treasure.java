package model.dungeon.tile;

import model.dungeon.entity.Entity;

public class Treasure extends Effect {
    Treasure(Tile decore) {
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
