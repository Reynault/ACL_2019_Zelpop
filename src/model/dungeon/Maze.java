package model.dungeon;

import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.tile.Tile;
import model.global.GlobalDirection;
import model.global.GlobalSprites;
import model.global.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Maze {

    private List<Entity> entities;
    private Tile[][] tiles;
    private int width, height;
    private Hero hero;

    /**
     * Default constructor
     * <p>
     * The maze must have at least one line and one column
     *
     * @param tiles    list of tiles
     * @param entities list of entities
     */
    public Maze(Tile[][] tiles, List<Entity> entities) {
        this.tiles = tiles;
        width = tiles[0].length;
        height = tiles.length;
        this.entities = entities;
        this.hero = EntityFactory.getInstance().getHero();
    }

    /**
     * Draw the maze
     *
     * @param img
     */
    public void draw(BufferedImage img) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        int width, height,
                xShift, yShift,
                currentX, currentY,
                tilePositionX, tilePositionY;

        int screenX = 9;
        Position posHero = hero.getPosition();

        crayon.setColor(Color.BLACK);

        width = img.getWidth();
        height = img.getHeight();

        xShift = width / 2;
        yShift = height / 2;

        tilePositionX = (GlobalSprites.getScaling() * GlobalSprites.get8Sprite());
        tilePositionY = (GlobalSprites.getScaling() * GlobalSprites.get8Sprite());

        // Drawing surrounding tiles
        for (int x = -screenX; x <= screenX; x++) {
            for (int y = -screenX; y <= screenX; y++) {
                currentX = posHero.getX() + x;
                currentY = posHero.getY() + y;
                // If the tile exists
                if (currentX >= 0 && currentY >= 0 && currentY < tiles.length && currentX < tiles[currentY].length) {
                    // Drawing the current tile
                    tiles[currentY][currentX].draw(img,
                            (x * tilePositionX) + xShift,
                            (y * tilePositionY) + yShift);

                    // Drawing entities on this tile
                    Entity e = getEntity(currentX, currentY);
                    if( e != null){
                        e.draw(img, (x * tilePositionX) + xShift, (y * tilePositionY) + yShift);
                    }
                } else {
                    // Otherwise, the void
                    crayon.fillRect(
                            (x * tilePositionX) + xShift,
                            (y * tilePositionY) + yShift,
                            GlobalSprites.get8Sprite() * GlobalSprites.getScaling(),
                            GlobalSprites.get8Sprite() * GlobalSprites.getScaling()
                    );
                }
            }
        }

        // Drawing hero at the center of the screen
        hero.draw(img, xShift, yShift);

        /*
        int x = 0;
        int y = 0;
        for (Tile[] row :
                tiles) {
            for (Tile tile :
                    row) {
                tile.draw(img, x, y);
                x++;
            }
            y++;
            x = 0;
        }
        if(entities != null && entities.size() > 0){
            for (Entity e :
                    entities) {
                e.draw(img);
            }
        }
        this.hero.draw(img);
        */

    }

    /**
     * Move a entity in the maze using a direction
     *
     * @param e         entity in the maze
     * @param direction direction for the move
     */
    public void moveEntity(Entity e, GlobalDirection direction) {
        int x, y;
        Position currentPosition, newPosition;

        // Generating a movement
        direction = e.behave(direction);

        if (direction != GlobalDirection.IDLE) {

            currentPosition = e.getPosition();

            x = currentPosition.getX();
            y = currentPosition.getY();

            switch (direction) {
                case LEFT:
                    x -= 1;
                    break;
                case UP:
                    y -= 1;
                    break;
                case DOWN:
                    y += 1;
                    break;
                case RIGHT:
                    x += 1;
                    break;
            }

            // Checking if movement is in the maze
            if (canMove(e, x, y)) {
                newPosition = new Position(x, y, direction);
            } else {
                newPosition = new Position(
                        currentPosition.getX(),
                        currentPosition.getY(),
                        direction
                );
            }

            e.setPosition(newPosition);
        }
    }

    /**
     * Verify if an entity can move or not
     *
     * @param entity entity who wants to move
     * @param x      position of the target tile
     * @param y      position of the target tile
     * @return true if the entity can move
     */
    private boolean canMove(Entity entity, int x, int y) {
        Tile tile;
        boolean can = false;

        if (x >= 0 && x < width && y >= 0 && y < height) {
            tile = tiles[y][x];
            if (tile.canBeCrossed()) {
                can = true;
            } else {
                if (entity.canPassThrought()) {
                    can = true;
                }
            }
        }

        return can;
    }

    /**
     * Method that move all the entities non controlled by the player.
     */
    public void moveEntities() {
        if (entities != null && entities.size() > 0) {
            for (Entity e : entities) {
                moveEntity(e, GlobalDirection.IDLE);
            }
        }
    }

    /**
     * Method getEntity, it fetch an entity from a specify tile on the maze
     */
    public Entity getEntity(int x, int y){
        Entity res = null;
        for(Entity entity: entities){
            if(entity.getPosition().getY() == y && entity.getPosition().getX() == x){
                res = entity;
            }
        }
        return res;
    }
}
