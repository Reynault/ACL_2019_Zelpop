package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.GlobalDirection;
import model.global.Position;

public abstract class Entity {

    private int hp;
    private boolean passThrought;
    private Position position;
    protected Behavior behavior;

    /**
     * Default constructor
     * @param hp health point
     */
    protected Entity (int hp) {
        this.hp = hp;
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
        return this.behave(direction);
    }

    /**
     * To draw the entity
     */
    public abstract void draw();
}
