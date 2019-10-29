package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.GlobalDirection;
import model.global.Position;

public abstract class Entity {

    private int hp;
    private boolean passThrought;
    private Position position;
    protected Behavior behavior;

    protected Entity(int hp, boolean passThrought, Position position, Behavior behavior) {
        this.hp = hp;
        this.passThrought = passThrought;
        this.position = position;
        this.behavior = behavior;
    }


    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public boolean canPassThrought(){
        return passThrought;
    }

    public void setPassThrought(boolean value){
        passThrought = value;
    }

    public GlobalDirection behave(GlobalDirection direction){
        return behavior.behave(this, direction);
    }

    /**
     * To draw the entity
     */
    public abstract void draw();
}
