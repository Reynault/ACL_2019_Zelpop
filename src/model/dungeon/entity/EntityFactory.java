package model.dungeon.entity;

import model.dungeon.entity.behavior.BehaveFactory;
import model.global.Cmd;
import model.global.Position;

import java.io.Serializable;

public class EntityFactory implements Serializable {

    public static int HERO_HP = 30;
    public static int HERO_X = 0;
    public static int HERO_Y = 0;
    public static Cmd HERO_DIRECTION = Cmd.DOWN;
    public static int HERO_DAMAGE = 5;
    public static int HERO_VALUE = 50;
    public static int HERO_SCORE = 0;

    public static int MONSTER_HP = 10;
    public static int MONSTER_DAMAGE = 5;
    public static int MONSTER_VALUE = 50;
    public static int MONSTER_SCORE = 0;

    public static int GHOST_HP = 5;
    public static int GHOST_DAMAGE = 5;
    public static int GHOST_VALUE = 50;
    public static int GHOST_SCORE = 0;

    public static int GOBELIN_HP = 10;
    public static int GOBELIN_DAMAGE = 5;
    public static int GOBELIN_VALUE = 50;
    public static int GOBELIN_SCORE = 0;

    private Hero hero;

    /**
     * Generate the Hero
     */
    public Hero generateHero() {
        Stats stats = new Stats(HERO_HP, HERO_DAMAGE, 1, 1);
        return hero = new Hero(stats,
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
        Stats stats = new Stats(MONSTER_HP, MONSTER_DAMAGE, 0, 0);
        return new Monster(stats,
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
        Stats stats = new Stats(GHOST_HP, GHOST_DAMAGE, 0, 0);
        return new Ghost(stats,
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
        Stats stats = new Stats(GOBELIN_HP, GOBELIN_DAMAGE, 0, 0);
        return new Gobelin(stats,
                false,
                GOBELIN_SCORE,
                GOBELIN_VALUE,
                position,
                BehaveFactory.getGobelinBehavior());
    }
}
