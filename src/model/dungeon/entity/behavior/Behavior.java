package model.dungeon.entity.behavior;

import model.dungeon.entity.Entity;
import model.global.GlobalDirection;

public interface Behavior {
    public abstract GlobalDirection behave(Entity entity);
}
