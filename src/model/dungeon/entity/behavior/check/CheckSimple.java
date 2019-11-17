package model.dungeon.entity.behavior.check;

import model.Pair;
import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Position;

public class CheckSimple implements Check {

    @Override
    public boolean check(Maze maze, Entity entity) {
        Position pos = entity.getPosition();
        int x = pos.getX();
        int y = pos.getY();
        Pair val = maze.getPositionByDirection(x,y, pos.getCmd());

        Entity e = maze.getEntity(val.getX(), val.getY());
        if(e != null && e != entity && e.isHero()){
            return true;
        }else{
            return false;
        }
    }
}
