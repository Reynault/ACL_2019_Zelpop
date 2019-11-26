package trash;

import model.dungeon.tile.Tile;
import model.dungeon.tile.TileFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Trash {
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
                    tiles[i][j] = TileFactory.getEmptyTile();
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
        Boolean pipop = false;

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
            if (pipop){
                return String.valueOf("+");
            }
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
            if (kaput > region.size()*10){
                System.out.print(regionCopi);
                System.out.print(region);
                for (Cell cell: regionCopi) {
                    cell.pipop = true;
                }
                return null;
            }
        }

        // Once all cells are assigned, we will create
        // the separation with walls
        if (region1.size() > 4  && region2.size() > 4){
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
                int indexToOpen = random.nextInt(found.size() - 1);
                found.get(indexToOpen).wall = false;
                // we check that with the wall we didn't block another door
                // every cell for wall
                // if an adjacent cell is not in R1 or R2 and is not a wall, than it must be a door
                for (Cell cell: found) {
                    if (cell.up != null && !region2.contains(cell.up) && !region1.contains(cell.up) && !cell.up.isWall()){
                        cell.wall = false;
                    }
                    if (cell.right != null && !region2.contains(cell.right) && !region1.contains(cell.right) && !cell.right.isWall()){
                        cell.wall = false;
                    }
                    if (cell.down != null && !region2.contains(cell.down) && !region1.contains(cell.down) && !cell.down.isWall()){
                        cell.wall = false;
                    }
                    if (cell.left != null && !region2.contains(cell.left) && !region1.contains(cell.left) && !cell.left.isWall()){
                        cell.wall = false;
                    }
                }

                // And we do the same for each region

                if (region1.size() > 5){
                    System.out.println("Size Region1 : " + region1.size());
                    split(region1);
                }

                if (region2.size() > 5){
                    System.out.println("Size Region2 : " + region2.size());
                    split(region2);
                }
            }
        }

        return null;
    }


}
