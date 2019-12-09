package model.dungeon.entity.behavior.move;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Cmd;
import model.global.Position;

public class MoveProjectile implements Move {

    @Override
    public Cmd move(Maze maze, Entity entity, Cmd commande) {
        Cmd movement;
        movement = entity.getPosition().getCmd();

        entity.setPosition(new Position(
                entity.getPosition().getX(),
                entity.getPosition().getY(),
                movement
        ));

        return movement;
    }
}
