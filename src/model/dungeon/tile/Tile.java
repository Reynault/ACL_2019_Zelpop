package model.dungeon.tile;

import model.dungeon.entity.Entity;
import model.global.GlobalSprites;
import sprite.spriteManager.SpriteManager;
import sprite.spriteManager.SpriteManagerTile;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends TileFactory {

    protected SpriteManager spriteManager;
    /**
     * Default constructor
     */
    protected Tile(){
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTiles());
    }

    public void draw(BufferedImage img, int x, int y, int scale){
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                x,
                y,
                spriteManager.getCurrentSprite().getWidth() * scale,
                spriteManager.getCurrentSprite().getHeight() * scale,
                null);
        crayon.dispose();
    }

    public void action(Entity entity){

    }

    public boolean canBeCrossed(){
        return true;
    }

    @Override
    public String toString() {
        return "Tile ";
    }
}
