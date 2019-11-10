package model.dungeon.tile;

import model.dungeon.entity.Entity;
import model.global.GlobalSprites;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends Effect {
    Wall(Tile decore) {
        super(decore);
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getWalls());
    }

    @Override
    public void draw(BufferedImage img, int x, int y) {
        decore.draw(img, x, y);
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                x * GlobalSprites.getScaling() * GlobalSprites.get8Sprite(),
                y * GlobalSprites.getScaling() * GlobalSprites.get8Sprite(),
                spriteManager.getCurrentSprite().getWidth() * GlobalSprites.getScaling(),
                spriteManager.getCurrentSprite().getHeight() * GlobalSprites.getScaling(),
                null);
    }

    @Override
    public void action(Entity e) {
        
    }

    @Override
    public boolean canBeCrossed() {
        return false;
    }

    @Override
    public String toString() {
        return "Wall ";
    }
}
