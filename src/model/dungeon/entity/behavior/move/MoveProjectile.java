package model.dungeon.entity.behavior.move;

import model.Pair;
import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Cmd;
import model.global.Position;

public class MoveProjectile implements Move {

    @Override
    public Cmd move(Maze maze, Entity entity, Cmd commande) {
        Position position = entity.getPosition();
        int x = position.getX();
        int y = position.getY();
        Cmd movement = position.getCmd();

        Pair newPos = maze.getPositionByDirection(x, y, movement);

        if(!maze.canMove(entity, newPos.getX(), newPos.getY())){
            maze.removeProjectile(entity);
        }

        return movement;
    }
}
