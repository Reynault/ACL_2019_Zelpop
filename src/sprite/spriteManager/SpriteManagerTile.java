package sprite.spriteManager;

import model.global.Cmd;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteManagerTile extends SpriteManager {


    public SpriteManagerTile(BufferedImage sprite) {
        super(sprite);
    }

    @Override
    public void setSprite(Cmd direction) {

    }

    @Override
    public BufferedImage getCurrentSprite() {
        return sprite;
    }

    @Override
    public void draw(BufferedImage img, int x, int y, int scale) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        BufferedImage image = getCurrentSprite();

        crayon.drawImage(image,
                x,
                y,
                image.getWidth() * scale,
                image.getHeight() * scale,
                null);
        crayon.dispose();
    }
}
