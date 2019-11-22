package test;

import model.dungeon.Maze;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.mazeFactory.MazeFactory;
import model.global.Cmd;
import model.global.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class TrapTest {

    @Test
    void action_Trap() {
        EntityFactory entityFactory = new EntityFactory();
        //hero position is down
        //hero position is (1,1)
        //position 2 1 is a Trap
        Hero hero = entityFactory.generateHero();
        MazeFactory mazeFactory = new MazeFactory();

        //Position (2.1) is a wall
        InputStream is =  TrapTest.class.getClassLoader().getResourceAsStream("level/testMaze3.txt");
        Maze maze = mazeFactory.getMaze(is, entityFactory);
        hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(), Cmd.RIGHT));
        maze.moveEntity(hero,Cmd.RIGHT);


        Assertions.assertNotEquals(hero.getMaxHp(),hero.getHp());
    }
}