package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.GlobalSprites;
import model.global.Position;
import sprite.SpriteManagerHero;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hero extends Entity {

    public Hero(int hp, boolean passThrought, Position position, Behavior behavior) {
        super(hp, passThrought, position, behavior, new SpriteManagerHero(TextureFactory.getTextureFactory().getHero()));
    }
}
