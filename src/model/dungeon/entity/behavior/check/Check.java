package model.dungeon.entity.behavior.check;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

public interface Check {

    public abstract boolean check(Maze maze, Entity entity);
}
