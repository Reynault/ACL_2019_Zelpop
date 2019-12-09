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
    @DisplayName("HP")
    class HP {

        @Nested
        @DisplayName("Right")
        class Right {

            @Test
            public void positive(){
                hero.setHP(50);

                Assertions.assertTrue(hero.isAlive());
            }

            @Test
            public void negtive(){
                hero.setHP(-690);

                Assertions.assertFalse(hero.isAlive());
            }

            @Test
            public void zero(){
                hero.setHP(0);

                Assertions.assertTrue(hero.isAlive());
            }


        }
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
            public void positive_dead() {
                hero.setDefence((int) monster.getMaxHp() + 1);
                maze.moveEntities();
                Assertions.assertFalse(monster.isAlive());
            }

            /**
             * Testing the case where an entity has a positive defence stat and
             * another entity is attacking, and then taking damages without being killed
             */
            @Test
            public void positive_not_dead() {
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
             * Testing the case where an entity has a negative defence, the other entity will gain
             * HP without having more than the max amount
             */
            @Test
            public void positive() {
                hero.setDefence((int) -monster.getMaxHp());
                monster.takeDamage(5);

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

        @Nested
        @DisplayName("Right")
        class Right {

            /**
             * Testing the case where an entity has a positive regeneration, is alive and don't have
             * the maximum amount of pv
             */
            @Test
            public void positive(){
                hero.takeDamage(1);
                maze.regenEntities();

                Assertions.assertEquals(hero.getMaxHp(), hero.getHp());
            }

            /**
             * Testing the case where an entity has a positive regeneration, is alive and have
             * the maximum amount of pv.
             *
             * It's HP can't regen.
             */
            @Test
            public void positive_max(){
                maze.regenEntities();

                Assertions.assertEquals(hero.getMaxHp(), hero.getHp());
            }

            /**
             * Testing the case where an entity has a positive regeneration, is alive, don't have
             * the maximum amount of pv and has a regeneration > than the HP needed to reach the maximum.
             * <p>
             * It's HP must regen until the max.
             */
            @Test
            public void positive_more() {
                hero.setVitality(hero.getMaxHp());
                hero.takeDamage(1);

                maze.regenEntities();

                Assertions.assertEquals(hero.getMaxHp(), hero.getHp());
            }
        }

        @Nested
        @DisplayName("Boundary")
        class Boundary {

            /**
             * Testing the case where an entity has a positive regeneration, is dead and don't have
             * the maximum amount of pv.
             * <p>
             * It's HP must regen.
             */
            @Test
            public void positive_dead() {
                hero.takeDamage(hero.getMaxHp());

                hero.takeDamage(hero.getVitality());
                maze.regenEntities();

                Assertions.assertEquals(0, hero.getHp());
            }


            /**
             * Testing the case where an entity has a zero regeneration, is alive and have
             * the maximum amount of pv.
             *
             * It's HP must be the same.
             */
            @Test
            public void zero(){
                hero.setVitality(0);
                maze.regenEntities();

                Assertions.assertEquals(hero.getMaxHp(), hero.getHp());
            }

            /**
             * Testing the case where an entity has a negative regeneration, is alive and have
             * the maximum amount of pv.
             *
             * It's HP must decrease.
             */
            @Test
            public void negative(){
                hero.setVitality(-1);
                maze.regenEntities();

                Assertions.assertEquals(hero.getMaxHp()+hero.getVitality(), hero.getHp());
            }
        }

    }

    @Nested
    @DisplayName("Attack")
    class Attack {

        @Nested
        @DisplayName("Right")
        class Right {

            /**
             * Testing the case where an entity is attacking an other one. The other takes the
             * amount of damages
             */
            @Test
            public void positive(){
                hero.setDmg((int)monster.getMaxHp() + 1);

                maze.attack();

                Assertions.assertFalse(monster.isAlive());
            }

            /**
             * Testing the case where a dead entity is attacking an other one. It does nothing.
             */
            @Test
            public void positive_dead(){
                hero.takeDamage(hero.getMaxHp()+1);

                maze.attack();

                Assertions.assertEquals(monster.getMaxHp(), monster.getHp());
            }

        }


        @Nested
        @DisplayName("Boundary")
        class Boundary {

            /**
             * Testing the case where an entity has zero attack, it does nothing
             */
            @Test
            public void zero(){
                hero.setDmg(0);

                maze.attack();

                Assertions.assertEquals(monster.getMaxHp(), monster.getHp());
            }

            /**
             * Testing the case where an entity has a negative attack, it heals
             * the victim.
             *
             * In this case, the victim do have all its life.
             */
            @Test
            public void negative_max(){
                hero.setDmg((int) -monster.getMaxHp());

                maze.attack();

                Assertions.assertEquals(monster.getMaxHp(), monster.getHp());
            }

            /**
             * Testing the case where an entity has a negative attack, it heals
             * the victim.
             *
             * In this case, the victim don't have all its life.
             */
            @Test
            public void negative_not_max(){
                hero.setDmg((int) -monster.getMaxHp());
                monster.takeDamage(10);

                maze.attack();

                Assertions.assertEquals(monster.getMaxHp(), monster.getHp());
            }

        }

    }

}