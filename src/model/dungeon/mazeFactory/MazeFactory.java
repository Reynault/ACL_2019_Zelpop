package model.dungeon.mazeFactory;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;
import model.global.GlobalDirection;
import model.global.Position;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeFactory {

    /**
     * Maze's getter
     * @return Maze
     */
    public Maze getMaze() {
        int defaultLength = 20; // 10*10
        int defaultEntities = 1;
        Tile[][] tiles = new Tile[20][20];
        List<Entity> entities = new ArrayList<>(defaultEntities);
        Random random = new Random();
        //Generate a default list of tiles
        for(int i = 0; i < defaultLength ; i++){
            for(int j = 0; j < defaultLength ; j++) {
                if(random.nextInt(5) == 0){
                    tiles[i][j] = TileFactory.getWall();
                }else{
                    tiles[i][j] = TileFactory.generateTile();
                }
            }
        }

        // Generate a default list of Entities
        for(int i = 0 ; i < defaultEntities ; i++){
            entities.add(EntityFactory.getInstance().getRandomMonster(
                    new Position(0,0, GlobalDirection.IDLE))
            );
        }
        for (Tile[] row :
                tiles) {
            for (Tile tile :
                    row) {
                System.out.print(tile);
            }
            System.out.print("\n");
        }

        return new Maze(tiles, entities);
    }

    public Maze getMaze(File file) {
        return null;
    }
}
