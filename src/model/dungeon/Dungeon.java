package model.dungeon;

import model.dungeon.mazeFactory.MazeDefault;
import model.dungeon.mazeFactory.MazeFactory;
import model.dungeon.mazeFactory.MazeFile;
import model.global.GlobalDirection;

import java.io.File;

public class Dungeon {

    private Maze currentMaze;
    private MazeFactory mazeDefault;
    private MazeFactory mazeFile;

    /**
     * Default constructor
     */
    public Dungeon(){
        mazeDefault = new MazeDefault();
        mazeFile = new MazeFile();
    }

    /**
     * Move the hero with the direction
     * @param direction direction for the move
     */
    public void moveHero(GlobalDirection direction){

    }

    /**
     * Generate a maze
     */
    public void generateMaze(){
        this.currentMaze = mazeDefault.getMaze();
    }

    /**
     * Generate a maze with a file
     * @param filename name of the file
     */
    public void generateMaze(File filename){
        this.currentMaze = mazeFile.getMaze(filename);
    }

    /**
     * Update the whole dungeon
     */
    public void updateAll(){

    }

    /**
     * Drawn the dungeon
     */
    public void draw(){

    }
}
