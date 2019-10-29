package model.dungeon.entity;

public abstract class Entity {

    private int x;
    private int y;
    private int hp;

    /**
     * Default constructor
     * @param x x-axis
     * @param y y-axis
     * @param hp health point
     */
    protected Entity (int x, int y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    /**
     * Getter for the entity's x-axis
     * @return abs
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the entity's y-axis
     * @return abs
     */
    public int getY() {
        return y;
    }

    /**
     * To draw the entity
     */
    public abstract void draw();
}
