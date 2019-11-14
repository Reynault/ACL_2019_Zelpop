package model.dungeon.entity.behavior.attack;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

public interface Attack {

    void attack(Maze maze , Entity entity);
}
