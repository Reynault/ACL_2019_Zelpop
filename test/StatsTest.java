import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
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
        monster = maze.getEntity(1, 1);
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
                Assertions.assertFalse(monster.isAlive());
            }

            /**
             * Testing the case where an entity has a positive defence stat and
             * another entity is attacking, and then taking damages without being killed
             */
            @Test
            public void positive_pas_tuer() {
                hero.setDefence(1);
                maze.moveEntities();
                Assertions.assertEquals(monster.getMaxHp() - 1, monster.getHp());
            }

            /**
             * Testing the case where an entity has a defence equals to zero,
             * the attacker keep his HP.
             */
            @Test
            public void zero() {
                hero.setDefence(0);
                maze.moveEntities();
                Assertions.assertEquals(monster.getMaxHp(), monster.getHp());
            }

        }

        @Nested
        @DisplayName("Boundary")
        class Boundary {

            /**
             * Testing the case where an entity has a negative defence, the other entity will then
             * gain HP
             */
            @Test
            public void negative_alive() {
                monster.takeDamage(5);
                hero.setDefence(-5);
                maze.moveEntities();
                Assertions.assertEquals(monster.getMaxHp(), monster.getHp());
            }

            /**
             * Testing the case where an entity has a negative defence, the other entity will not gain
             * HP because it has already reached its max
             */
            @Test
            public void negative_full_hp() {
                hero.setDefence(-5);
                maze.moveEntities();
                Assertions.assertEquals(monster.getMaxHp(), monster.getHp());
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