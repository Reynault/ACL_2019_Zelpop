package sprite;

import model.global.GlobalSprites;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private static BufferedImage tiles;
    private static BufferedImage chest;

    private TextureFactory(){
        try {
            player = ImageIO.read(new File("res/sprites/players.png"));
            monster = ImageIO.read(new File("res/sprites/monsters.png"));
            tiles = ImageIO.read(new File("res/sprites/tiles.png"));
            chest = ImageIO.read(new File("res/sprites/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getHero(){
        Random random = new Random();
        BufferedImage sprite = player.getSubimage(0, HERO_HEIGHT * random.nextInt(14), HERO_LENGTH, HERO_HEIGHT);
        return sprite;
    }

    public BufferedImage getMonster(){
        Random random = new Random();
        BufferedImage sprite = monster.getSubimage(0, MONSTER_HEIGHT * random.nextInt(24), MONSTER_LENGTH, MONSTER_HEIGHT);
        return sprite;
    }

    public BufferedImage getTiles(){
        BufferedImage sprite = tiles.getSubimage(4 * TILE_LENGTH, TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }
    public BufferedImage getWalls(){
        BufferedImage sprite = tiles.getSubimage(0 * TILE_LENGTH, 0 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }
    public BufferedImage getTraps(){
        BufferedImage sprite = tiles.getSubimage(13 * TILE_LENGTH, 1 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }
    public BufferedImage getTreasure(){
        BufferedImage sprite = chest.getSubimage(2 * TILE_LENGTH, 0 * TILE_HEIGHT , TILE_LENGTH, TILE_HEIGHT);
        return sprite;
    }

    public static TextureFactory getTextureFactory(){
        return textureFactory;
    }
}
