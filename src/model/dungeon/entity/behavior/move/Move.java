package model.dungeon.entity.behavior.move;

import engine.Cmd;
import model.dungeon.Maze;
import model.dungeon.entity.Entity;

public interface Move {

    public abstract Cmd move(Maze maze, Entity entity);
}
