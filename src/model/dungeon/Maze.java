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
    private EntityFactory entityFactory;

    private static String PV_STATUS = "HP";
    private static String SCORE_STATUS = "SCORE";

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
     *
     * @param img image
     */
    public void draw(BufferedImage img) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        int width, height,
                xShift, yShift,
                currentX, currentY,
                tilePositionX, tilePositionY,
                sideBarSizeX, sideBarSizeY;

        int nbTileDisplayed = 9;
        Position posHero = hero.getPosition();
        int unit = GlobalSprites.get8Sprite() * GlobalSprites.getScaling();

        crayon.setColor(Color.BLACK);

        width = img.getWidth();
        height = img.getHeight();

        sideBarSizeX = 8 * (GlobalSprites.getScaling() * GlobalSprites.get8Sprite());
        sideBarSizeY = (nbTileDisplayed * 2 + 1) * unit;

        xShift = width / 2 - sideBarSizeX / 2;
        yShift = height / 2;

        tilePositionX = (GlobalSprites.getScaling() * GlobalSprites.get8Sprite());
        tilePositionY = (GlobalSprites.getScaling() * GlobalSprites.get8Sprite());

        // Drawing surrounding tiles
        for (int x = -nbTileDisplayed; x <= nbTileDisplayed; x++) {
            for (int y = -nbTileDisplayed; y <= nbTileDisplayed; y++) {
                currentX = posHero.getX() + x;
                currentY = posHero.getY() + y;
                // If the tile exists
                if (currentX >= 0 && currentY >= 0 && currentY < tiles.length && currentX < tiles[currentY].length) {
                    // Drawing the current tile
                    tiles[currentY][currentX].draw(img,
                            (x * tilePositionX) + xShift,
                            (y * tilePositionY) + yShift, GlobalSprites.getScaling());
                } else {
                    // Otherwise, the void
                    crayon.fillRect(
                            (x * tilePositionX) + xShift,
                            (y * tilePositionY) + yShift,
                            unit,
                            unit
                    );
                }
            }
        }

        // Drawing surrounding entities
        for (Entity e : entities) {
            Position pos = e.getPosition();
            // If it is nearby
            if (pos.getX() >= (posHero.getX() - nbTileDisplayed) && pos.getX() <= (posHero.getX() + nbTileDisplayed) &&
                    pos.getY() >= (posHero.getY() - nbTileDisplayed) && pos.getY() <= (posHero.getY() + nbTileDisplayed)) {
                currentX = -(posHero.getX() - pos.getX());
                currentY = -(posHero.getY() - pos.getY());
                e.draw(img, (currentX * tilePositionX) + xShift, (currentY * tilePositionY) + yShift, GlobalSprites.getScaling());
            }
        }

        // Drawing hero at the center of the screen
        hero.draw(img, xShift, yShift, GlobalSprites.getScaling());

        // Drawing the side bar which inform the player of with things like the hero's HP
        Color sideBarColor = new Color(
                0x2E2E2E
        );
        Color textColor = Color.WHITE;
        Color pvColor = new Color(0x800000);

        crayon.setColor(sideBarColor);

        // Drawing the bar
        crayon.fillRect(
                xShift + nbTileDisplayed * unit,
                yShift - nbTileDisplayed * unit,
                sideBarSizeX,
                sideBarSizeY
        );


        crayon.setColor(Color.BLACK);

        // Drawing the mini-map
        int nbTileDisplyedOnMap = 14;
        int miniUnit = GlobalSprites.get8Sprite() * GlobalSprites.getMiniScaling();

        int miniShiftx = xShift + nbTileDisplayed * unit + unit * 4;
        int miniShifty = yShift - nbTileDisplayed * unit + unit * 4;

        int miniTilePositionX = miniUnit;
        int miniTilePositionY = miniUnit;

        // Drawing surrounding tiles
        for (int x = -nbTileDisplyedOnMap; x <= nbTileDisplyedOnMap; x++) {
            for (int y = -nbTileDisplyedOnMap; y <= nbTileDisplyedOnMap; y++) {
                currentX = posHero.getX() + x;
                currentY = posHero.getY() + y;
                // If the tile exists
                if (currentX >= 0 && currentY >= 0 && currentY < tiles.length && currentX < tiles[currentY].length) {
                    // Drawing the current tile
                    tiles[currentY][currentX].draw(img,
                            (x * miniTilePositionX) + miniShiftx,
                            (y * miniTilePositionY) + miniShifty,
                            GlobalSprites.getMiniScaling());
                } else {
                    // Otherwise, the void
                    crayon.fillRect(
                            (x * miniTilePositionX) + miniShiftx,
                            (y * miniTilePositionY) + miniShifty,
                            miniUnit,
                            miniUnit
                    );
                }
            }
        }

        // Drawing mini hero
        hero.draw(img,
                miniShiftx,
                miniShifty,
                GlobalSprites.getMiniScaling());


        // Drawing the HP label
        TextManager text = TextureFactory.getTextManager();
        BufferedImage labelHP = text.getString(PV_STATUS, textColor);

        int sideBarElementX = xShift + nbTileDisplayed * unit + unit;

        crayon.drawImage(
                labelHP,
                sideBarElementX,
                yShift - nbTileDisplayed * unit + unit * 8,
                labelHP.getWidth(),
                labelHP.getHeight(),
                null
        );

        // Drawing the HP bar
        crayon.setColor(pvColor);

        crayon.drawRect(
                sideBarElementX,
                yShift - nbTileDisplayed * unit + unit * 10,
                unit * 6,
                unit
        );

        double HPratio = (double) hero.getHp() / (double) hero.getMaxHp();

        crayon.fillRect(
                sideBarElementX,
                yShift - nbTileDisplayed * unit + unit * 10,
                (int) (unit * HPratio * 6),
                unit
        );

        // Drawing score indicator
        BufferedImage labelScore = text.getString(SCORE_STATUS, textColor);

        // Drawing label
        crayon.drawImage(
                labelScore,
                sideBarElementX,
                yShift + (labelScore.getHeight() * 3),
                labelScore.getWidth(),
                labelScore.getHeight(),
                null
        );

        // Drawing score value
        Color labelScoreColor = new Color(0xB99828);

        BufferedImage labelScoreValue = text.getString(String.valueOf(hero.getScore()), labelScoreColor);

        crayon.drawImage(
                labelScoreValue,
                sideBarElementX,
                yShift + (labelScoreValue.getHeight() * 5),
                labelScoreValue.getWidth(),
                labelScoreValue.getHeight(),
                null
        );

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
     *
     * @param x the x position
     * @param y the y position
     * @param damage the damages taken by the tile
     */
    public void destroy(int x, int y, int damage){
        // The target is in the maze
        if(x < width && x >= 0 && y < height && y >= 0) {
            Tile tile = tiles[x][y];
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
    public void attackEntity(Entity entity, Entity victim, int damage) {
        // Damaging the victim with entity's damages
        victim.takeDamage(damage);
        if (!victim.isAlive()) {
            // If its dead, then we have to remove it and to increase score
            int bonus = scoring.killMonster(entity);
            entity.increaseScore(bonus);

            // Deleting killed entity
            removedEntity.add(entity);
        }
    }
}
