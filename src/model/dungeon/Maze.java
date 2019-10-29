package model.dungeon;

import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.tile.Tile;
import model.global.GlobalDirection;
import model.global.Position;

import javax.xml.stream.FactoryConfigurationError;
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
     */
    public void draw(){

    }

    /**
     * Move an entity in the maze
     * @param entity entity in the maze
     */
    public void moveEntity(Entity entity){

    }

    /**
     * Move a entity in the maze using a direction
     * @param entity entity in the maze
     * @param direction direction for the move
     */
    public void moveEntity(Entity entity, GlobalDirection direction){

    }

    /**
     * Verify if an entity can move or not
     * @param entity entity who wants to move
     * @param tile next tile for the entity
     * @return true if the entity can move
     */
    private boolean canMove(Entity entity, Tile tile){
        if(tile.canBeCrossed()){
            return true;
        }else{
            if(entity.canPassThrought()){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * Method that move all the entities non controlled by the player.
     */
    public void moveEntities() {
        GlobalDirection direction;
        int x, y;
        Position currentPosition, newPosition;
        boolean movementPossible;

        for (Entity e: entities){

            // Generating a movement
            direction = e.behave(GlobalDirection.IDLE);
            currentPosition = e.getPosition();
            newPosition = currentPosition;

            x = currentPosition.getX();
            y = currentPosition.getY();

            switch (direction){
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
                default:
                    break;
            }

            // Checking if movement is in the maze
            if(x >= 0 && x < width && y >= 0 && y < height){
                // In that case, chacking if entity can move
                if(canMove(e, tiles[x][y])){
                    newPosition = new Position(x, y, direction);
                }else{
                    newPosition = new Position(
                            currentPosition.getX(),
                            currentPosition.getY(),
                            direction
                    );
                }
            }

            e.setPosition(newPosition);

        }
    }
}
