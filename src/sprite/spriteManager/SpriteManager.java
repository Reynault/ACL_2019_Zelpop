package sprite.spriteManager;

import model.global.GlobalDirection;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class SpriteManager implements Serializable {
    protected final static int UP = 2;
    protected final static int DOWN = 1;
    protected final static int LEFT = 0;
    protected final static int RIGHT = 0;

    protected int frame;
    protected GlobalDirection facing;
    protected transient BufferedImage sprite;

    public SpriteManager(BufferedImage sprite) {
        this.sprite = sprite;
        frame = 0;
        facing = GlobalDirection.LEFT;
    }

    public abstract void setSprite(GlobalDirection direction);

    public abstract BufferedImage getCurrentSprite();
}
