package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.GlobalDirection;
import model.global.GlobalSprites;
import model.global.Position;
import sprite.spriteManager.SpriteManager;

import java.awt.*;
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
     * Draw the entity
     * @param img the image in which the entity will be drawn
     */
    public void draw(BufferedImage img){
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                position.getX() * GlobalSprites.getScaling() * GlobalSprites.get8Sprite(),
                position.getY() * GlobalSprites.getScaling() * GlobalSprites.get8Sprite(),
                spriteManager.getCurrentSprite().getWidth() * GlobalSprites.getScaling(),
                spriteManager.getCurrentSprite().getHeight() * GlobalSprites.getScaling(),
                null);
    }

    /**
     * Draw the entity at a given position
     * @param img the image in which the entity will be drawn
     * @param x x position
     * @param y y position
     */
    public void draw(BufferedImage img, int x, int y){
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                x,
                y,
                spriteManager.getCurrentSprite().getWidth() * GlobalSprites.getScaling(),
                spriteManager.getCurrentSprite().getHeight() * GlobalSprites.getScaling(),
                null);
    }
}
