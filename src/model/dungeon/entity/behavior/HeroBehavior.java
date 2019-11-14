package model.dungeon.entity.behavior;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.behavior.attack.Attack;
import model.dungeon.entity.behavior.check.Check;
import model.dungeon.entity.behavior.check.CheckSimple;
import model.dungeon.entity.behavior.move.Move;
import model.global.GlobalDirection;
import model.global.Position;

public class HeroBehavior implements Behavior {

    protected Attack attack;
    protected Check check;
    protected Move move;
    private Maze currentMaze ;
    private Entity currentEntity ;

    protected void Behavior(Maze maze, Entity entity ){
        this.currentEntity = entity;
        this.currentMaze = maze;
    }

    @Override
    public GlobalDirection behave(Entity entity, GlobalDirection direction) {
        Position currentPosition = entity.getPosition();

        // Only change the direction of the look
        if(currentPosition.getGlobalDirection() != direction){
            entity.setPosition(new Position(
                    currentPosition.getX(),
                    currentPosition.getY(),
                    direction
            ));
            direction = GlobalDirection.IDLE;
        }

        return direction;
    }

    @Override
    public GlobalDirection behave(Maze maze, Entity entity, Attack attack) {
        //check if a mouvement or an attack from heo entity
        if (check.check(maze , entity)){
            attack.attack(maze, entity);
        }else {
            move.move(maze , entity);
        }
        return null;
    }

}
