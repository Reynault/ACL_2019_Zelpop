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
        decore.setImage();
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getBreakableWall());
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
