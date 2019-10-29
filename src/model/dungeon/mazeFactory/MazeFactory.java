package model.dungeon.mazeFactory;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MazeFactory {

    /**
     * Maze's getter
     * @return Maze
     */
    public Maze getMaze() {
        TileFactory tf = new TileFactory();
        int defaultLength = 10; // 10*10
        int defaultEntities = 5;
        Tile[][] tiles = new Tile[10][10];
        List<Entity> entities = new ArrayList<>(defaultEntities);

        //Generate a default list of tiles
        for(int i = 0; i < defaultLength ; i++){
            for(int j = 0; j < defaultLength ; j++) {
                tiles[i][j] = tf.generateTile();
            }
        }

        // Generate a default list of Entities
        for(int i = 0 ; i < defaultEntities ; i++){
            entities.add(EntityFactory.getInstance().generateMonster());
        }

        return new Maze(tiles, entities);
    }

    public Maze getMaze(File file) {
        return null;
    }
}
