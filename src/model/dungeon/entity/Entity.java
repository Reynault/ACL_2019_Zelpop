package model.dungeon.entity;

import jdk.nashorn.internal.ir.SplitReturn;
import model.dungeon.entity.behavior.Behavior;
import model.global.GlobalDirection;
import model.global.Position;
import sprite.SpriteManager;

import java.awt.image.BufferedImage;

public abstract class Entity {

    protected int hp;
    protected boolean passThrought;
    protected Position position;
    protected Behavior behavior;
    protected SpriteManager spriteManager;

    protected Entity(int hp, boolean passThrought, Position position, Behavior behavior, SpriteManager spriteManager) {
        this.hp = hp;
        this.passThrought = passThrought;
        this.position = position;
        this.behavior = behavior;
        this.spriteManager = spriteManager;
    }


    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
        spriteManager.setSprite(position.getGlobalDirection());
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
    public abstract void draw(BufferedImage img);
}
