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

        Entity victim;

        if ((victim = maze.getEntity(x, y)) != null) {
            commande = Cmd.IDLE;
        }

        entity.setPosition(
                new Position(x, y, commande)
        );

        if (victim != null && victim != entity) {
            return true;
        } else {
            return false;
        }
    }
}
