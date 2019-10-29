package model.dungeon.entity;

public class Monster extends Entity {

    /**
     * Default constructor
     * @param x x-axis
     * @param y y-axis
     * @param hp health point
     */
    protected Monster(int x, int y, int hp) {
        super(x, y, hp);
    }

    @Override
    public void draw() {

    }
}
