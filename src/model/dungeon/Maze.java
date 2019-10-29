package model.dungeon;

import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.tile.Tile;
import model.global.GlobalDirection;

import javax.xml.stream.FactoryConfigurationError;
import java.util.List;

public class Maze {

    private List<Entity> entities;
    private List<Tile> tiles;
    private Hero hero;

    /**
     * Default constructor
     * @param tiles list of tiles
     * @param entities list of entities
     */
    public Maze(List<Tile> tiles, List<Entity> entities){
        this.tiles = tiles;
        this.entities = entities;
        this.hero = EntityFactory.getInstance().getHero();
    }

    /**
     * Draw the maze
     */
    public void draw(){

    }

    /**
     * Update the entities in the maze
     */
    public void updateEntities(){

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
        //we can alway move on the tile
        return true;
    }

    public void moveEntities() {
        for (Entity e: entities){
            e.behave();
        }
    }
}
