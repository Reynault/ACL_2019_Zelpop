package model.dungeon.entity;

import model.dungeon.entity.behavior.BehaveFactory;
import model.global.Cmd;
import model.global.Position;

import java.io.Serializable;

public class EntityFactory implements Serializable {

    private static Stats STAT_HERO = new Stats(100, 10, 1, 2);
    public static int HERO_X = 0;
    public static int HERO_Y = 0;
    public static Cmd HERO_DIRECTION = Cmd.DOWN;
    private static int HERO_VALUE = 50;
    private static int HERO_SCORE = 0;

    private static Stats STAT_MONSTER = new Stats(25, 5, 1, 1);
    private static int MONSTER_VALUE = 50;
    private static int MONSTER_SCORE = 0;

    private static Stats STAT_GHOST = new Stats(15, 25, 0, 10);
    private static int GHOST_VALUE = 50;
    private static int GHOST_SCORE = 0;

    private static Stats STAT_GOBLIN = new Stats(75, 5, 3, 1);
    private static int GOBELIN_VALUE = 50;
    private static int GOBELIN_SCORE = 0;

    private Hero hero;

    /**
     * Generate the Hero
     */
    public Hero generateHero() {
        return hero = new Hero(STAT_HERO,
                false,
                            HERO_SCORE,
                            HERO_VALUE,
                            new Position(
                                    HERO_X,
                                    HERO_Y,
                                    HERO_DIRECTION
                            ),
                    BehaveFactory.getHeroBehavior()
                );  // default hp
    }

    /**
     * Getter of the hero in the dungeon
     * @return
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Generate a classic monster
     */
    public Monster getRandomMonster(Position position) {
        return new Monster(STAT_MONSTER,
                false,
                MONSTER_SCORE,
                MONSTER_VALUE,
                position,
                BehaveFactory.getSimpleBehavior());  // default hp
    }

    /**
     * Generate a classic ghost
     */
    public Ghost getRandomGhost(Position position) {
        return new Ghost(STAT_GHOST,
                true,
                GHOST_SCORE,
                GHOST_VALUE,
                position,
                BehaveFactory.getSimpleBehavior());
    }

    /**
     * Generate a classic gobelin
     */
    public Gobelin getRandomGobelin(Position position) {
        return new Gobelin(STAT_GOBLIN,
                false,
                GOBELIN_SCORE,
                GOBELIN_VALUE,
                position,
                BehaveFactory.getGobelinBehavior());
    }
}
