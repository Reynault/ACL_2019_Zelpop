package model.dungeon.entity.behavior.attack;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

import java.io.Serializable;

/**
 * Attack is responsible of executing the attack strategy of the entity
 */
public abstract class Attack implements Serializable {

    public void attack(Maze maze , Entity entity){
        entity.setAttacking();
    }
}
