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

    private static BufferedImage player;
    private static BufferedImage monster;

    private TextureFactory(){
        try {
            player = ImageIO.read(new File("ressources/players.png"));
            monster = ImageIO.read(new File("ressources/monsters.png"));
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

    public static TextureFactory getTextureFactory(){
        return textureFactory;
    }
}
