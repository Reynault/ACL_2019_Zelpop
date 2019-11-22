package sprite.spriteManager;

import java.awt.image.BufferedImage;

public abstract class SpriteManagerEntity extends SpriteManager{

    protected boolean isAttacking;
    protected int attackFrame;

    public SpriteManagerEntity(BufferedImage sprite) {
        super(sprite);
        isAttacking = false;
        attackFrame = 0;
    }

    public abstract void isAttacking();
}
