package model.dungeon;

import model.dungeon.entity.Entity;

public class ColisionManager {

    private static ColisionManager instance = new ColisionManager();
    private Maze maze;

    public static ColisionManager getInstance(){
        return instance;
    }

    public boolean canMove(Entity e, int x, int y){
        return false;
    }

    public boolean canMove(Entity e1, Entity e2){
        return false;
    }

    public void setMaze(Maze maze){
        this.maze = maze;
    }
}
