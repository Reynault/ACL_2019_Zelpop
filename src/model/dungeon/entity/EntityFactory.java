package model.dungeon.entity;

import model.dungeon.entity.behavior.HeroBehavior;
import model.dungeon.entity.behavior.RandomBehavior;
import model.global.GlobalDirection;
import model.global.GlobalSprites;
import model.global.Position;

public class EntityFactory {

    public static int HERO_HP = 20;
    public static int HERO_X = 0;
    public static int HERO_Y = 0;
    public static GlobalDirection HERO_DIRECTION = GlobalDirection.DOWN;

    private static int MONSTER_HP = 6;

    private Hero hero;

    private static EntityFactory ourInstance = new EntityFactory();

    public static EntityFactory getInstance() {
        return ourInstance;
    }

    private EntityFactory() {
    }

    /**
     * Generate the Hero
     */
    public Hero generateHero() {
        return hero = new Hero(HERO_HP,
                false,
                            new Position(
                                    HERO_X,
                                    HERO_Y,
                                    HERO_DIRECTION
                            ),
                    new HeroBehavior()
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
        return new Monster(6, false, position, new RandomBehavior());  // default hp
    }
}
