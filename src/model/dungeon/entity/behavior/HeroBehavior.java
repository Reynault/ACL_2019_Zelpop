package model.dungeon.entity.behavior;

import model.dungeon.entity.Entity;
import model.global.GlobalDirection;
import model.global.Position;

public class HeroBehavior implements Behavior {

    @Override
    public GlobalDirection behave(Entity entity, GlobalDirection direction) {
        Position currentPosition = entity.getPosition();

        // Only change the direction of the look
        if(currentPosition.getGlobalDirection() != direction){
            entity.setPosition(new Position(
                    currentPosition.getX(),
                    currentPosition.getY(),
                    currentPosition.getGlobalDirection()
            ));
            direction = GlobalDirection.IDLE;
        }

        return direction;
    }
}
