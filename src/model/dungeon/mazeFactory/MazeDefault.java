package model.dungeon.mazeFactory;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MazeDefault implements MazeFactory {

    @Override
    public Maze getMaze() {
        TileFactory tf = new TileFactory();
        int defaultLength = 10; // 10*10
        int defaultEntities = 5;
        List<Tile> tiles = new ArrayList<>(defaultLength);
        List<Entity> entities = new ArrayList<>(defaultEntities);

        //Generate a default list of tiles
        for(int i = 0; i < defaultLength ; i++){
            for(int j = 0; j < defaultLength ; j++) {
                tiles.add(tf.generateTile());
            }
        }

        // Generate a default list of Entities
        for(int i = 0 ; i < defaultEntities ; i++){
            entities.add(EntityFactory.getInstance().generateMonster());
        }

        return new Maze(tiles, entities);
    }

    @Override
    public Maze getMaze(File fileName) {
        return null;
    }
}
