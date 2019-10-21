package model.dungeon;

import model.dungeon.entity.Entity;
import model.dungeon.tile.Tile;
import model.global.GlobalDirection;

import javax.xml.stream.FactoryConfigurationError;
import java.util.List;

public class Maze {
    public Maze(List<Tile> tiles, List<Entity> entities){

    }

    public void draw(){

    }

    public void updateEntities(){

    }

    public void moveEntity(Entity entity){

    }

    public void moveEntity(Entity entity, GlobalDirection direction){

    }

    private boolean canMove(Entity entity, Tile tile){
        //owe can alway move on the tile
        return true;
    }
}
