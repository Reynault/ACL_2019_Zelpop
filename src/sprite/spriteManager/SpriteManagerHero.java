package sprite.spriteManager;

import model.global.Cmd;
import model.global.GlobalSprites;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SpriteManagerHero extends SpriteManagerEntity {

    public SpriteManagerHero(BufferedImage sprite){
        super(sprite);
    }

    @Override
    public void setAttacking() {
        attacking = true;
    }

    @Override
    public void setSprite(Cmd direction){
        this.frame = (frame + 1) % 2;
        facing = direction;
    }

    @Override
    public BufferedImage getCurrentSprite(){
        BufferedImage toReturn;

        if(isAttacking()){
            int counter = 0;
            switch (facing){
                case DOWN:
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * 4 + counter * GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite() * DOWN, GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * counter,
                            GlobalSprites.get8Sprite());
                    break;
                case RIGHT:
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * 4 + counter * GlobalSprites.get8Sprite(),
                        GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * counter,
                        GlobalSprites.get8Sprite());
                    break;

                default:{
                    frame = 0;
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
            }
            attacking = false;
        }else{
            switch (facing){
                case DOWN:{
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * DOWN, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
                case UP:{
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * UP, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
                case LEFT:{
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                    tx.translate(-toReturn.getWidth(null), 0);
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    toReturn = op.filter(toReturn, null);
                    break;
                }
                case RIGHT:{
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
                default:{
                    frame = 0;
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
            }
        }
        return toReturn;
    }
}
