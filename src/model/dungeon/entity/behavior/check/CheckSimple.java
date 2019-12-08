package model.dungeon.entity.behavior.check;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Cmd;
import model.global.Position;

public class CheckSimple implements Check {

    @Override
    public boolean check(Maze maze, Entity entity) {
        Position pos = entity.getPosition();
        int x = pos.getX();
        int y = pos.getY();
        Cmd commande = pos.getCmd();

        Entity victim;

        if ((victim = maze.getEntity(x+1, y)) != null && victim.isHero()) {
            commande = Cmd.RIGHT;
        } else if ((victim = maze.getEntity(x-1, y)) != null && victim.isHero()) {
            commande = Cmd.LEFT;
        } else if ((victim = maze.getEntity(x, y+1)) != null && victim.isHero()) {
            commande = Cmd.DOWN;
        } else if ((victim = maze.getEntity(x, y-1)) != null && victim.isHero()) {
            commande = Cmd.UP;
        }

        entity.setPosition(
                new Position(x, y, commande)
        );

        if (victim != null && victim != entity && victim.isHero()) {
            return true;
        } else {
            return false;
        }
    }
}
