package model.dungeon.entity.behavior;

import model.dungeon.entity.Entity;
import model.global.GlobalDirection;

public interface Behavior {

    /**
     * The behavior of an entity is used to determine a direction
     * @param entity entity who want to move
     * @return GlobalDirection
     */
    public abstract GlobalDirection behave(Entity entity, GlobalDirection direction);
}
