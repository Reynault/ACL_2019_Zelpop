package model.dungeon.tile;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Position;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManagerTile;

import java.util.Random;

public class Teleport extends Effect{
    private boolean isTriggered;

    Teleport(int hp, Tile decore) {
        super(hp, decore);
        spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTraps());
        isTriggered = false;
    }

    @Override
    public void setImage() {
        decore.setImage();
        if(isTriggered){
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTiles());
        }else {
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTraps());
        }
    }

    @Override
    public void action(Maze maze, Entity e) {
        if(!isTriggered) {
            // Retrieving information from maze
            int width = maze.getWidth();
            int height = maze.getHeight();

            // Fetching empty position in the maze
            int x, y;
            Random random = new Random();
            do {
                x = random.nextInt(height);
                y = random.nextInt(width);
            } while (maze.getEntity(x, y) != null || !maze.canMove(e, x, y));

            // Moving the entity
            e.setPosition(
                    new Position(
                            x,
                            y,
                            e.getPosition().getCmd()
                    )
            );

            isTriggered = true;
            spriteManager = new SpriteManagerTile(TextureFactory.getTextureFactory().getTiles());
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

    @Override
    public boolean isBreakable() {
        return true;
    }
}
