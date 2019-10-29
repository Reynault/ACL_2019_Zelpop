package model.dungeon.entity;

import model.global.GlobalDirection;

public class Monster extends Entity {

    /**
     * Default constructor
     * @param hp health point
     */
    protected Monster(int hp) {
        super(hp);
    }

    @Override
    public GlobalDirection behave() {
        return null;
    }

    @Override
    public void draw() {

    }
}
