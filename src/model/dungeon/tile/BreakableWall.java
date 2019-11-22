package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Effect{
    BreakableWall(int hp, Tile decore) {
        super(hp, decore);
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getBreakableWall());
    }

    @Override
    public void setImage() {
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getBreakableWall());
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
        crayon.dispose();
    }

    @Override
    public void action(Maze maze, Entity e) {

    }

    @Override
    public boolean canBeCrossed() {
        return false;
    }

    @Override
    public boolean isStairs() {
        return false;
    }

    @Override
    public int getGold() {
        return 0;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }
}
