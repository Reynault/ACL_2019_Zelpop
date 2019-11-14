package model.dungeon.entity.behavior;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.behavior.attack.Attack;
import model.dungeon.entity.behavior.check.Check;
import model.global.GlobalDirection;

public interface Behavior {


    /**
     * The behavior of an entity is used to determine a direction
     * @param entity entity who want to move
     * @return GlobalDirection
     */
    public abstract GlobalDirection behave(Entity entity, GlobalDirection direction);



    /**
     *
     * @param maze
     * @param entity
     * @param attack
     * @return
     */
    public abstract GlobalDirection behave(Maze maze, Entity entity, Attack attack);
}
