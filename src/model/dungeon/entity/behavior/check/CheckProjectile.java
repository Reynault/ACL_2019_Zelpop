package model.dungeon.entity.behavior.check;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.Projectile;
import model.global.Cmd;
import model.global.Position;

public class CheckProjectile implements Check {

    @Override
    public boolean check(Maze maze, Entity entity) {
        Position pos = entity.getPosition();
        int x = pos.getX();
        int y = pos.getY();

        Entity victim = maze.getEntity(x, y);
        Entity owner = null;

        if(entity.isProjectile()){
            owner = ((Projectile)entity).getOwner();
        }

        if (victim != null && victim != entity && victim != owner) {
            return true;
        } else {
            return false;
        }
    }
}
