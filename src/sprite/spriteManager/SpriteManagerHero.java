package sprite.spriteManager;

import model.global.Cmd;
import model.global.GlobalSprites;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SpriteManagerHero extends SpriteManagerEntity {

    public SpriteManagerHero(BufferedImage sprite) {
        super(sprite);
    }

    @Override
    public void setAttacking() {
        // Updating attack informations
        attacking = true;
        attackFrame = 0;

        // Setting up animation for later
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                attackFrame ++;
                System.out.println("OF COURSE");
            }
        };
        setAnimation(timerTask, GlobalSprites.getAnimationDelay());
    }

    @Override
    public void setSprite(Cmd direction) {
        this.frame = (frame + 1) % 2;
        facing = direction;
    }

    @Override
    public BufferedImage getCurrentSprite() {
        BufferedImage toReturn;

        if (isAttacking()) {
            switch (facing) {
                case DOWN:
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * 4 + attackFrame * GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite() * DOWN, GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * attackFrame,
                            GlobalSprites.get8Sprite());
                    break;
                case RIGHT:
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * 4 + attackFrame * GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * attackFrame,
                            GlobalSprites.get8Sprite());
                    break;
                case UP:
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * 4 + attackFrame * GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite() * UP, GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * attackFrame,
                            GlobalSprites.get8Sprite());
                    break;
                case LEFT:
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * 4 + attackFrame * GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * attackFrame,
                            GlobalSprites.get8Sprite());
                    System.out.println(GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * attackFrame);
                    break;

                default: {
                    frame = 0;
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
            }
            if(attackFrame == 1){
                attacking = false;
            }
        } else {
            switch (facing) {
                case DOWN: {
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * DOWN, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
                case UP: {
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * UP, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
                case LEFT: {
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
                case RIGHT: {
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
                default: {
                    frame = 0;
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
            }
        }

        if (facing == Cmd.LEFT) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-(GlobalSprites.get8Sprite()), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            toReturn = op.filter(toReturn, null);
        }

        return toReturn;
    }
}
