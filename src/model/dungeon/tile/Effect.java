package model.dungeon.tile;

public abstract class Effect extends Tile {

    @Override
    public abstract void draw();

    @Override
    public abstract void action();
}
