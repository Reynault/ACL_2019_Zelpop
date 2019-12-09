package model.dungeon.entity.behavior.check;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Cmd;
import model.global.Position;

public class CheckProjectile implements Check {

    @Override
    public boolean check(Maze maze, Entity entity) {
        Position pos = entity.getPosition();
        int x = pos.getX();
        int y = pos.getY();
        Cmd commande = pos.getCmd();

        Entity victim = maze.getEntity(x, y);


        if (victim != null && victim != entity) {
            return true;
        } else {
            return false;
        }
    }
}
