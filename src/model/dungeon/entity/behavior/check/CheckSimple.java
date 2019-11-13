package model.dungeon.entity.behavior.check;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;

public class CheckSimple implements Check {

    @Override
    public boolean check(Maze maze, Entity entity) {
        return false;
    }
}
