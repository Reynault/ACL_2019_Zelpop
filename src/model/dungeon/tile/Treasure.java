package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Treasure extends Effect {

    private boolean open;
    private int gold;

    /**
     * Default constructor
     * @param decore decorated tile
     */
    Treasure(int hp, Tile decore, int gold) {
        super(hp, decore);
        open = false;
        this.gold = gold;
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getFilledTreasure());
    }

    @Override
    public void setImage() {
        decore.setImage();
        if(open) {
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getEmptyTreasue());
        }else{
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getFilledTreasure());
        }
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
        if(!open) {
            e.increaseScore(maze.getChestScore(this));
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getEmptyTreasue());
        }
        open = true;
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
        return gold;
    }
}
