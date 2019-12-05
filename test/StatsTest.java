import model.dungeon.Dungeon;
import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.entity.Monster;
import model.dungeon.mazeFactory.MazeFactory;
import model.global.Cmd;
import model.global.Position;
import org.junit.jupiter.api.*;

import java.io.InputStream;

/**
 * Class test providing tests for defence, attack, regen and HP stats for entities
 */
class StatsTest {

    Maze maze;
    Hero hero;
    Entity monster;

    @BeforeEach
    public void init() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("level/testMazeStat.txt");
        EntityFactory entityFactory = new EntityFactory();
        MazeFactory mazeFactory = new MazeFactory();
        entityFactory.generateHero();
        maze = mazeFactory.getMaze(is, entityFactory);
        hero = maze.getHero();
        hero.setPosition(new Position(
                hero.getPosition().getX(),
                hero.getPosition().getY(),
                Cmd.UP
        ));
        monster = maze.getEntity(1,1);
    }

    @Nested
    @DisplayName("Defence")
    class Counter {

        @Nested
        @DisplayName("Right")
        class Right {

            /**
             * Testing the case where an entity has a positive defence stat and
             * another entity is attacking, and then dying from the counter
             */
            @Test
            public void positive_tuer() {
                hero.setDefence((int) monster.getMaxHp());
                maze.moveEntities();
                System.out.println(hero.getHp());
                System.out.println(hero.getDef());
                System.out.println(monster.getMaxHp());
                System.out.println(monster.getHp());
                Assertions.assertFalse(monster.isAlive());
            }

        }

    }

    @Nested
    @DisplayName("Regen")
    class Regen {

    }

    @Nested
    @DisplayName("Attack")
    class Attack {

    }

    @Nested
    @DisplayName("HP")
    class HP {

    }

}