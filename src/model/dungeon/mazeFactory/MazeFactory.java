package model.dungeon.mazeFactory;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;
import model.global.GlobalDirection;
import model.global.Position;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeFactory {

    /**
     * Maze's getter
     * @return Maze
     */
    public Maze getMaze() {
        int defaultLength = 20;
        int defaultEntities = 1;
        Tile[][] tiles = new Tile[defaultLength][defaultLength];
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

    /**************************************
    * w = wall
    * x = simple tile
    * c = chest
    * t = trap
    **************************************/
    /**
     * Maze's getter with a file's reader
     * @param file filename
     * @return Maze
     */
    public Maze getMaze(File file) {
        Tile[][] tiles = null;
        List<Entity> entities = null;
        try{
            InputStream flux = new FileInputStream(file);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String line;
            if ((line = buff.readLine()) != null){
                // Length and width for the maze
                String[] lengthWidth = line.split(","); // {x and y}
                String lengthWidth2 = lengthWidth[0];   // {x
                String[] lengthString = lengthWidth2.split("\\{");
                int length = Integer.parseInt(lengthString[1]);  // length of the maze

                lengthWidth2 = lengthWidth[1];  // y}
                String[] widthString = lengthWidth2.split("\\}");
                int width = Integer.parseInt(widthString[0]);   // width of the maze
                System.out.println("length : " + length + " | width : " + width);

                line = buff.readLine(); // next line

                // Creation of the maze
                tiles = new Tile[width][length];
                String[] lineOfMaze;
                String tile;
                String[] tileString;
                String typeOfTile;
                for(int i = 0; i < width ; i++){   // for each line
                    lineOfMaze = line.split("\\)");
                    for(int j = 0 ; j < length ; j++){   // for each "tile"
                        tile = lineOfMaze[j];
                        tileString = tile.split("\\(");
                        typeOfTile = tileString[1];
                        // Check the type of the tile
                        if(typeOfTile.compareTo("w") == 0){  // wall
                            tiles[i][j] = TileFactory.getWall();
                        }
                        else if(typeOfTile.compareTo("x") == 0){  // simple tile
                            tiles[i][j] = TileFactory.getEmptyTile();
                        }
                        else if(typeOfTile.compareTo("c") == 0){  // chest/treasure
                            tiles[i][j] = TileFactory.getTreasure();
                        }
                        else if(typeOfTile.compareTo("t") == 0){  // trap
                            tiles[i][j] = TileFactory.getTrap();
                        }
                    }
                    line = buff.readLine();
                }

                // Entities' placement
                int nbEntities = Integer.parseInt(line);
                entities = new ArrayList<>(nbEntities);

                String entityLine;  // the line of the entity
                String[] firstSplit;    // split the name and informations
                String withoutName;     // informations of the entity
                String[] info;          // split the informations
                String[] lastInfo;      // split the last information which has a }
                int x, y;
                for(int i = 0; i < nbEntities + 1 ; i++){   // enemies + hero
                    line = buff.readLine(); // next line
                    entityLine = line;
                    firstSplit = entityLine.split("\\{");
                    withoutName = firstSplit[1]; // x,y}
                    info = withoutName.split(",");
                    x = Integer.parseInt(info[0]);
                    lastInfo = info[1].split("\\}");    // The last information has a }
                    y = Integer.parseInt(lastInfo[0]);
                    if(firstSplit[0].compareTo("Hero") == 0) {
                        EntityFactory.getInstance().getHero().setPosition(new Position(x, y, GlobalDirection.DOWN));
                    }
                    else {  // It's an enemy
                        entities.add(EntityFactory.getInstance().getRandomMonster(
                                new Position(x,y, GlobalDirection.IDLE))
                        );
                    }
                }
            }
            // Close the buffers
            buff.close();
            lecture.close();
            flux.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return new Maze(tiles, entities);
    }
}

