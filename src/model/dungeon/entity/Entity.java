package model.dungeon.entity;

import model.dungeon.Maze;
import model.dungeon.entity.behavior.Behavior;
import model.global.Cmd;
import model.global.Position;
import sprite.spriteManager.SpriteManager;
import sprite.spriteManager.SpriteManagerEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Entity implements Serializable {

    protected int maxHp;
    protected int hp;
    protected boolean passThrought;
    protected Position position;
    protected Behavior behavior;
    protected SpriteManagerEntity spriteManager;
    protected int damage;
    protected int score;
    protected int value;

    protected Entity(int hp, boolean passThrought, int damage, int score, int value, Position position, Behavior behavior, SpriteManagerEntity spriteManager) {
        this.maxHp = hp;
        this.hp = hp;
        this.passThrought = passThrought;
        this.position = position;
        this.behavior = behavior;
        this.score = 0;
        this.spriteManager = spriteManager;
        this.damage = damage;
        this.score = score;
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        spriteManager.setSprite(position.getCmd());
    }

    public boolean canPassThrought() {
        return passThrought;
    }

    public void setPassThrought(boolean value) {
        passThrought = value;
    }

    public Cmd behave(Maze maze, Cmd direction) {
        return behavior.behave(maze, this, direction);
    }


    /**
     * Entity is attacking
     *
     * @param maze
     */
    public void attack(Maze maze) {
        behavior.behave(maze, this, Cmd.ATTACK);
        spriteManager.isAttacking();
    }

    /**
     *
     */
    public int getDmg() {
        return damage;
    }

    /**
     *
     */
    public void takeDamage(int damage) {
        if (damage >= 0) {
            hp = hp - damage;
        }
    }

    /**
     *
     */
    public boolean isAlive() {
        if (this.hp > 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     */
    public void increaseScore(int bonus) {
        score += bonus;
    }

    public int getScore() {
        return score;
    }

    /**
     *
     */
    public abstract Boolean isHero();

    /**
     * Give an image for the entity (used after a load)
     */
    public abstract void setImage();

    /**
     * Draw the entity at a given position
     *
     * @param img   the image in which the entity will be drawn
     * @param x     x position
     * @param y     y position
     * @param scale scale of the entity
     */
    public void draw(BufferedImage img, int x, int y, int scale) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                x,
                y,
                spriteManager.getCurrentSprite().getWidth() * scale,
                spriteManager.getCurrentSprite().getHeight() * scale,
                null);
        crayon.dispose();
    }
}
