package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.GlobalDirection;
import model.global.Position;

public abstract class Entity {

    protected int x;
    protected int y;
    protected int hp;
    protected boolean passThrought;
    protected GlobalDirection direction;

    protected Behavior behavior;

    public Position getPosition(){
        return new Position(
                x,
                y,
                direction
        );
    }

    public void setPosition(GlobalDirection direction){
        this.direction = direction;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean canPassThrought(){
        return passThrought;
    }

    public void setPassThrought(boolean value){
        passThrought = value;
    }

    public abstract GlobalDirection behave();

    /**
     * To draw the entity
     */
    public abstract void draw();
}
