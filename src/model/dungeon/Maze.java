package model.dungeon;

import javafx.geometry.Pos;
import model.dungeon.entity.Entity;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.tile.Tile;
import model.global.GlobalDirection;
import model.global.GlobalSprites;
import model.global.Position;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Maze {

    private List<Entity> entities;
    private Tile[][] tiles;
    private int width, height;
    private Hero hero;

    /**
     * Default constructor
     * <p>
     * The maze must have at least one line and one column
     *
     * @param tiles    list of tiles
     * @param entities list of entities
     */
    public Maze(Tile[][] tiles, List<Entity> entities) {
        this.tiles = tiles;
        width = tiles[0].length;
        height = tiles.length;
        this.entities = entities;
        this.hero = EntityFactory.getInstance().getHero();
    }

    /**
     * Draw the maze
     *
     * @param img
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

        sideBarSizeX = 8*(GlobalSprites.getScaling() * GlobalSprites.get8Sprite());
        sideBarSizeY = (nbTileDisplayed*2+1) * unit;

        xShift = width / 2 - sideBarSizeX/2;
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
        for(Entity e: entities) {
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
        Color pvColor = new Color(0x890502);

        crayon.setColor(sideBarColor);

        // Drawing the bar
        crayon.fillRect(
                xShift + nbTileDisplayed * unit,
                yShift - nbTileDisplayed * unit,
                sideBarSizeX,
                sideBarSizeY
        );


        // Drawing the mini-map
        int miniShiftx = xShift + nbTileDisplayed * unit + unit;
        int miniShifty = yShift - nbTileDisplayed * unit + unit;

        int miniTilePositionX = (GlobalSprites.get8Sprite() * GlobalSprites.getMiniScaling());
        int miniTilePositionY = (GlobalSprites.get8Sprite() * GlobalSprites.getMiniScaling());

        // Drawing surrounding tiles
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                // Drawing the current tile
                tiles[y][x].draw(img,
                        (x * miniTilePositionX) + miniShiftx,
                        (y * miniTilePositionY) + miniShifty, GlobalSprites.getMiniScaling());
            }
        }

        // Drawing mini hero
        hero.draw(img,
                miniShiftx + (posHero.getX() * GlobalSprites.get8Sprite() * GlobalSprites.getMiniScaling()),
                miniShifty + (posHero.getY() * GlobalSprites.get8Sprite() * GlobalSprites.getMiniScaling()),
                GlobalSprites.getMiniScaling());


        // Drawing the HP label
        TextManager text = new TextManager();
        BufferedImage label = text.getString("HP", textColor);

        int sideBarElementX = xShift + nbTileDisplayed * unit + unit;
        
        crayon.drawImage(
                label,
                sideBarElementX,
                yShift - nbTileDisplayed * unit + unit * 8,
                label.getWidth(),
                label.getHeight(),
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

        int HPratio = hero.getMaxHp() / hero.getHp();

        crayon.fillRect(
                sideBarElementX,
                yShift - nbTileDisplayed * unit + unit * 10,
                unit *  HPratio * 6,
                unit
        );

        crayon.dispose();

        // FORMER VERSION OF DRAW
        /*
        int x = 0;
        int y = 0;
        for (Tile[] row :
                tiles) {
            for (Tile tile :
                    row) {
                tile.draw(img, x, y);
                x++;
            }
            y++;
            x = 0;
        }
        if(entities != null && entities.size() > 0){
            for (Entity e :
                    entities) {
                e.draw(img);
            }
        }
        this.hero.draw(img);
        */

    }

    /**
     * Move a entity in the maze using a direction
     *
     * @param e         entity in the maze
     * @param direction direction for the move
     */
    public void moveEntity(Entity e, GlobalDirection direction) {
        int x, y;
        Position currentPosition, newPosition;

        // Generating a movement
        direction = e.behave(direction);

        if (direction != GlobalDirection.IDLE) {

            currentPosition = e.getPosition();

            x = currentPosition.getX();
            y = currentPosition.getY();

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

            // Checking if movement is in the maze
            if (canMove(e, x, y)) {
                newPosition = new Position(x, y, direction);
            } else {
                newPosition = new Position(
                        currentPosition.getX(),
                        currentPosition.getY(),
                        direction
                );
            }

            e.setPosition(newPosition);
        }
    }

    /**
     * Verify if an entity can move or not
     *
     * @param entity entity who wants to move
     * @param x      position of the target tile
     * @param y      position of the target tile
     * @return true if the entity can move
     */
    private boolean canMove(Entity entity, int x, int y) {
        Tile tile;
        boolean can = false;

        if (x >= 0 && x < width && y >= 0 && y < height) {
            tile = tiles[y][x];
            if (tile.canBeCrossed()) {
                can = true;
            } else {
                if (entity.canPassThrought()) {
                    can = true;
                }
            }
        }

        return can;
    }

    /**
     * Method that move all the entities non controlled by the player.
     */
    public void moveEntities() {
        if (entities != null && entities.size() > 0) {
            for (Entity e : entities) {
                moveEntity(e, GlobalDirection.IDLE);
            }
        }
    }

    /**
     * Method getEntity, it fetch an entity from a specify tile on the maze
     */
    public Entity getEntity(int x, int y){
        Entity res = null;
        for(Entity entity: entities){
            if(entity.getPosition().getY() == y && entity.getPosition().getX() == x){
                res = entity;
            }
        }
        return res;
    }
}
