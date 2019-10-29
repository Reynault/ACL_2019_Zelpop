package sprite;

import jdk.nashorn.internal.objects.Global;
import model.global.GlobalDirection;
import model.global.GlobalSprites;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class SpriteManager {
    protected final static int UP = 2;
    protected final static int DOWN = 1;
    protected final static int LEFT = 0;
    protected final static int RIGHT = 0;

    protected int frame;
    protected GlobalDirection facing;
    protected BufferedImage sprite;

    public SpriteManager(BufferedImage sprite){
        this.sprite = sprite;
        frame = 0;
        facing = GlobalDirection.LEFT;
    }

    public abstract void setSprite(GlobalDirection direction);

    public abstract BufferedImage getCurrentSprite();
}
