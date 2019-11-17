package model.dungeon.mazeFactory;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.scoring.ScoringFactory;
import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;
import model.global.Cmd;
import model.global.Position;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MazeFactory implements Serializable {

    private static int MAZE_COUNTER = 0;

    private static int BASIC_MAZE_SCORE = 50;

    private static int TRAP_DAMAGE = 5;
    private static int TREASURE_GOLD = 50;

    /**
     * Maze's getter
     *
     * @return Maze
     */
    public Maze getMaze() {
        MAZE_COUNTER++;
        int defaultLength = 20;
        int defaultEntities = 1;
        Tile[][] tiles = new Tile[defaultLength][defaultLength];
        List<Entity> entities = new ArrayList<>(defaultEntities);
        Random random = new Random();
        //Generate a default list of tiles
        for (int i = 0; i < defaultLength; i++) {
            for (int j = 0; j < defaultLength; j++) {
                if (random.nextInt(5) == 0) {
                    tiles[i][j] = TileFactory.getWall();
                } else {
                    tiles[i][j] = TileFactory.generateTile();
                }
            }
        }

        // Generate a default list of Entities
        for (int i = 0; i < defaultEntities; i++) {
            entities.add(EntityFactory.getInstance().getRandomMonster(
                    new Position(0, 0, Cmd.IDLE))
            );
        }
        /*
        for (Tile[] row :
                tiles) {
            for (Tile tile :
                    row) {
                System.out.print(tile);
            }
            System.out.print("\n");
        }
        */
        return new Maze(tiles, entities, ScoringFactory.getSimpleScoring(
                MAZE_COUNTER,
                BASIC_MAZE_SCORE
        ));
    }

    /**************************************
     * w = wall
     * x = simple tile
     * c = chest
     * t = trap
     **************************************/
    /**
     * Maze's getter with a file's reader
     *
     * @param file filename
     * @return Maze
     */
    public Maze getMaze(InputStream file) {
        MAZE_COUNTER++;
        Tile[][] tiles = null;
        List<Entity> entities = null;
        try {
            InputStream flux = file;
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String line;
            if ((line = buff.readLine()) != null) {
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
                for (int i = 0; i < width; i++) {   // for each line
                    lineOfMaze = line.split("\\)");
                    for (int j = 0; j < length; j++) {   // for each "tile"
                        tile = lineOfMaze[j];
                        tileString = tile.split("\\(");
                        typeOfTile = tileString[1];
                        // Check the type of the tile
                        if (typeOfTile.compareTo("w") == 0) {  // wall
                            tiles[i][j] = TileFactory.getWall();
                        } else if (typeOfTile.compareTo("x") == 0) {  // simple tile
                            tiles[i][j] = TileFactory.getEmptyTile();
                        } else if (typeOfTile.compareTo("c") == 0) {  // chest/treasure
                            tiles[i][j] = TileFactory.getTreasure(TREASURE_GOLD);
                        } else if (typeOfTile.compareTo("t") == 0) {  // trap
                            tiles[i][j] = TileFactory.getTrap(TRAP_DAMAGE);
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
                for (int i = 0; i < nbEntities + 1; i++) {   // enemies + hero
                    line = buff.readLine(); // next line
                    entityLine = line;
                    firstSplit = entityLine.split("\\{");
                    withoutName = firstSplit[1]; // x,y}
                    info = withoutName.split(",");
                    x = Integer.parseInt(info[0]);
                    lastInfo = info[1].split("\\}");    // The last information has a }
                    y = Integer.parseInt(lastInfo[0]);
                    if (firstSplit[0].compareTo("Hero") == 0) {
                        EntityFactory.getInstance().getHero().setPosition(new Position(x, y, Cmd.DOWN));
                    } else {  // It's an enemy
                        entities.add(EntityFactory.getInstance().getRandomMonster(
                                new Position(x, y, Cmd.IDLE))
                        );
                    }
                }
            }
            // Close the buffers
            buff.close();
            lecture.close();
            flux.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return new Maze(tiles, entities, ScoringFactory.getSimpleScoring(
                MAZE_COUNTER,
                BASIC_MAZE_SCORE
        ));
    }

    private int specialTileRation = 10;
    private int entityRatio = 30;
    private boolean hasStairs;
    private int minimumDistance;

    /**
     * Generation of a simple maze using an algorithm that
     * split regions into 4 recursively in order to have a maze
     * filled with empty rooms
     *
     * @param size the size of the labyrinth
     * @return the maze
     */
    public Maze getRandomMaze(int size) {
        MAZE_COUNTER++;
        minimumDistance = 3/4;
        // The maze will have one stairs
        hasStairs = false;

        // init of return and int[][] to generate maze
        Tile[][] maze = new Tile[size][size];
        int[][] cells = new int[size][size];
        for (int[] row : cells) {
            for (int cell : row) {
                cell = 0;
            }
        }

        split(cells, 0, size, 0, size,
                new ArrayList<Integer>(Arrays.asList(0)),
                new ArrayList<Integer>(Arrays.asList(0)),
                new ArrayList<Integer>(Arrays.asList(0)),
                new ArrayList<Integer>(Arrays.asList(0)));

//        for (int[] row : cells) {
//            for (int cell : row) {
//                System.out.print(cell);
//            }
//            System.out.print("\n");
//        }

        // Getting random object and entity list
        Random random = new Random();
        ArrayList<Entity> entities = new ArrayList<>();


        // If there isn't any stairs, add one
        if (!hasStairs) {
            System.out.println("Boucle stairs debuggage");
            int x, y;
            do {
                x = random.nextInt(cells.length);
                y = random.nextInt(cells[x].length);
                // While the stairs are near the spawn and while it is in a wall
            } while (cells[x][y] != 0 && size * minimumDistance > (x+y));
            cells[x][y] = 4;
        }

        // convertion of int[][] into a maze
        int i = 0;
        int j = 0;
        for (int[] row : cells) {
            for (int cell : row) {
                switch (cell) {
                    case 1:
                        maze[i][j] = TileFactory.getWall();
                        break;
                    case 2:
                        maze[i][j] = TileFactory.getTrap(TRAP_DAMAGE);
                        break;
                    case 3:
                        maze[i][j] = TileFactory.getTreasure(TREASURE_GOLD);
                        break;
                    case 4:
                        maze[i][j] = TileFactory.getStairs();
                        break;
                    default:
                        maze[i][j] = TileFactory.generateTile();
                        // Then adding entity
                        if (random.nextInt(entityRatio) == 1) {
                            entities.add(EntityFactory.getInstance().getRandomMonster(
                                    new Position(j, i, Cmd.IDLE)
                            ));
                        }
                        break;
                }
                j++;
            }
            j = 0;
            i++;
        }

        return new Maze(maze, entities, ScoringFactory.getSimpleScoring(
                MAZE_COUNTER,
                BASIC_MAZE_SCORE
        ));
    }

    private void split(int[][] cells, int xmin, int xmax, int ymin, int ymax, ArrayList<Integer> prevxu, ArrayList<Integer> prevyr, ArrayList<Integer> prevxd, ArrayList<Integer> prevyl) {
        //final case to make sure we can split
        if (xmax - xmin < prevxd.size() + prevxd.size() + 3 || ymax - ymin < prevyl.size() + prevyr.size() + 3) {
            // If we can't split, we fill the room with tiles only if it has a certain dimension
            if (xmax - xmin > prevxd.size() + prevxd.size() && ymax - ymin > prevyl.size() + prevyr.size()) {
                fillRoom(cells, xmin, xmax, ymin, ymax);
            }
            return;
        }

        Random random = new Random();

        // selection on cut verticaly and horizontaly
        int cutx = prevxd.get(0);
        while (prevxd.contains(cutx) || prevxu.contains(cutx) || cutx == xmin || cutx == xmax - 1) {
            cutx = random.nextInt(xmax - xmin) + xmin;
        }
        int cuty = prevyl.get(0);
        while (prevyl.contains(cuty) || prevyr.contains(cuty) || cuty == ymin || cuty == ymax - 1) {
            cuty = random.nextInt(ymax - ymin) + ymin;
        }

        // placing 1 on cut lines
        for (int i = ymin; i < ymax; i++) {
            for (int j = xmin; j < xmax; j++) {
                if (i == cuty || j == cutx) {
                    cells[i][j] = 1;
                }
            }
        }

//        System.out.println("Cutx: " + cutx + " | xmin: " + xmin + " | xmax: " + xmax );
//        System.out.println("Cuty: " + cuty + " | ymin: " + ymin + " | ymax: " + ymax );
        // generatin 2 set of 2 door to remove to like 4 regions together
        int doorx1 = random.nextInt(cutx - xmin) + xmin;
        int doorx2 = random.nextInt(xmax - cutx - 1) + cutx + 1;
        int doory1 = random.nextInt(cuty - ymin) + ymin;
        int doory2 = random.nextInt(ymax - cuty - 1) + cuty + 1;

        // oppening the door
        cells[cuty][doorx1] = 0;
        cells[cuty][doorx2] = 0;

        cells[doory1][cutx] = 0;
        cells[doory2][cutx] = 0;

        // calling recursivly on those 4 regions
        /**
         split(cells, xmin, cutx, ymin, cuty, doorx1, doory1);
         split(cells, cutx+1, xmax, ymin, cuty, doorx1, doory2);
         split(cells, xmin, cutx, cuty+1, ymax, doorx2, doory1);
         split(cells, cutx+1, xmax, cuty+1, ymax, doorx2, doory2);
         **/

        split(cells, xmin, cutx, ymin, cuty,
                prevxu,
                new ArrayList<Integer>(Arrays.asList(doory1)),
                new ArrayList<Integer>(Arrays.asList(doorx1)),
                prevyl);
        split(cells, cutx + 1, xmax, ymin, cuty,
                prevxu,
                prevyr,
                new ArrayList<Integer>(Arrays.asList(doorx2)),
                new ArrayList<Integer>(Arrays.asList(doory1)));
        split(cells, xmin, cutx, cuty + 1, ymax,
                new ArrayList<Integer>(Arrays.asList(doorx1)),
                new ArrayList<Integer>(Arrays.asList(doory2)),
                prevxd,
                prevyl);
        split(cells, cutx + 1, xmax, cuty + 1, ymax,
                new ArrayList<Integer>(Arrays.asList(doorx1)),
                prevyr,
                prevxd,
                new ArrayList<Integer>(Arrays.asList(doory2)));

    }


    private void fillRoom(int[][] cells, int xmin, int xmax, int ymin, int ymax) {
        Random random = new Random();
        int rand;
//        System.out.println("xmin : " + xmin);
//        System.out.println("xmax : " + xmax);
//        System.out.println("ymin : " + ymin);
//        System.out.println("ymax : " + ymax);
        for (int i = xmin; i < xmax; i++) {
            for (int j = ymin; j < ymax; j++) {
                rand = random.nextInt(specialTileRation);
                if (rand == 1) {
                    rand = random.nextInt(3);
                    switch (rand) {
                        case 1:
                            cells[j][i] = 2;
                            break;
                        case 2:
                            cells[j][i] = 3;
                            break;
                    }
                }
            }
        }
    }
}

