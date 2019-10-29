package model.dungeon.tile;

import model.dungeon.entity.Entity;

public abstract class Effect extends Tile {
    private Tile decore;

    Effect(Tile decore){
        this.decore = decore;
    }

    @Override
    public abstract void draw();

    @Override
    public abstract void action(Entity e);

    @Override
    public abstract boolean canBeCrossed();
}
