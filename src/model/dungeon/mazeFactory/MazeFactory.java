package model.dungeon.mazeFactory;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;
import model.global.GlobalDirection;
import model.global.Position;

import java.io.*;
import java.lang.reflect.Array;
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

    private Tile[][] generatorMaze(int size){
        Tile[][] tiles = new Tile[size][size];
        Cell[][] cells = new Cell[size][size];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }
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

        for (Cell[] row :
                cells) {
            for (Cell cell :
                    row) {
                System.out.print(cell);
            }
            System.out.print("\n");
        }

        ArrayList<Cell> region = new ArrayList<>();
        for (Cell[] row :
                cells) {
            region.addAll(Arrays.asList(row));
        }

        split(region);
        System.out.println("\n");

        for (Cell[] row :
                cells) {
            for (Cell cell :
                    row) {
                System.out.print(cell);
            }
            System.out.print("\n");
        }

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
        Cell rigth;
        Cell down;
        Cell left;
        Boolean wall = false;

        public Cell() {
        }

        public void setCell(Cell up, Cell rigth, Cell down, Cell left) {
            this.up = up;
            this.rigth = rigth;
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
            return "" + (wall? 1:0);
        }
    }

    private ArrayList<Cell> split(ArrayList<Cell> region){
        ArrayList<Cell> regionCopi = new ArrayList<>();
        regionCopi = (ArrayList<Cell>) region.clone();
        Random random = new Random();
        int cell1 = random.nextInt(region.size() - 1);
        int cell2 = cell1;
        while (cell1 == cell2){
            cell2 = random.nextInt(region.size() - 1);
        }
        ArrayList<Cell> region1 = new ArrayList<>();
        ArrayList<Cell> region2 = new ArrayList<>();

        region1.add(regionCopi.get(cell1));
        region2.add(regionCopi.get(cell2));

        regionCopi.remove(cell1);
        regionCopi.remove(cell2);

        Collections.shuffle(regionCopi);
        int kaput = 0;
        while (!regionCopi.isEmpty()){
            kaput++;
            ArrayList<Cell> found = new ArrayList<>();
            for (Cell cell : regionCopi) {
                if(region1.contains(cell.up) ||
                        region1.contains(cell.rigth) ||
                        region1.contains(cell.down) ||
                        region1.contains(cell.left)){
                    region1.add(cell);
                    found.add(cell);
                }else{
                    if(region2.contains(cell.up) ||
                            region2.contains(cell.rigth) ||
                            region2.contains(cell.down) ||
                            region2.contains(cell.left)){
                        region2.add(cell);
                        found.add(cell);
                    }
                }
            }
            regionCopi.removeAll(found);
            //TODO: REGLERE CE BUG
            if (kaput > region.size()*2){
                return null;
            }
        }

        if (region1.size() > 10  && region2.size() > 10){
            ArrayList<Cell> found = new ArrayList<>();
            for (Cell cell :
                    region1) {
                if(region2.contains(cell.up) ||
                        region2.contains(cell.rigth) ||
                        region2.contains(cell.down) ||
                        region2.contains(cell.left)) {
                    cell.setWall();
                    found.add(cell);
                }
            }
            region1.removeAll(found);
            if(found.size() > 1){
                found.get(random.nextInt(found.size() - 1)).wall = false;
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

