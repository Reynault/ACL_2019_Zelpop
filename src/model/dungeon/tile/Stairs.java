package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import sound.SoundManager;
import sound.SoundManagerFactory;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Stairs extends Effect{

    Stairs(int hp, Tile decore) {
        super(hp, decore, null);
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
    public void action(Maze maze, Entity e) {}

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
