package sprite.spriteManager;

import model.global.Cmd;
import model.global.GlobalSprites;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class SpriteManagerMonster extends SpriteManagerEntity {

    public SpriteManagerMonster(BufferedImage sprite) {
        super(sprite);
    }

    @Override
    public void setAttacking() {

    }

    @Override
    public void setSprite(Cmd direction) {
        this.frame = (frame + 1) % 2;
        facing = direction;
    }

    @Override
    public BufferedImage getCurrentSprite() {
        BufferedImage toReturn;
        switch (facing){
            case UP:
            case LEFT:{
                toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(), GlobalSprites.get8Sprite());
                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                tx.translate(-toReturn.getWidth(null), 0);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                toReturn = op.filter(toReturn, null);
                break;
            }
            case DOWN:
            case RIGHT:{
                toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(), GlobalSprites.get8Sprite());
                break;
            }
            default:{
                frame = 0;
                toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(), GlobalSprites.get8Sprite());
                break;
            }
        }
        return toReturn;
    }
}
