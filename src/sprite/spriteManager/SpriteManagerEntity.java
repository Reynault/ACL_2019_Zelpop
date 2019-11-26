package sprite.spriteManager;

import model.global.Cmd;
import model.global.GlobalSprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

public abstract class SpriteManagerEntity extends SpriteManager{

    protected boolean attacking;
    protected int attackFrame;

    public SpriteManagerEntity(BufferedImage sprite) {
        super(sprite);
        attacking = false;
    }

    @Override
    public void setSprite(Cmd direction) {
        this.frame = (frame + 1) % 2;
        facing = direction;
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
        timerTask = new TimerTask() {
            @Override
            public void run() {
                attacking = false;
            }
        };
        setAnimation(timerTask, GlobalSprites.getAnimationDelay()*3);
    }

    public boolean isAttacking() {
        return attacking;
    }

    @Override
    public void draw(BufferedImage img, int x, int y, int scale) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        BufferedImage image = getCurrentSprite();

        int posx = x;
        if(image.getWidth() == GlobalSprites.get16Sprite() && facing == Cmd.LEFT){
            posx = x - (image.getWidth()/2*scale);
        }

        crayon.drawImage(image,
                posx,
                y,
                image.getWidth() * scale,
                image.getHeight() * scale,
                null);
        crayon.dispose();
    }
}
