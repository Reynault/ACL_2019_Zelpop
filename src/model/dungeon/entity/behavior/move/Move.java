package model.dungeon.entity.behavior.move;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Cmd;

import java.io.Serializable;

/**
 * Move is responsible of telling which tile is the next destination
 */
public interface Move extends Serializable {

    Cmd move(Maze maze, Entity entity, Cmd commande);
}
