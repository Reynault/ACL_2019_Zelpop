import model.dungeon.Dungeon;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.global.GlobalDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DungeonTest {

    Dungeon dungeon;
    Hero hero;

    @BeforeEach
    void initialize(){
        // Create a dungeon
        dungeon = new Dungeon("res/level/test.txt");
        // default hero with idle
        hero = EntityFactory.getInstance().getHero();
    }

    @Test
    void moveHero_different_direction() {
        dungeon.moveHero(GlobalDirection.UP);
        assertEquals(GlobalDirection.UP, hero.getPosition().getGlobalDirection());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_move_right() {
        dungeon.moveHero(GlobalDirection.RIGHT);
        dungeon.moveHero(GlobalDirection.RIGHT);
        assertEquals(GlobalDirection.RIGHT, hero.getPosition().getGlobalDirection());
        assertEquals(1, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_move_down() {
        // Once cause already down
        dungeon.moveHero(GlobalDirection.DOWN);
        assertEquals(GlobalDirection.DOWN, hero.getPosition().getGlobalDirection());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(1, hero.getPosition().getY());
    }

    @Test
    void moveHero_move_up() {
        dungeon.moveHero(GlobalDirection.DOWN);
        dungeon.moveHero(GlobalDirection.DOWN);
        // Change the direction
        dungeon.moveHero(GlobalDirection.UP);
        // Move the player
        dungeon.moveHero(GlobalDirection.UP);
        assertEquals(GlobalDirection.UP, hero.getPosition().getGlobalDirection());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(1, hero.getPosition().getY());
    }


    @Test
    void moveHero_move_left() {
        dungeon.moveHero(GlobalDirection.RIGHT);
        dungeon.moveHero(GlobalDirection.RIGHT);
        dungeon.moveHero(GlobalDirection.RIGHT);
        // Change the direction
        dungeon.moveHero(GlobalDirection.LEFT);
        // Move the player
        dungeon.moveHero(GlobalDirection.LEFT);
        assertEquals(GlobalDirection.LEFT, hero.getPosition().getGlobalDirection());
        assertEquals(1, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_out_of_bound() {
        dungeon.moveHero(GlobalDirection.UP);
        dungeon.moveHero(GlobalDirection.UP);
        assertEquals(GlobalDirection.UP, hero.getPosition().getGlobalDirection());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_change_direction() {
        dungeon.moveHero(GlobalDirection.LEFT);
        dungeon.moveHero(GlobalDirection.UP);
        assertEquals(GlobalDirection.UP, hero.getPosition().getGlobalDirection());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_change_direction_after_movement() {
        dungeon.moveHero(GlobalDirection.RIGHT);
        dungeon.moveHero(GlobalDirection.RIGHT);
        dungeon.moveHero(GlobalDirection.UP);
        assertEquals(GlobalDirection.UP, hero.getPosition().getGlobalDirection());
        assertEquals(1, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_moving_into_wall() {
        dungeon.moveHero(GlobalDirection.DOWN);
        dungeon.moveHero(GlobalDirection.RIGHT);
        dungeon.moveHero(GlobalDirection.RIGHT);
        assertEquals(GlobalDirection.RIGHT, hero.getPosition().getGlobalDirection());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(1, hero.getPosition().getY());
    }

    @Test
    void moveHero_moving_after_wall() {
        dungeon.moveHero(GlobalDirection.DOWN);
        dungeon.moveHero(GlobalDirection.DOWN);
        dungeon.moveHero(GlobalDirection.RIGHT);
        dungeon.moveHero(GlobalDirection.RIGHT);
        assertEquals(GlobalDirection.RIGHT, hero.getPosition().getGlobalDirection());
        assertEquals(1, hero.getPosition().getX());
        assertEquals(2, hero.getPosition().getY());
    }
}