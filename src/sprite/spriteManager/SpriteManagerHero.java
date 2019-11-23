package sprite.spriteManager;

import model.global.Cmd;
import model.global.GlobalSprites;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
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
    public List<BufferedImage> getCurrentSprite(){
        ArrayList<BufferedImage> toReturn = new ArrayList<>();
        BufferedImage construct;

        switch (facing){
            case DOWN:{
                construct = sprite.getSubimage(GlobalSprites.get8Sprite() * frame, GlobalSprites.get8Sprite() * DOWN, GlobalSprites.get8Sprite(), GlobalSprites.get8Sprite());
                break;
            }
            case UP:{
                construct = sprite.getSubimage(GlobalSprites.get8Sprite() * frame, GlobalSprites.get8Sprite() * UP, GlobalSprites.get8Sprite(), GlobalSprites.get8Sprite());
                break;
            }
            case LEFT:{
                construct = sprite.getSubimage(GlobalSprites.get8Sprite() * frame, GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(), GlobalSprites.get8Sprite());
                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                tx.translate(-construct.getWidth(null), 0);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                construct = op.filter(construct, null);
                break;
            }
            case RIGHT:{
                construct = sprite.getSubimage(GlobalSprites.get8Sprite() * frame, GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(), GlobalSprites.get8Sprite());
                break;
            }
            default:{
                frame = 0;
                construct = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(), GlobalSprites.get8Sprite());
                break;
            }
        }

        toReturn.add(construct);
        return toReturn;
    }
}
