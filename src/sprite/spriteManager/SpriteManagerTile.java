package sprite.spriteManager;

import model.global.Cmd;

import java.awt.image.BufferedImage;

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
}
