package model.dungeon.entity;

import model.dungeon.entity.behavior.HeroBehavior;
import model.dungeon.entity.behavior.RandomBehavior;
import model.global.GlobalDirection;
import model.global.Position;

public class EntityFactory {

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
        return hero = new Hero(20,
                false,
                            new Position(
                                    0,
                                    0,
                                    GlobalDirection.DOWN
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
        return new Monster(2, false, position, new RandomBehavior());  // default hp
    }
}
