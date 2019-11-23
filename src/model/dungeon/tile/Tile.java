package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.GlobalSprites;
import sprite.spriteManager.SpriteManager;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;

public class Tile extends Breakable implements Serializable {

    protected SpriteManager spriteManager;

    /**
     * Default constructor
     */
    protected Tile(int hp){
        super(hp);
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTiles());
    }

    /**
     * Give an image for each tile (used after a load)
     */
    public void setImage(){
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTiles());
    }

    /**
     * Draw the tile
     * @param img image
     * @param x x-axes
     * @param y y-axes
     * @param scale scale of the game
     */
    public void draw(BufferedImage img, int x, int y, int scale) throws InterruptedException{
        drawPartOfTile(img, x, y, scale, spriteManager);
    }

    protected static void drawPartOfTile(BufferedImage img, int x, int y, int scale, SpriteManager spriteManager) throws InterruptedException{
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        BufferedImage image;
        List<BufferedImage> images =  spriteManager.getCurrentSprite();

        for(int i = 0; i < images.size(); i++) {

            if(i > 0) {
                Thread.sleep(GlobalSprites.getAnimationDelay());
            }

            image = images.get(i);
            crayon.drawImage(image,
                    x,
                    y,
                    image.getWidth() * scale,
                    image.getHeight() * scale,
                    null);
            crayon.dispose();
        }
    }

    public void action(Maze maze, Entity entity){

    }

    /**
     * Boolean which informs if a tile can be crossed
     * @return true if the tile can be crossed
     */
    public boolean canBeCrossed(){
        return true;
    }

    public boolean isStairs(){
        return false;
    }

    @Override
    public String toString() {
        return "Tile ";
    }

    public int getGold() {
        return 0;
    }

    @Override
    public boolean isBreakable() {
        return false;
    }

    public Tile getAncestor(){
        return this;
    }
}
