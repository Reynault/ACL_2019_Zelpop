package sprite.spriteManager;

import java.awt.image.BufferedImage;

public class SpriteManagerProjectile extends SpriteManagerEntity {
    public SpriteManagerProjectile(BufferedImage sprite) {
        super(sprite);
    }

    @Override
    public BufferedImage getCurrentSprite() {
        return sprite;
    }
}
