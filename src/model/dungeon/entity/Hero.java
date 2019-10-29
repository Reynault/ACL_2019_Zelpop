package model.dungeon.entity;

import model.global.GlobalDirection;

public class Hero extends Entity {

    /**
     * Default constructor
     * @param hp health point
     */
    public Hero(int hp) {
        super(hp);
    }

    @Override
    public GlobalDirection behave() {
        return this.behavior.behave(
                this, GlobalDirection.IDLE
        );
    }

    @Override
    public void draw() {

    }
}
