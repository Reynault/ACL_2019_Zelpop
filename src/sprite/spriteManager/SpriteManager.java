package sprite.spriteManager;

import model.global.Cmd;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class SpriteManager implements Serializable {
    protected final static int UP = 2;
    protected final static int DOWN = 1;
    protected final static int LEFT = 0;
    protected final static int RIGHT = 0;

    int frame;
    Cmd facing;
    protected transient BufferedImage sprite;

    public SpriteManager(BufferedImage sprite) {
        this.sprite = sprite;
        frame = 0;
        facing = Cmd.DOWN;
    }

    public abstract void setSprite(Cmd direction);

    public abstract BufferedImage getCurrentSprite();

    void setAnimation(TimerTask task, int delay){
        Timer timer = new Timer();
        timer.schedule(task, delay);
    }

    public abstract void draw(BufferedImage img, int x, int y, int scale);
}
