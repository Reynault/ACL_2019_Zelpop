package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.Position;

public class Hero extends Entity {

    public Hero(int hp, boolean passThrought, Position position, Behavior behavior) {
        super(hp, passThrought, position, behavior);
    }

    @Override
    public void draw() {

    }
}
