package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.GlobalSprites;
import model.global.Position;
import sprite.SpriteManagerMonster;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Monster extends Entity {

    protected Monster(int hp, boolean passThrought, Position position, Behavior behavior) {
        super(hp, passThrought, position, behavior, new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster()));
    }
}
