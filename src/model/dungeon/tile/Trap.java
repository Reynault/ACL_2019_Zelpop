package model.dungeon.tile;

import model.dungeon.entity.Entity;
import model.global.GlobalSprites;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Trap extends Effect {
    Trap(Tile decore) {
        super(decore);
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTraps());
    }

    @Override
    public void draw(BufferedImage img, int x, int y, int scale) {
        decore.draw(img, x, y, scale);
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                x,
                y,
                spriteManager.getCurrentSprite().getWidth() * scale,
                spriteManager.getCurrentSprite().getHeight() * scale,
                null);
    }

    @Override
    public void action(Entity e) {

    }

    @Override
    public boolean canBeCrossed() {
        return true;
    }
}
