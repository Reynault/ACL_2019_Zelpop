package model.dungeon.mazeFactory;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;
import model.global.GlobalDirection;
import model.global.Position;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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
    public Maze getMaze(InputStream file) {
        Tile[][] tiles = null;
        List<Entity> entities = null;
        try{
            InputStream flux = file;
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

        tiles = generatorMaze(22);

        return new Maze(tiles, entities);
    }

    /**
     * Generation of a simple maze using an algorithm that
     * split regions into 2 recursively in order to have a maze
     * filled with empty rooms
     * @param size the size of the labyrinth
     * @return the maze
     */
    private Tile[][] generatorMaze(int size){
        Tile[][] tiles = new Tile[size][size];
        Cell[][] cells = new Cell[size][size];

        // Initialise cell table
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }

        // Initialise values of cells
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (i == 0){
                    if (j == 0){
                        cells[i][j].setCell(null, cells[i][j + 1], cells[i + 1][j], null);
                    }else{
                        if (j == cells[0].length - 1){
                            cells[i][j].setCell(null, null, cells[i + 1][j], cells[i][j - 1]);
                        }else{
                            cells[i][j].setCell(null, cells[i][j + 1], cells[i + 1][j], cells[i][j - 1]);
                        }
                    }
                }else{
                    if (i == cells.length - 1){
                        if (j == 0){
                            cells[i][j].setCell(cells[i - 1][j], cells[i][j + 1], null, null);
                        }else {
                            if (j == cells[0].length - 1){
                                cells[i][j].setCell(cells[i - 1][j], null, null, cells[i][j - 1]);
                            }else{
                                cells[i][j].setCell(cells[i - 1][j], cells[i][j + 1], null, cells[i][j - 1]);

                            }
                        }
                    }else{
                        if (j == 0){
                            cells[i][j].setCell(cells[i - 1][j], cells[i][j + 1], cells[i + 1][j], null);
                        }else {
                            if (j == cells[0].length - 1){
                                cells[i][j].setCell(cells[i - 1][j], null, cells[i + 1][j], cells[i][j - 1]);
                            }else{
                                cells[i][j].setCell(cells[i - 1][j], cells[i][j + 1], cells[i + 1][j], cells[i][j - 1]);
                            }
                        }
                    }
                }
            }
        }

        // Just displaying some information
        for (Cell[] row :
                cells) {
            for (Cell cell :
                    row) {
                System.out.print(cell);
            }
            System.out.print("\n");
        }

        // create a region out of cells
        ArrayList<Cell> region = new ArrayList<>();
        for (Cell[] row :
                cells) {
            region.addAll(Arrays.asList(row));
        }

        // split the list in order to have two regions
        // and split each new regions into two again, and again, ...
        split(region);
        System.out.println("\n");

        // Just displaying some information
        for (Cell[] row :
                cells) {
            for (Cell cell :
                    row) {
                System.out.print(cell);
            }
            System.out.print("\n");
        }

        // Translate the cell table into a tile table
        int i=0;
        int j=0;
        for (Cell[] row :
                cells) {
            for (Cell cell :
                    row) {
                if(cell.isWall()){
                    tiles[i][j] = TileFactory.getWall();
                }else{
                    tiles[i][j] = TileFactory.generateTile();
                }
                j++;
            }
            j=0;
            i++;
        }


        return tiles;
    }

    class Cell{
        Cell up;
        Cell right;
        Cell down;
        Cell left;
        Boolean wall = false;

        public Cell() {
        }

        public void setCell(Cell up, Cell rigth, Cell down, Cell left) {
            this.up = up;
            this.right = rigth;
            this.down = down;
            this.left = left;
        }
        public Boolean isWall() {
            return wall;
        }
        public void setWall() {
            this.wall = true;
        }

        @Override
        public String toString() {
            if(wall){
                return String.valueOf(1);
            }else{
                return String.valueOf(0);
            }
        }
    }

    private ArrayList<Cell> split(ArrayList<Cell> region){
        ArrayList<Cell> regionCopi = new ArrayList<>();
        // We make a copy, cause we will work with a brand new one
        // which will be used as a set
        regionCopi = (ArrayList<Cell>) region.clone();
        Random random = new Random();

        // We chose two random cells
        int cell1 = random.nextInt(region.size() - 1);
        int cell2 = cell1;
        while (cell1 == cell2){
            cell2 = random.nextInt(region.size() - 1);
        }

        // We instantiate two sub regions
        ArrayList<Cell> region1 = new ArrayList<>();
        ArrayList<Cell> region2 = new ArrayList<>();

        // Each one will receive one cell
        region1.add(regionCopi.get(cell1));
        region2.add(regionCopi.get(cell2));

        // Each cell is then removed from the main region
        // (in order to make sure that we will not assign them again)
        regionCopi.remove(cell1);
        regionCopi.remove(cell2);

        Collections.shuffle(regionCopi);
        int kaput = 0;

        // While the main set isn't empty, it means that we haven't assign every
        // cell yet
        while (!regionCopi.isEmpty()){
            kaput++;
            ArrayList<Cell> found = new ArrayList<>();
            // For each cells contained in the set
            for (Cell cell : regionCopi) {
                // We check if there siblings are in one of
                // the two regions, it will then be assign to it
                if(region1.contains(cell.up) ||
                        region1.contains(cell.right) ||
                        region1.contains(cell.down) ||
                        region1.contains(cell.left)){
                    region1.add(cell);
                    found.add(cell);
                }else if(region2.contains(cell.up) ||
                            region2.contains(cell.right) ||
                            region2.contains(cell.down) ||
                            region2.contains(cell.left)){
                        region2.add(cell);
                        found.add(cell);
                }
            }

            // Then we remove every assigned cell from
            // the main set
            regionCopi.removeAll(found);
            //TODO: REGLER CE BUG
            if (kaput > region.size()*2){
                return null;
            }
        }

        // Once all cells are assigned, we will create
        // the separation with walls
        if (region1.size() > 10  && region2.size() > 10){
            ArrayList<Cell> found = new ArrayList<>();
            // For each cells in the first region,
            // if there is a sibling that is from de second region,
            // then it's a wall
            for (Cell cell :
                    region1) {
                if(region2.contains(cell.up) ||
                        region2.contains(cell.right) ||
                        region2.contains(cell.down) ||
                        region2.contains(cell.left)) {
                    cell.setWall();
                    found.add(cell);
                }
            }
            // And we remove all the wall from the first region
            region1.removeAll(found);
            if(found.size() > 1){
                // Then we create a door
                found.get(random.nextInt(found.size() - 1)).wall = false;

                // And we do the same for each region


                if (region1.size() > 20){
                    System.out.println("Size Region1 : " + region1.size());
                    split(region1);
                }

                if (region2.size() > 20){
                    System.out.println("Size Region2 : " + region2.size());
                    split(region2);
                }
            }
        }

        return null;
    }


}

