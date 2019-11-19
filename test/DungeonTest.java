import model.dungeon.Dungeon;
import model.dungeon.entity.Hero;
import model.global.Cmd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DungeonTest {

    Dungeon dungeon;
    Hero hero;

    @BeforeEach
    void initialize() {
        try {
            // Create a dungeon
            dungeon = new Dungeon(new File("res/level/test.txt"));
            // default hero with idle
            hero = dungeon.getHero();
        } catch (FileNotFoundException e) {
            System.out.println(e.getCause());
        }
    }

    @Test
    void moveHero_different_direction() {
        dungeon.moveHero(Cmd.UP);
        assertEquals(Cmd.UP, hero.getPosition().getCmd());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_move_right() {
        dungeon.moveHero(Cmd.RIGHT);
        dungeon.moveHero(Cmd.RIGHT);
        assertEquals(Cmd.RIGHT, hero.getPosition().getCmd());
        assertEquals(1, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_move_down() {
        // Once cause already down
        dungeon.moveHero(Cmd.DOWN);
        assertEquals(Cmd.DOWN, hero.getPosition().getCmd());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(1, hero.getPosition().getY());
    }

    @Test
    void moveHero_move_up() {
        dungeon.moveHero(Cmd.DOWN);
        dungeon.moveHero(Cmd.DOWN);
        // Change the direction
        dungeon.moveHero(Cmd.UP);
        // Move the player
        dungeon.moveHero(Cmd.UP);
        assertEquals(Cmd.UP, hero.getPosition().getCmd());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(1, hero.getPosition().getY());
    }


    @Test
    void moveHero_move_left() {
        dungeon.moveHero(Cmd.RIGHT);
        dungeon.moveHero(Cmd.RIGHT);
        dungeon.moveHero(Cmd.RIGHT);
        // Change the direction
        dungeon.moveHero(Cmd.LEFT);
        // Move the player
        dungeon.moveHero(Cmd.LEFT);
        assertEquals(Cmd.LEFT, hero.getPosition().getCmd());
        assertEquals(1, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_out_of_bound() {
        dungeon.moveHero(Cmd.UP);
        dungeon.moveHero(Cmd.UP);
        assertEquals(Cmd.UP, hero.getPosition().getCmd());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_change_direction() {
        dungeon.moveHero(Cmd.LEFT);
        dungeon.moveHero(Cmd.UP);
        assertEquals(Cmd.UP, hero.getPosition().getCmd());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_change_direction_after_movement() {
        dungeon.moveHero(Cmd.RIGHT);
        dungeon.moveHero(Cmd.RIGHT);
        dungeon.moveHero(Cmd.UP);
        assertEquals(Cmd.UP, hero.getPosition().getCmd());
        assertEquals(1, hero.getPosition().getX());
        assertEquals(0, hero.getPosition().getY());
    }

    @Test
    void moveHero_moving_into_wall() {
        dungeon.moveHero(Cmd.DOWN);
        dungeon.moveHero(Cmd.RIGHT);
        dungeon.moveHero(Cmd.RIGHT);
        assertEquals(Cmd.RIGHT, hero.getPosition().getCmd());
        assertEquals(0, hero.getPosition().getX());
        assertEquals(1, hero.getPosition().getY());
    }

    @Test
    void moveHero_moving_after_wall() {
        dungeon.moveHero(Cmd.DOWN);
        dungeon.moveHero(Cmd.DOWN);
        dungeon.moveHero(Cmd.RIGHT);
        dungeon.moveHero(Cmd.RIGHT);
        assertEquals(Cmd.RIGHT, hero.getPosition().getCmd());
        assertEquals(1, hero.getPosition().getX());
        assertEquals(2, hero.getPosition().getY());
    }
}