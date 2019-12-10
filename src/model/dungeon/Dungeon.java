package model.dungeon;

import model.ZelpopGame;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.entity.ScaleStat;
import model.dungeon.mazeFactory.MazeFactory;
import model.global.Cmd;
import model.global.GlobalGrow;
import model.global.Position;
import model.state.StateFactory;
import sound.Sound;
import sprite.TextureFactory;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Dungeon implements Serializable {

    private Maze currentMaze;
    private MazeFactory mazeFactory;
    private EntityFactory entityFactory;
    private Hero hero;
    private int floor;

    private static int DEFAULT_MAZE_SIZE = 20;

    /**
     * Default constructor with random generated maze
     */
    public Dungeon() {
        this.mazeFactory = new MazeFactory();
        ScaleStat.getInstance().resetLevel();
        this.entityFactory = new EntityFactory();
        this.hero = entityFactory.generateHero();
        currentMaze = mazeFactory.getRandomMaze(DEFAULT_MAZE_SIZE, entityFactory);
        this.floor = 1;
    }

    /**
     * Default constructor with a file given in parameter
     */
    public Dungeon(InputStream inputStream){
        this.mazeFactory = new MazeFactory();
        ScaleStat.getInstance().resetLevel();
        this.entityFactory = new EntityFactory();
        this.hero = entityFactory.generateHero();

        currentMaze = mazeFactory.getMaze(inputStream, entityFactory);
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
     * Entity is ranged-attacking
     */
    public void rangedAttackHero() {
        currentMaze.rangedAttack(hero);
    }


    /**
     * Update the whole dungeon
     */
    public void updateAll(ZelpopGame game) {
        currentMaze.moveEntities();
        currentMaze.regenEntities();
        if (!hero.isAlive()) {
            game.setState(StateFactory.getGameOver(this));
        }

        // Playing the low life sound when the hero is wounded
        if(hero.getHp() < 0.25 * hero.getMaxHp()) {
            Sound.loopSound(Sound.LOW_LIFE);
        }
        // Stopping the low life sound when the is dead or is healed
        if(!hero.isAlive() || hero.getHp() >= 0.25 * hero.getMaxHp()){
            Sound.stopSound(Sound.LOW_LIFE);
        }
    }

    /**
     * Give an image for each tile and entity and the hero (used after a load)
     */
    public void setImages() {
        this.currentMaze.setImages();
        this.hero.setRessources();
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
        int centerWidth = 1000;
        int sidebarWidth = 280;

        // Draw the maze
        currentMaze.draw(img);
        // Draw the labels of the depth
        BufferedImage textImage;
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        TextManager textManager = TextureFactory.getTextManager();
        textImage = textManager.getString(floor + "", Color.WHITE);
        crayon.drawImage(
                textImage,
                centerWidth + (sidebarWidth /2 - (textImage.getWidth()/2)),
                25,
                textImage.getWidth(),
                textImage.getHeight(),
                null
        );
    }

    public void changeLevel() {
        if (currentMaze.isFinished()) {
            ScaleStat.getInstance().setLevel();
            currentMaze = mazeFactory.getRandomMaze(DEFAULT_MAZE_SIZE, entityFactory);
            hero.setPosition(new Position(
                    EntityFactory.HERO_X,
                    EntityFactory.HERO_Y,
                    EntityFactory.HERO_DIRECTION));
            floor++;
        }
    }

    public void upgradeHP() {
        if ( hero.getScore() >= hero.getStats().getMaxHpCostToUpgrade()){
            hero.increaseScore( -hero.getStats().getMaxHpCostToUpgrade());
            hero.getStats().setMaxHpCostToUpgrade(GlobalGrow.priceGrow(hero.getStats().getMaxHp(), GlobalGrow.growRateHp(), GlobalGrow.startingPriceHp()));
            hero.getStats().setMaxHp(GlobalGrow.statGrow(hero.getStats().getMaxHp()));
        }
    }

    public void upgradeRNG() {
        if ( hero.getScore() >= hero.getStats().getVitalityCostToUpgrade()){
            hero.increaseScore( -hero.getStats().getVitalityCostToUpgrade());
            hero.getStats().setVitalityCostToUpgrade(GlobalGrow.priceGrow(hero.getStats().getVitality(), GlobalGrow.growRatevit(), GlobalGrow.startingPriceVit()));
            hero.getStats().setVitality(GlobalGrow.statGrow(hero.getStats().getVitality()));
        }
    }

    public void upgradeATK() {
        if ( hero.getScore() >= hero.getStats().getDamageCostToUpgrade()){
            hero.increaseScore( -hero.getStats().getDamageCostToUpgrade());
            hero.getStats().setDamageCostToUpgrade(GlobalGrow.priceGrow(hero.getStats().getDamage(), GlobalGrow.growRateAtk(), GlobalGrow.startingPriceAtk()));
            hero.getStats().setDmg(GlobalGrow.statGrow(hero.getStats().getDamage()));
        }
    }

    public void upgradeCNT() {
        if ( hero.getScore() >= hero.getStats().getDefenceCostToUpgrade()){
            hero.increaseScore( -hero.getStats().getDefenceCostToUpgrade());
            hero.getStats().setDefenceCostToUpgrade(GlobalGrow.priceGrow(hero.getStats().getDefence(), GlobalGrow.growRateDef(), GlobalGrow.startingPriceDef()));
            hero.getStats().setDefence(GlobalGrow.statGrow(hero.getStats().getDefence()));
        }
    }
}
