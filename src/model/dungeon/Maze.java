package model.dungeon;

import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.tile.Tile;
import model.global.GlobalDirection;
import model.global.Position;

import javax.xml.stream.FactoryConfigurationError;
import java.awt.image.BufferedImage;
import java.util.List;

import static engine.Cmd.RIGHT;

public class Maze {

    private List<Entity> entities;
    private Tile[][] tiles;
    private int width, height;
    private Hero hero;

    /**
     * Default constructor
     *
     * The maze must have at least one line and one column
     * @param tiles list of tiles
     * @param entities list of entities
     */
    public Maze(Tile[][] tiles, List<Entity> entities){
        this.tiles = tiles;
        width = tiles.length;
        height = tiles[0].length;
        this.entities = entities;
        this.hero = EntityFactory.getInstance().getHero();
    }

    /**
     * Draw the maze
     * @param img
     */
    public void draw(BufferedImage img){
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
        for (Entity e :
                entities) {
            e.draw(img);
        }
        this.hero.draw(img);
    }

    /**
     * Move a entity in the maze using a direction
     * @param e entity in the maze
     * @param direction direction for the move
     */
    public void moveEntity(Entity e, GlobalDirection direction){
        int x, y;
        Position currentPosition, newPosition;

        // Generating a movement
        direction = e.behave(direction);

        if(direction != GlobalDirection.IDLE) {

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
     * @param entity entity who wants to move
     * @param x position of the target tile
     * @param y position of the target tile
     * @return true if the entity can move
     */
    private boolean canMove(Entity entity, int x, int y){
        Tile tile;
        boolean can = false;

        if(x >= 0 && x < width && y >= 0 && y < height){
            tile = tiles[x][y];
            if(tile.canBeCrossed()){
                can = true;
            }else{
                if(entity.canPassThrought()) {
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
        for (Entity e: entities){
            moveEntity(e, GlobalDirection.IDLE);
        }
    }
}
