package model.dungeon;

import model.ZelpopGame;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.mazeFactory.MazeFactory;
import model.global.Cmd;
import model.global.Position;
import model.state.StateFactory;

import java.awt.image.BufferedImage;
import java.io.*;

public class Dungeon implements Serializable {

    private Maze currentMaze;
    private MazeFactory mazeFactory;
    private EntityFactory entityFactory;
    private Hero hero;

    private static int DEFAULT_MAZE_SIZE = 20;

    /**
     * Default constructor with random generated maze
     */
    public Dungeon() {
        this.mazeFactory = new MazeFactory();
        this.entityFactory = new EntityFactory();
        this.hero = entityFactory.generateHero();
        currentMaze = mazeFactory.getRandomMaze(DEFAULT_MAZE_SIZE, entityFactory);
    }

    /**
     * Default constructor with a file given in parameter
     */
    public Dungeon(File file) throws FileNotFoundException {
        this.mazeFactory = new MazeFactory();
        this.entityFactory = new EntityFactory();
        this.hero = entityFactory.generateHero();

        InputStream is = new FileInputStream(file);
        currentMaze = mazeFactory.getMaze(is, entityFactory);
    }


    /**
     * Move the hero with the direction
     *
     * @param direction direction for the move
     */
    public void moveHero(Cmd direction) {
        currentMaze.moveEntity(
                hero,
                direction
        );
    }


    /**
     * Entity is attacking
     */
    public void attack() {
        currentMaze.attack();
    }

    /**
     * Update the whole dungeon
     */
    public void updateAll(ZelpopGame game) {
        currentMaze.moveEntities();
        if (!hero.isAlive()) {
            game.setState(StateFactory.getGameOver(this));
        }
        currentMaze.regenEntites();
    }

    /**
     * Give an image for each tile and entity and the hero (used after a load)
     */
    public void setImages() {
        this.currentMaze.setImages();
        this.hero.setImage();
    }

    public Hero getHero(){
        return entityFactory.getHero();
    }

    /**
     * Drawn the dungeon
     *
     * @param img image
     */
    public void draw(BufferedImage img) throws InterruptedException{
        currentMaze.draw(img);
    }

    public void changeLevel() {
        if (currentMaze.isFinished()) {
            currentMaze = mazeFactory.getRandomMaze(DEFAULT_MAZE_SIZE, entityFactory);
            hero.setPosition(new Position(
                    EntityFactory.HERO_X,
                    EntityFactory.HERO_Y,
                    EntityFactory.HERO_DIRECTION));
        }
    }
}
