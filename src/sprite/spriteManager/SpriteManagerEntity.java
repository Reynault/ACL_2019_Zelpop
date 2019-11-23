package sprite.spriteManager;

import model.global.GlobalSprites;

import java.awt.image.BufferedImage;
import java.util.TimerTask;

public abstract class SpriteManagerEntity extends SpriteManager{

    protected boolean attacking;
    protected int attackFrame;

    public SpriteManagerEntity(BufferedImage sprite) {
        super(sprite);
        attacking = false;
    }

    public void setAttacking(){
        // Updating attack informations
        attacking = true;
        attackFrame = 0;

        // Setting up animation for later
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                attackFrame ++;
            }
        };
        setAnimation(timerTask, GlobalSprites.getAnimationDelay());
    }

    public boolean isAttacking() {
        return attacking;
    }
}
