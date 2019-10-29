package model.dungeon.mazeFactory;

import model.dungeon.Maze;
import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MazeDefault implements MazeFactory {

    @Override
    public Maze getMaze() {
        int defaultValue = 10; // 10*10
        List<Tile> tiles = new ArrayList<>(defaultValue);
        //Generate a default list of tiles
        for(int i = 0; i < defaultValue ; i++){
            for(int j = 0; j < defaultValue ; j++) {
                tiles.add(TileFactory.generateTile());
            }
        }

        return new Maze(tiles, null);
    }

    @Override
    public Maze getMaze(File fileName) {
        return null;
    }
}
