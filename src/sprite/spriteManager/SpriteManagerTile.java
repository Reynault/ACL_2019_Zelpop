package sprite.spriteManager;

import model.global.GlobalDirection;
import sprite.spriteManager.SpriteManager;

import java.awt.image.BufferedImage;

public class SpriteManagerTile extends SpriteManager {


    public SpriteManagerTile(BufferedImage sprite) {
        super(sprite);
    }

    @Override
    public void setSprite(GlobalDirection direction) {

    }

    @Override
    public BufferedImage getCurrentSprite() {
        return sprite;
    }
}