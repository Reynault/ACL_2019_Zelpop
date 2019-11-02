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

    @Override
    public void draw(BufferedImage img) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                position.getX() * GlobalSprites.getScaling(),
                position.getY() * GlobalSprites.getScaling(),
                spriteManager.getCurrentSprite().getWidth() * GlobalSprites.getScaling(),
                spriteManager.getCurrentSprite().getHeight() * GlobalSprites.getScaling(),
                null);

    }
}
