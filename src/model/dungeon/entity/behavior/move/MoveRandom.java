package model.dungeon.entity.behavior.move;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Cmd;
import model.global.Position;

public class MoveRandom implements Move {
    @Override
    public Cmd move(Maze maze, Entity entity, Cmd commande) {
        Cmd movement;

        // Generating a random that define a movement
        int rand = (int)(Math.round(Math.random()*4));

        switch (rand){
            case 1:
                movement = Cmd.LEFT;
                break;
            case 2:
                movement = Cmd.UP;
                break;
            case 3:
                movement = Cmd.RIGHT;
                break;
            case 4:
                movement = Cmd.DOWN;
                break;
            default:
                movement = Cmd.IDLE;
                break;
        }

        entity.setPosition(new Position(
                entity.getPosition().getX(),
                entity.getPosition().getY(),
                movement
        ));

        return movement;
    }
}
