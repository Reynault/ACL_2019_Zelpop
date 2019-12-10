package model.dungeon.entity;

import model.dungeon.Maze;
import model.dungeon.entity.behavior.Behavior;
import model.global.Cmd;
import model.global.Position;
import sound.soundManager.SoundManager;
import sprite.spriteManager.SpriteManagerEntity;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Entity implements Serializable {

    protected Stats stats;
    protected SoundManager sounds;

    protected boolean passThrought;
    protected boolean untouchable;
    protected Position position;
    protected Behavior behavior;
    protected SpriteManagerEntity spriteManager;
    protected int score;   // base score for the entity
    protected int value;   // value of the score

    /**
     *
     * @param stats stats of the entity
     * @param passThrought if the entity can move throught walls
     * @param score
     * @param value value of the score when the entity dies
     * @param position position  of the entity
     * @param behavior behavior of the entity
     * @param spriteManager sprite manager
     */
    protected Entity(Stats stats, boolean passThrought, boolean untouchable, int score, int value, Position position, Behavior behavior, SpriteManagerEntity spriteManager, SoundManager soundManager) {
        this.stats = stats;
        this.passThrought = passThrought;
        this.position = position;
        this.behavior = behavior;
        this.spriteManager = spriteManager;
        this.score = score;
        this.value = value;
        this.untouchable = untouchable;
        this.sounds = soundManager;
    }

    public void regen(){
        stats.setCurrentHp(Math.min(getHp() + stats.getVitality(), getMaxHp()));
    }

    public int getValue() {
        return value;
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
    }

    public void setAttacking() {
        spriteManager.setAttacking();
        sounds.playActionSound();
    }

    /**
     *
     */
    public void takeDamage(double damage) {
        if(damage < 0 && -damage + getHp() > getMaxHp()){
            this.stats.setCurrentHp(getMaxHp());
        }else {
            this.stats.setCurrentHp(this.stats.getCurrentHp() - damage);
        }
    }

    /**
     *
     */
    public boolean isAlive() {
        return this.stats.getCurrentHp() >= 0;
    }

    /**
     *
     */
    public boolean isHero() {
        return false;
    }

    public boolean isProjectile() {
        return false;
    }

    /**
     *  Increase the total score
     * @param bonus value of the bonus
     */
    public void increaseScore(double bonus) {
        score += bonus;
    }

    public int getScore() {
        return score;
    }

    /**
     * Give an image for the entity (used after a load)
     */
    public abstract void setRessources();

    /**
     * Draw the entity at a given position
     *
     * @param img   the image in which the entity will be drawn
     * @param x     x position
     * @param y     y position
     * @param scale scale of the entity
     */
    public void draw(BufferedImage img, int x, int y, int scale) throws InterruptedException {
        spriteManager.draw(img, x, y, scale);
    }

    public double getHp() {
        return stats.getCurrentHp();
    }

    public double getMaxHp() {
        return stats.getMaxHp();
    }

    public double getVitality() { return stats.getVitality(); }

    public double getDmg() {
        return stats.getDamage();
    }

    public double getDef() {
        return stats.getDefence();
    }

    public double getMaxHpCostToUpgrade() { return stats.getMaxHpCostToUpgrade(); }

    public double getDamageCostToUpgrade() { return stats.getDamageCostToUpgrade(); }

    public double getDefenceCostToUpgrade() { return stats.getDefenceCostToUpgrade(); }

    public double getVitalityCostToUpgrade() { return stats.getVitalityCostToUpgrade(); }

    public void setDmg(int dmg) {
        stats.setDmg(dmg);
    }

    public void setDefence(int defence) {
        stats.setDefence(defence);
    }

    public Stats getStats() {
        return stats;
    }


    public boolean isUntouchable() {
        return untouchable;
    }

    /**
     * Multiplier for the scoring
     * @return value of the multiplier
     */
    public abstract int getMultiplier();
}
