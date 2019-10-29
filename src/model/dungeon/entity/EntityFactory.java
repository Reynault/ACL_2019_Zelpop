package model.dungeon.entity;

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
        return hero = new Hero(20);  // default hp
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
    public Monster generateMonster() {
        return new Monster(2);  // default hp
    }
}
