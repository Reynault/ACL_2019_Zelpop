package model.dungeon;

import model.Pair;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.scoring.Scoring;
import model.dungeon.tile.Tile;
import model.global.Cmd;
import model.global.GlobalSprites;
import model.global.Position;
import sprite.TextureFactory;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Maze implements Serializable {

    private List<Entity> entities;
    private List<Entity> removedEntity;
    private Tile[][] tiles;
    private int width, height;
    private Hero hero;
    private Scoring scoring;

    /**
     * Default constructor
     * The maze must have at least one line and one column
     *
     * @param tiles    list of tiles
     * @param entities list of entities
     */
    public Maze(Tile[][] tiles, List<Entity> entities, Scoring scoring, EntityFactory entityFactory) {
        this.tiles = tiles;
        width = tiles[0].length;
        height = tiles.length;
        this.entities = entities;
        this.hero = entityFactory.getHero();
        this.scoring = scoring;
        removedEntity = new ArrayList<>();
    }

    /**
     * Give an image for each tile and entity (used after a load)
     */
    public void setImages() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j].setImage();
            }
        }
        for (Entity e : entities) {
            e.setImage();
        }
    }

    /**
     * Draw the maze
     * @param img image
     */
    public void draw(BufferedImage img) throws InterruptedException{
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        int frameWidth, frameHeight,
                xShift, yShift,
                currentX, currentY,
                sideBarSize, centerSize;

        centerSize = 1000;
        sideBarSize = 280;

        int unit = GlobalSprites.get8Sprite() * GlobalSprites.getScaling();
        int nbTileDisplayedY = 12;
        int nbTileDisplayedX = 20;
        Position posHero = hero.getPosition();

        crayon.setColor(Color.BLACK);

        xShift = centerSize / 2;
        yShift = img.getHeight() / 2;

        // Drawing the background
        crayon.drawImage(
                TextureFactory.getTextureFactory().getBackground(),
                xShift - (nbTileDisplayedX * unit),
                yShift - (nbTileDisplayedY * unit),
                ((nbTileDisplayedX*2+1) * unit),
                ((nbTileDisplayedY*2+1) * unit),
                null
                );

        // Drawing surrounding tiles
        for (int x = -nbTileDisplayedX; x <= nbTileDisplayedX; x++) {
            for (int y = -nbTileDisplayedY; y <= nbTileDisplayedY; y++) {
                currentX = posHero.getX() + x;
                currentY = posHero.getY() + y;
                // If the tile exists
                if (currentX >= 0 && currentY >= 0 && currentY < tiles.length && currentX < tiles[currentY].length) {
                    // Drawing the current tile
                    tiles[currentY][currentX].draw(img,
                            (x * unit) + xShift,
                            (y * unit) + yShift, GlobalSprites.getScaling());
                }
            }
        }

        // Drawing surrounding entities
        for (Entity e : entities) {
            Position pos = e.getPosition();
            // If it is nearby
            if (pos.getX() >= (posHero.getX() - nbTileDisplayedX) && pos.getX() <= (posHero.getX() + nbTileDisplayedX) &&
                    pos.getY() >= (posHero.getY() - nbTileDisplayedY) && pos.getY() <= (posHero.getY() + nbTileDisplayedY)) {
                currentX = -(posHero.getX() - pos.getX());
                currentY = -(posHero.getY() - pos.getY());
                e.draw(img, (currentX * unit) + xShift, (currentY * unit) + yShift, GlobalSprites.getScaling());
            }
        }

        // Drawing hero
        hero.draw(img, xShift, yShift, GlobalSprites.getScaling());

        //********************************************************************************************
        // Drawing the side bar
        int top_span = 75;
        int heightLabel = 20;
        Color textColor = Color.WHITE;
        Color pvColor = new Color(0x800000);


        BufferedImage textImage;
        TextManager textManager = TextureFactory.getTextManager();

        // Drawing the bar
//        crayon.fillRect(
//                xShift + nbTileDisplayed * unit,
//                yShift - nbTileDisplayed * unit,
//                sideBarSize,
//                sideBarSizeY
//        );
        // Setting the right bar
        Color sideBarColor;
        sideBarColor = new Color(0x646464);
        crayon.setBackground(sideBarColor);
        crayon.clearRect(1000, 0, sideBarSize, img.getHeight());

        crayon.setColor(Color.BLACK);
        // Drawing the mini-map
        int nbTileDisployedOnMap = 14;
        int miniUnit = GlobalSprites.get8Sprite() * GlobalSprites.getMiniScaling();

        int miniShiftx = centerSize + nbTileDisployedOnMap * miniUnit + ((sideBarSize/2) - (nbTileDisployedOnMap * miniUnit));
        int miniShifty = top_span + nbTileDisplayedY * miniUnit;

        int miniTilePositionX = miniUnit;
        int miniTilePositionY = miniUnit;

        crayon.drawImage(
                TextureFactory.getTextureFactory().getBackground(),
                miniShiftx - (nbTileDisployedOnMap * miniUnit),
                miniShifty - (nbTileDisployedOnMap * miniUnit),
                ((nbTileDisployedOnMap*2+1) * miniUnit),
                ((nbTileDisployedOnMap*2+1) * miniUnit),
                null
        );

        // Drawing surrounding tiles
        for (int x = -nbTileDisployedOnMap; x <= nbTileDisployedOnMap; x++) {
            for (int y = -nbTileDisployedOnMap; y <= nbTileDisployedOnMap; y++) {
                currentX = posHero.getX() + x;
                currentY = posHero.getY() + y;
                // If the tile exists
                if (currentX >= 0 && currentY >= 0 && currentY < tiles.length && currentX < tiles[currentY].length) {
                    // Drawing the current tile
                    tiles[currentY][currentX].draw(img,
                            (x * miniTilePositionX) + miniShiftx,
                            (y * miniTilePositionY) + miniShifty,
                            GlobalSprites.getMiniScaling());
                }
            }
        }

        // Drawing mini hero
        hero.draw(img,
                miniShiftx,
                miniShifty,
                GlobalSprites.getMiniScaling());


        // Drawing the HP label
        int sideBarElementX = xShift + nbTileDisplayedX * unit + unit;

        TextManager text = TextureFactory.getTextManager();
//        BufferedImage labelHP = text.getString(PV_STATUS, textColor);
//
//        crayon.drawImage(
//                labelHP,
//                sideBarElementX,
//                yShift - nbTileDisplayedY * unit + unit * 8 + top_span,
//                labelHP.getWidth(),
//                labelHP.getHeight(),
//                null
//        );

        // Drawing the HP bar
        crayon.setColor(pvColor);

        int barWidth = unit * 6;
        int barX = centerSize + (sideBarSize /2 - (barWidth/2));
        int barY = unit;

        crayon.drawRect(
                barX,
                yShift - nbTileDisplayedY * unit + unit * 10 + top_span,
                barWidth,
                barY
        );

        double HPratio = (double) hero.getHp() / (double) hero.getMaxHp();
        int rectWidth = (int) (unit * HPratio * 6);

        crayon.fillRect(
                centerSize + (sideBarSize /2 - (barWidth/2)),
                yShift - nbTileDisplayedY * unit + unit * 10 + top_span,
                rectWidth,
                unit
        );

        // Drawing score value
        Color labelScoreColor = new Color(0xB99828);

        BufferedImage labelScoreValue = text.getString(String.valueOf(hero.getScore()), labelScoreColor);
        crayon.drawImage(
                labelScoreValue,
                centerSize + (sideBarSize /2 - (labelScoreValue.getWidth()/2)),
                450,
                labelScoreValue.getWidth(),
                labelScoreValue.getHeight(),
                null
        );

        // Stats
        int stats[] = new int[4];
        stats[0] = (int)hero.getMaxHp();
        stats[1] = (int)hero.getVitality();
        stats[2] = (int)hero.getDmg();
        stats[3] = (int)hero.getDef();

        double cost[] = new double[4];
        cost[0] = hero.getMaxHpCostToUpgrade();
        cost[1] = hero.getVitalityCostToUpgrade();
        cost[2] = hero.getDamageCostToUpgrade();
        cost[3] = hero.getDefenceCostToUpgrade();

        for(int i = 0 ; i < stats.length ; i++){
            textImage = textManager.getString(stats[i] + "", textColor);
            crayon.drawImage(
                    textImage,
                    1080,
                    550 + i * heightLabel * 2,
                    40,
                    heightLabel,
                    null
            );

            // Cost to up a stat
            textImage = textManager.getString(cost[i] + "", Color.RED);
            crayon.drawImage(
                    textImage,
                    1200,
                    560 + i * heightLabel * 2,
                    50,
                    heightLabel/2,
                    null
            );

        }

        crayon.dispose();
    }

    /**
     * Move a entity in the maze using a direction
     *
     * @param e         entity in the maze
     * @param direction direction for the move
     */
    public void moveEntity(Entity e, Cmd direction) {
        int x, y;
        Position currentPosition, newPosition;

        // Generating a movement
        direction = e.behave(this, direction);

        if (direction != Cmd.IDLE) {

            currentPosition = e.getPosition();

            x = currentPosition.getX();
            y = currentPosition.getY();

            Pair p = getPositionByDirection(x, y, direction);

            // Checking if movement is in the maze
            if (canMove(e, p.getX(), p.getY())) {
                newPosition = new Position(p.getX(), p.getY(), direction);
            } else {
                newPosition = new Position(
                        currentPosition.getX(),
                        currentPosition.getY(),
                        direction
                );
            }

            // Setting position
            e.setPosition(newPosition);

            // Then adding tile action to the hero
            Position position = hero.getPosition();
            Tile t = tiles[position.getY()][position.getX()];
            t.action(this, hero);
        }
    }

    public Pair getPositionByDirection(int x, int y, Cmd direction) {
        switch (direction) {
            case LEFT:
                x -= 1;
                break;
            case UP:
                y -= 1;
                break;
            case DOWN:
                y += 1;
                break;
            case RIGHT:
                x += 1;
                break;
        }
        return new Pair(x, y);
    }

    /**
     * Verify if an entity can move or not
     *
     * @param entity entity who wants to move
     * @param x      position of the target tile
     * @param y      position of the target tile
     * @return true if the entity can move
     */
    public boolean canMove(Entity entity, int x, int y) {
        Tile tile;
        boolean can = false;

        // Checking if the movement is in the maze
        if (x >= 0 && x < width && y >= 0 && y < height) {
            tile = tiles[y][x];

            // Checking if the entity can go on the tile
            if (tile.canBeCrossed()) {
                can = true;
            } else {
                if (entity.canPassThrought()) {
                    can = true;
                }
            }

            // Checking there already is an entity on it
            if (getEntity(x, y) != null || (hero.getPosition().getX() == x && hero.getPosition().getY() == y)) {
                can = false;
            }
        }

        return can;
    }

    /**
     * Method that move all the entities non controlled by the player.
     */
    public void moveEntities() {
        if (entities != null && entities.size() > 0) {
            for (Entity e : removedEntity) {
                removeEntity(e);
            }
            for (Entity e : entities) {
                moveEntity(e, Cmd.IDLE);
            }
        }
    }

    /**
     * Entity is attacking
     */
    public void attack() {
        this.hero.attack(this);
    }

    /**
     * Method getEntity, it fetch an entity from a specify tile on the maze
     */
    public Entity getEntity(int x, int y) {
        Entity res = null;
        Position pos = hero.getPosition();

        if (pos.getX() == x && pos.getY() == y) {
            res = hero;
        } else {
            for (Entity entity : entities) {
                if (entity.getPosition().getY() == y && entity.getPosition().getX() == x) {
                    res = entity;
                }
            }
        }

        return res;
    }

    public boolean isFinished() {
        Position pos = hero.getPosition();
        Tile t = tiles[pos.getY()][pos.getX()];
        if (t.isStairs()) {
            hero.increaseScore(scoring.leaveMaze());
            return true;
        } else {
            return false;
        }
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public int getChestScore(Tile tile) {
        return scoring.findChest(tile);
    }

    public Hero getHero() {
        return hero;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    /**
     * Getter that give the selected tile from the maze
     * @param x the x position
     * @param y the y position
     * @return the selected tile
     */
    public Tile getTile(int x, int y) {
        Tile tile = null;
        if(x < width && x >= 0 && y < height && y >= 0){
            tile = tiles[y][x];
        }
        return tile;
    }

    /**
     * Method that destroy a targeted tile in the maze,
     * the maze is taking damages from an entity, and if its
     * destroyed, it becomes its decorated tile.
     *  @param x the x position
     * @param y the y position
     * @param damage the damages taken by the tile
     */
    public void destroy(int x, int y, double damage){
        // The target is in the maze
        if(x < width && x >= 0 && y < height && y >= 0) {
            Tile tile = tiles[y][x];
            // And is breakable
            if(tile.isBreakable()){
                // It takes damages
                tile.takeDamage(damage);
                if(tile.isDestroyed()){
                    // And if destroyed, then it becomes its ancestor
                    tiles[y][x] = tiles[y][x].getAncestor();
                }
            }
        }
    }

    /**
     * Method that hit a target from an entity. If the target is dead, then
     * it is removed from the maze.
     * @param entity The attacker
     * @param victim The victim
     * @param damage The damages taken by the victim
     */
    public void attackEntity(Entity entity, Entity victim, double damage) {
        // Damaging the victim with entity's damages
        victim.takeDamage(damage);
        if (!victim.isAlive()) {
            // If its dead, then we have to remove it and to increase score
            double bonus = scoring.killMonster(victim);
            entity.increaseScore(bonus);

            // Deleting killed entity
            removedEntity.add(victim);
        }
    }

    public void defendEntity(Entity entity, Entity victim) {
        // Damaging the entity with victim's defence
        entity.takeDamage(victim.getDef());
        if (!entity.isAlive()){
            if (victim.isHero()){
                // If its dead and the hero is defending, then we have to remove it and to increase score
                double bonus = scoring.killMonster(entity);
                entity.increaseScore(bonus);

                // Deleting killed entity
                removedEntity.add(entity);
            }
        }
    }

    public void regenEntites() {
        if (entities != null && entities.size() > 0) {
            for (Entity e : entities) {
                e.regen();
            }
        }
        hero.regen();
    }
}
