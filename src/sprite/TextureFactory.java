package sprite;

import model.global.GlobalSprites;
import sprite.spriteManager.TextManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class TextureFactory {
    private static TextureFactory textureFactory = new TextureFactory();

    private static int HERO_HEIGHT = GlobalSprites.get8Sprite() * 3;
    private static int HERO_LENGTH = GlobalSprites.get8Sprite() * 7;

    private static int MONSTER_HEIGHT = GlobalSprites.get8Sprite();
    private static int MONSTER_LENGTH = GlobalSprites.get8Sprite() * 7;

    private static int TILE_HEIGHT = GlobalSprites.get8Sprite();
    private static int TILE_LENGTH = GlobalSprites.get8Sprite();

    private static BufferedImage player;
    private static BufferedImage monster;
    private static BufferedImage ghost;
    private static BufferedImage gobelin;
    private static BufferedImage tiles;
    private static BufferedImage chest;
    private static BufferedImage text;
    private static BufferedImage background;

    private static TextManager textManager = new TextManager();

    private TextureFactory(){
        try {
            //getting image path
            InputStream inputStream
                    = TextureFactory.class.getClassLoader().getResourceAsStream("sprites/players.png");
            player = ImageIO.read(inputStream);

            inputStream
                    = TextureFactory.class.getClassLoader().getResourceAsStream("sprites/monsters.png");
            monster = ImageIO.read(inputStream);

            inputStream
                    = TextureFactory.class.getClassLoader().getResourceAsStream("sprites/tiles.png");
            tiles = ImageIO.read(inputStream);

            inputStream
                    = TextureFactory.class.getClassLoader().getResourceAsStream("sprites/chest.png");
            chest = ImageIO.read(inputStream);

            inputStream
                    = TextureFactory.class.getClassLoader().getResourceAsStream("sprites/text.png");
            text = ImageIO.read(inputStream);


            inputStream
                    = TextureFactory.class.getClassLoader().getResourceAsStream("sprites/background.png");
            background = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getBackground(){
        return background;
    }

    public BufferedImage getHero(){
        BufferedImage sprite = player.getSubimage(0, 5*HERO_HEIGHT, HERO_LENGTH, HERO_HEIGHT);
        return sprite;
    }

    public BufferedImage getMonster(){
        BufferedImage sprite = monster.getSubimage(0, 5*MONSTER_HEIGHT, MONSTER_LENGTH, MONSTER_HEIGHT);
        return sprite;
    }

    public BufferedImage getTiles(){
        BufferedImage sprite = tiles.getSubimage(4 * TILE_LENGTH, 2 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }

    public BufferedImage getWalls(){
        BufferedImage sprite = tiles.getSubimage(0 * TILE_LENGTH, 0 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }

    public BufferedImage getTraps(){
        BufferedImage sprite = tiles.getSubimage(13 * TILE_LENGTH, 2 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }

    public BufferedImage getFilledTreasure(){
        BufferedImage sprite = chest.getSubimage(0 * TILE_LENGTH, 0 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }

    public BufferedImage getEmptyTreasure(){
        BufferedImage sprite = chest.getSubimage(1 * TILE_LENGTH, 0 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }

    public BufferedImage getStairs(){
        BufferedImage sprite = tiles.getSubimage(8 * TILE_LENGTH, 2 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }

    public BufferedImage getBreakableWall(){
        BufferedImage sprite = tiles.getSubimage(3 * TILE_LENGTH, 0 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }

    public BufferedImage getGhost() {
        BufferedImage sprite = monster.getSubimage(0, 15*MONSTER_HEIGHT, MONSTER_LENGTH, MONSTER_HEIGHT);
        return sprite;
    }

    public BufferedImage getGobelin() {
        BufferedImage sprite = monster.getSubimage(0, 12*MONSTER_HEIGHT, MONSTER_LENGTH, MONSTER_HEIGHT);
        return sprite;
    }

    public BufferedImage getText(){
        return text;
    }

    public static TextureFactory getTextureFactory(){
        return textureFactory;
    }

    public static TextManager getTextManager() {
        return textManager;
    }
}
