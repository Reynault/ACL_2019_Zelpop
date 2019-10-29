package model.dungeon;

import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.mazeFactory.MazeDefault;
import model.dungeon.mazeFactory.MazeFactory;
import model.dungeon.mazeFactory.MazeFile;
import model.global.GlobalDirection;

import java.io.File;

public class Dungeon {

    private Maze currentMaze;
    private MazeFactory mazeDefault;
    private MazeFactory mazeFile;
    private Hero hero;

    /**
     * Default constructor
     */
    public Dungeon(){
        mazeDefault = new MazeDefault();
        mazeFile = new MazeFile();
        this.hero = EntityFactory.getInstance().generateHero();
        generateMaze();
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
    private void generateMaze(){
        this.currentMaze = mazeDefault.getMaze();
    }

    /**
     * Generate a maze with a file
     * @param filename name of the file
     */
    private void generateMaze(File filename){
        this.currentMaze = mazeFile.getMaze(filename);
    }

    /**
     * Update the whole dungeon
     */
    public void updateAll(){
        currentMaze.moveEntities();
    }

    /**
     * Drawn the dungeon
     */
    public void draw(){

    }
}
