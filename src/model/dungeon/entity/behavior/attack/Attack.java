package model.dungeon.entity.behavior.attack;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

import java.io.Serializable;

public interface Attack extends Serializable {

    void attack(Maze maze , Entity entity);
}
