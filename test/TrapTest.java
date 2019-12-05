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
    void Hero_Move_Into_Trap_Damage() {
        EntityFactory entityFactory = new EntityFactory();
        Hero hero = entityFactory.generateHero();
        MazeFactory mazeFactory = new MazeFactory();
        InputStream is =  TrapTest.class.getClassLoader().getResourceAsStream("level/testTrap.txt");
        Maze maze = mazeFactory.getMaze(is, entityFactory);
        //hero position is (0,0)
        //position 0 1 is a Trap damage
        //hero position is down
        hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(), Cmd.DOWN));
        maze.moveEntity(hero,Cmd.DOWN);

        Assertions.assertNotEquals(hero.getMaxHp(),hero.getHp());
    }

    @Test
    void Hero_Move_Into_Trap_Teleporter() {
        EntityFactory entityFactory = new EntityFactory();
        Hero hero = entityFactory.generateHero();
        MazeFactory mazeFactory = new MazeFactory();
        InputStream is =  TrapTest.class.getClassLoader().getResourceAsStream("level/testTrap.txt");
        Maze maze = mazeFactory.getMaze(is, entityFactory);
        //hero position is (0,0)
        //position 1 0 is a Trap teleporter
        //hero position is right
        hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(), Cmd.RIGHT));
        Position p = new Position(hero.getPosition().getX()+1,hero.getPosition().getY(), Cmd.RIGHT);
        maze.moveEntity(hero,Cmd.RIGHT);

        Assertions.assertNotEquals(hero.getPosition().toString(), p.toString());
    }

    @Test
    void Hero_Attack_Trap_Damage(){
        EntityFactory entityFactory = new EntityFactory();
        Hero hero = entityFactory.generateHero();
        MazeFactory mazeFactory = new MazeFactory();
        InputStream is =  MazeTest.class.getClassLoader().getResourceAsStream("level/testTrap.txt");
        Maze maze = mazeFactory.getMaze(is, entityFactory);
        //hero position is (0,0)
        //position 0 1 is a Trap damage
        //hero position is down
        hero.setPosition(new Position(hero.getPosition().getX(),hero.getPosition().getY(),Cmd.DOWN));
        maze.attack();
        maze.moveEntity(hero,Cmd.DOWN);

        Assertions.assertEquals(hero.getMaxHp(),hero.getHp());
    }

    @Test
    void Hero_Attack_Trap_Teleporter() {
        EntityFactory entityFactory = new EntityFactory();
        Hero hero = entityFactory.generateHero();
        MazeFactory mazeFactory = new MazeFactory();
        InputStream is = MazeTest.class.getClassLoader().getResourceAsStream("level/testTrap.txt");
        Maze maze = mazeFactory.getMaze(is, entityFactory);
        //hero position is (0,0)
        //position 1 0 is a Trap teleporter
        //hero position is right
        hero.setPosition(new Position(hero.getPosition().getX(), hero.getPosition().getY(), Cmd.RIGHT));
        Position p = new Position(hero.getPosition().getX()+1, hero.getPosition().getY(), Cmd.RIGHT);
        maze.attack();
        maze.moveEntity(hero, Cmd.RIGHT);

        Assertions.assertEquals(hero.getPosition().toString(), p.toString());
    }
}