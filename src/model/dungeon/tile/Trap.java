package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Trap extends Effect {

    private int trapDamage;

    /**
     * Default constructor
     * @param decore decorated tile
     */
    Trap(Tile decore) {
        super(decore);
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTraps());
        trapDamage = 2;
    }

    @Override
    public void setImage() {
        decore.setImage();
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
        crayon.dispose();
    }

    @Override
    public void action(Maze maze, Entity e) {
        e.takeDamage(trapDamage);
        if(!e.isAlive()){
            maze.removeEntity(e);
        }
    }

    @Override
    public boolean canBeCrossed() {
        return true;
    }

    @Override
    public boolean isStairs() {
        return false;
    }

    @Override
    public int getGold() {
        return 0;
    }
}
