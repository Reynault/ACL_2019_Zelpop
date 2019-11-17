package model.dungeon.entity.behavior.check;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

import java.io.Serializable;

public interface Check extends Serializable {

    boolean check(Maze maze, Entity entity);
}
