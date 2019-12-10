package model.dungeon.entity;

import model.dungeon.entity.behavior.BehaveFactory;
import model.global.Cmd;
import model.global.Position;

import java.io.Serializable;

public class EntityFactory implements Serializable {
    public static int HERO_X = 0;
    public static int HERO_Y = 0;
    public static Cmd HERO_DIRECTION = Cmd.DOWN;
    private static int HERO_VALUE = 50;
    private static int HERO_SCORE = 0;

    private static int MONSTER_VALUE = 50;
    private static int MONSTER_SCORE = 0;

    private static int GHOST_VALUE = 50;
    private static int GHOST_SCORE = 0;

    private static int GOBELIN_VALUE = 50;
    private static int GOBELIN_SCORE = 0;

    private Hero hero;

    /**
     * Generate the Hero
     */
    public Hero generateHero() {
        return hero = new Hero(StatFactory.getHeroStats(),
                false,
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
    public Entity getRandomMonster(Position position) {
        return new Monster(StatFactory.getmonsterStats(),
                false,
                false,
                MONSTER_SCORE,
                MONSTER_VALUE,
                position,
                BehaveFactory.getSimpleBehavior());  // default hp
    }

    /**
     * Generate a classic ghost
     */
    public Entity getRandomGhost(Position position) {
        return new Ghost(StatFactory.getGhostStats(),
                true,
                false,
                GHOST_SCORE,
                GHOST_VALUE,
                position,
                BehaveFactory.getSimpleBehavior());
    }

    /**
     * Generate a classic gobelin
     */
    public Entity getRandomGobelin(Position position) {
        return new Gobelin(StatFactory.getGoblinStats(),
                false,
                false,
                GOBELIN_SCORE,
                GOBELIN_VALUE,
                position,
                BehaveFactory.getGobelinBehavior());
    }

    /**
     * Generate a classic gobelin
     */
    public Entity getProjectile(Entity owner, Position position) {
        Stats stats = new Stats(1, owner.getDmg(), 0, 0);
        return new Projectile(stats,
                false,
                true,
                0,
                0,
                position,
                BehaveFactory.getProjectileBehavior(),
                owner);
    }
}
