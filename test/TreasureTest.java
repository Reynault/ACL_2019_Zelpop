package test;

import model.dungeon.Maze;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.mazeFactory.MazeFactory;
import model.global.Cmd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class TreasureTest {

    @Test
    void action_For_Treasure() {
        EntityFactory entityFactory = new EntityFactory();
        //hero position is down
        //hero position is (1,1)
        //position 2 1 is a Trap
        Hero hero = entityFactory.generateHero();
        MazeFactory mazeFactory = new MazeFactory();

        //Position (2.1) is a wall
        InputStream is =  TreasureTest.class.getClassLoader().getResourceAsStream("level/testMaze3.txt");
        Maze maze = mazeFactory.getMaze(is, entityFactory);
        int scoreBefore = hero.getScore();
        maze.moveEntity(hero,Cmd.DOWN);

        Assertions.assertNotEquals(hero.getScore() , scoreBefore);

    }
}