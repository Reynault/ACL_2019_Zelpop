package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Stairs extends Effect{

    Stairs(int hp, Tile decore) {
        super(hp, decore);
        spriteManager = new SpriteManagerTile(
                TextureFactory.getTextureFactory().getStairs()
        );
    }

    @Override
    public void setImage() {
        decore.setImage();
        spriteManager = new SpriteManagerTile(
                TextureFactory.getTextureFactory().getStairs()
        );
    }

    @Override
    public void draw(BufferedImage img, int x, int y, int scale) {
        decore.setImage();
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
        return true;
    }

    @Override
    public boolean isStairs() {
        return true;
    }

    @Override
    public int getGold() {
        return 0;
    }
}
