package sprite.spriteManager;

import java.awt.image.BufferedImage;

public abstract class SpriteManagerEntity extends SpriteManager{

    protected boolean attacking;
    protected int attackFrame;

    public SpriteManagerEntity(BufferedImage sprite) {
        super(sprite);
        attacking = false;
    }

    public abstract void setAttacking();

    public boolean isAttacking() {
        return attacking;
    }
}
