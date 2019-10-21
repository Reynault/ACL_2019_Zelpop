package model.dungeon.mazeFactory;

import model.dungeon.Maze;

import java.io.File;

public interface MazeFactory {
    public abstract Maze getMaze();
    public abstract Maze getMaze(File fileName);
}
