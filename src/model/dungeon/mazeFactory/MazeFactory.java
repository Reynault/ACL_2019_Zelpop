package model.dungeon.mazeFactory;

import model.dungeon.Maze;

import java.io.File;

public interface MazeFactory {

    /**
     * Maze's getter
     * @return Maze
     */
    public abstract Maze getMaze();

    /**
     * Maze's getter using a File
     * @param fileName name of the file
     * @return Maze
     */
    public abstract Maze getMaze(File fileName);
}
