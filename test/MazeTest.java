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

class MazeTest {

    @Nested
    class moveEntity_Tests {
        @Test
        void moveEntity_Not_In_Idle_Position() {

            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (0,0)
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();
            Maze maze = mazeFactory.getRandomMaze(30, entityFactory);

            maze.moveEntity(hero,Cmd.DOWN);

            Assertions.assertEquals(0,hero.getPosition().getX());
            Assertions.assertEquals(1,hero.getPosition().getY());

        }

        @Test
        void moveEntity__In_Idle_Position() {
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero is on position (0,0)
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();
            Maze maze = mazeFactory.getRandomMaze(30, entityFactory);

            maze.moveEntity(hero,Cmd.UP);

            Assertions.assertEquals(hero.getPosition().getX(),0);
            Assertions.assertEquals(hero.getPosition().getY(),0);
            Assertions.assertEquals(hero.getPosition().getCmd(),Cmd.UP);
        }

        @Test
        void moveEntity_Not_Ok_Position() {
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero is on position (0,0)

            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();
            Maze maze = mazeFactory.getRandomMaze(30, entityFactory);
            maze.moveEntity(hero,Cmd.LEFT);
            Assertions.assertEquals(hero.getPosition().getX(),0);
            Assertions.assertEquals(hero.getPosition().getY(),0);
        }

        @Test
        void Hero_Move_Valide_Pos(){
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (0,0)
            //position 1 0 non valide
            Hero hero = entityFactory.generateHero();
            hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(),Cmd.RIGHT));
            MazeFactory mazeFactory = new MazeFactory();
            Maze maze = mazeFactory.getRandomMaze(30, entityFactory);
            maze.moveEntity(hero, Cmd.RIGHT);
            Assertions.assertEquals(1,hero.getPosition().getX());
            Assertions.assertEquals(0,hero.getPosition().getY());
        }

        @Test
        void Hero_Move_Not_Valide_Pos_OutSide_The_Maze(){
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (0,0)
            //position -1 0 non valide
            Hero hero = entityFactory.generateHero();
            hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(),Cmd.LEFT));
            MazeFactory mazeFactory = new MazeFactory();
            Maze maze = mazeFactory.getRandomMaze(30, entityFactory);

            maze.moveEntity(hero,Cmd.LEFT);

            Assertions.assertEquals(0,hero.getPosition().getX());
            Assertions.assertEquals(0,hero.getPosition().getY());
        }

        @Test
        void Hero_Move_Not_Valide_Pos_Walking_Into_Wall(){
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (1,1)
            //position 2 1 non valide is a wall
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            //Position (2.1) is a wall
            InputStream is =  MazeTest.class.getClassLoader().getResourceAsStream("level/testMaze.txt");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(),Cmd.RIGHT));
            maze.moveEntity(hero,Cmd.RIGHT);

            Assertions.assertEquals(1,hero.getPosition().getX());
            Assertions.assertEquals(1,hero.getPosition().getY());
        }

        @Test
        void Hero_Move_Into_Trap(){
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (1,1)
            //position 2 1 is a Trap
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            //Position (2.1) is a wall
            InputStream is =  MazeTest.class.getClassLoader().getResourceAsStream("level/testMaze3.txt");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(),Cmd.RIGHT));
            maze.moveEntity(hero,Cmd.RIGHT);


            Assertions.assertNotEquals(hero.getMaxHp(),hero.getHp());
        }

        @Test
        void Hero_Move_Into_Treasure(){
            EntityFactory entityFactory = new EntityFactory();
            //hero position is down
            //hero position is (1,1)
            //position 2 1 is a Trap
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            //Position (2.1) is a wall
            InputStream is =  MazeTest.class.getClassLoader().getResourceAsStream("level/testMaze3.txt");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            int scoreBefore = hero.getScore();
            maze.moveEntity(hero,Cmd.DOWN);

            Assertions.assertNotEquals(hero.getScore() , scoreBefore);

        }

    }

    @Nested
    class moveEntities_Tests{
        @Test
        void Monster_Move_Valide_Pos(){
            EntityFactory entityFactory = new EntityFactory();
            //monster position is 5,2)
            //all positions around him are free
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            InputStream is =  MazeTest.class.getClassLoader().getResourceAsStream("level/testMaze.txt");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            maze.moveEntities();

            Assertions.assertEquals(null, maze.getEntity(5,2));
        }

        @Test
        void Monster_Move_Not_Valide_Pos_Walking_Into_Wall(){
            EntityFactory entityFactory = new EntityFactory();
            //monster position is (6,8)
            //all positions around the monsters are non valide
            Hero hero = entityFactory.generateHero();
            MazeFactory mazeFactory = new MazeFactory();

            InputStream is =  MazeTest.class.getClassLoader().getResourceAsStream("level/testMaze2");
            Maze maze = mazeFactory.getMaze(is, entityFactory);
            maze.moveEntities();

            Assertions.assertNotEquals(null, maze.getEntity(6,8));
            Assertions.assertEquals(6,maze.getEntity(6,8).getPosition().getX());
            Assertions.assertEquals(8,maze.getEntity(6,8).getPosition().getY());
        }
    }


}