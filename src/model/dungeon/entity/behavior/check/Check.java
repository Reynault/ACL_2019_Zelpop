package model.dungeon.entity.behavior.check;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

import java.io.Serializable;

/**
 * Check is responsible of telling if the entity has to move or to attack in a round
 */
public interface Check extends Serializable {

    boolean check(Maze maze, Entity entity);
}
