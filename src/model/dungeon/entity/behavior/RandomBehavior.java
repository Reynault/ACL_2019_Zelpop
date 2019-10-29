package model.dungeon.entity.behavior;

import model.dungeon.entity.Entity;
import model.global.GlobalDirection;

public class RandomBehavior implements Behavior {
    @Override
    public GlobalDirection behave(Entity entity, GlobalDirection direction) {

        GlobalDirection movement;

        // Generating a random that define a movement
        int rand = (int)(Math.round(Math.random()*4));

        switch (rand){
            case 1:
                movement = GlobalDirection.LEFT;
                break;
            case 2:
                movement = GlobalDirection.UP;
                break;
            case 3:
                movement = GlobalDirection.RIGHT;
                break;
            case 4:
                movement = GlobalDirection.DOWN;
                break;
            default:
                movement = GlobalDirection.IDLE;
                break;
        }

        return movement;
    }
}
