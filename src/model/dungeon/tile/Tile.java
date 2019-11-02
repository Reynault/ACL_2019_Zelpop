package model.dungeon.tile;

import model.dungeon.entity.Entity;
import model.global.GlobalSprites;
import sprite.SpriteManager;
import sprite.SpriteManagerTile;
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

    public void draw(BufferedImage img, int x, int y){
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                x * GlobalSprites.getScaling() * GlobalSprites.get8Sprite(),
                y * GlobalSprites.getScaling() * GlobalSprites.get8Sprite(),
                spriteManager.getCurrentSprite().getWidth() * GlobalSprites.getScaling(),
                spriteManager.getCurrentSprite().getHeight() * GlobalSprites.getScaling(),
                null);
    }

    public void action(Entity entity){

    }

    public boolean canBeCrossed(){
        return true;
    }

}
