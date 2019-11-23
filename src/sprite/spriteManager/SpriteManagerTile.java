package sprite.spriteManager;

import model.global.Cmd;

import javax.swing.*;
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
    public List<BufferedImage> getCurrentSprite() {
        ArrayList<BufferedImage> liste = new ArrayList<>();
        liste.add(sprite);
        return liste;
    }
}
