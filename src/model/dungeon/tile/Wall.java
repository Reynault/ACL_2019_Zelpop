package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends Effect {

    /**
     * Default constructor
     * @param decore decorated tile
     */
    Wall(int hp, Tile decore) {
        super(hp, decore);
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getWalls());
    }

    @Override
    public void setImage() {
        decore.setImage();
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getWalls());
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
    public String toString() {
        return "Wall ";
    }
}
