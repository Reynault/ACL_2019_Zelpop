package test;
import model.dungeon.Maze;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.mazeFactory.MazeFactory;
import model.global.Cmd;
import model.global.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class AttackSimpleTest {

    @Nested
    class attack_test{
        @Test
        void Hero_Attack_Is_Ok() {
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (1,1)
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            //Monster in (2.1)
            InputStream is =  AttackSimpleTest.class.getClassLoader().getResourceAsStream("level/testMaze.txt");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(), Cmd.RIGHT));
            maze.attack();

            Assertions.assertNotEquals(maze.getEntity(2,1).getMaxHp(),maze.getEntity(2,1).getHp());
        }

        @Test
        void Hero_Attack_Is_Ok_Monster_Is_Dead() {
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (1,1)
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            //Monster in (2.1)
            InputStream is =  AttackSimpleTest.class.getClassLoader().getResourceAsStream("level/testMaze.txt");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(), Cmd.RIGHT));
            maze.attack();
            maze.attack();

            Assertions.assertFalse(maze.getEntity(2,1).isAlive());

        }

        @Test
        void Monster_Attack_Is_Ok() {
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (1,1)
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            //Monster in (2.1)
            InputStream is =  AttackSimpleTest.class.getClassLoader().getResourceAsStream("level/testMaze.txt");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(), Cmd.RIGHT));

            maze.getEntity(2,1).setPosition(new Position(2,1, Cmd.LEFT));
            maze.getEntity(2,1).behave(maze, Cmd.ATTACK);

            Assertions.assertNotEquals(hero.getMaxHp(),hero.getHp());
        }

        @Test
        void Monster_Attack_Hero_Is_Dead() {
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (1,1)
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            //Monster in (2.1)
            InputStream is =  AttackSimpleTest.class.getClassLoader().getResourceAsStream("level/testMaze.txt");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(), Cmd.RIGHT));

            hero.takeDamage(50000);
            maze.getEntity(2,1).setPosition(new Position(2,1, Cmd.LEFT));
            maze.getEntity(2,1).behave(maze, Cmd.ATTACK);
            Assertions.assertFalse(hero.isAlive());

        }
    }
}