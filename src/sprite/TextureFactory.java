package sprite;

import model.global.GlobalSprites;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureFactory {
    private static TextureFactory textureFactory = new TextureFactory();

    private static int HERO_HEIGHT = GlobalSprites.get8Sprite() * 3;
    private static int HERO_LENGTH = GlobalSprites.get8Sprite() * 7;

    private static BufferedImage player;

    private TextureFactory(){
        try {
            player = ImageIO.read(new File("ressources/players.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getHero(){
        BufferedImage hero = player.getSubimage(0, 0, HERO_LENGTH, HERO_HEIGHT);
        return hero;
    }

    public static TextureFactory getTextureFactory(){
        return textureFactory;
    }
}
