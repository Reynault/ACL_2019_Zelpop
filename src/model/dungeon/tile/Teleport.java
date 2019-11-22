package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Position;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerTile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Teleport extends Effect{
    Teleport(Tile decore) {
        super(decore);
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getHero());
    }

    @Override
    public void setImage() {
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
        // Retrieving information from maze
        int width = maze.getWidth();
        int height = maze.getHeight();

        // Fetching empty position in the maze
        int x, y;
        Random random = new Random();
        do{
            x = random.nextInt(height);
            y = random.nextInt(width);
        }while(maze.getEntity(x,y) != null || !maze.canMove(e, x,y));

        // Moving the entity
        e.setPosition(
                new Position(
                        x,
                        y,
                        e.getPosition().getCmd()
                )
        );
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

    @Override
    public boolean isBreakable() {
        return true;
    }
}
