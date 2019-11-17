package model.dungeon.entity.behavior;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.behavior.attack.Attack;
import model.dungeon.entity.behavior.check.Check;
import model.dungeon.entity.behavior.move.Move;
import model.global.Cmd;
import model.global.Position;

import java.io.Serializable;

public class Behavior implements Serializable {

    Attack attack;
    Check check;
    Move move;
    Maze currentMaze;
    Entity currentEntity;

    public Behavior(Attack attack, Check check, Move move) {
        this.attack = attack;
        this.check = check;
        this.move = move;
        this.currentMaze = currentMaze;
        this.currentEntity = currentEntity;
    }

    /**
     * The behavior of an entity is used to determine a direction
     * <p>
     * It is a pattern method that implement a behavior depending on the movement
     * given by parameter commande
     *
     * @param entity entity who want to move
     * @return Cmd
     */
    public Cmd behave(Maze maze, Entity entity, Cmd commande) {
        Cmd res = Cmd.IDLE;
        switch (commande) {
            case ATTACK:

                attack.attack(maze, entity);
                break;

            case DOWN:
            case RIGHT:
            case LEFT:
            case UP:

                res = move.move(maze, entity, commande);
                break;

            default:

                // Pattern Method
                if (check.check(maze, entity)) {
                    attack.attack(maze, entity);
                } else {
                    res = move.move(maze, entity, commande);
                }

                break;
        }

        return res;
    }
}
