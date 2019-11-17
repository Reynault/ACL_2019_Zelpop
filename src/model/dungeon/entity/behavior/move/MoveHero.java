package model.dungeon.entity.behavior.move;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Cmd;
import model.global.Position;

public class MoveHero implements Move {
    @Override
    public Cmd move(Maze maze, Entity entity, Cmd commande) {
        Position currentPosition = entity.getPosition();

        // Only change the direction of the look
        if (currentPosition.getCmd() != commande) {
            entity.setPosition(new Position(
                    currentPosition.getX(),
                    currentPosition.getY(),
                    commande
            ));
            commande = Cmd.IDLE;
        }

        return commande;
    }
}
