package model.dungeon.entity;

import model.global.GlobalDirection;

public class Hero extends Entity{

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
