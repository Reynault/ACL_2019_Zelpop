package sprite.spriteManager;

import model.global.GlobalSprites;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class SpriteManagerMonster extends SpriteManagerEntity{

    public SpriteManagerMonster(BufferedImage sprite) {
        super(sprite);
    }

    @Override
    public BufferedImage getCurrentSprite() {
        BufferedImage toReturn;

        if (isAttacking()) {
            switch (facing) {
                case DOWN:
                case RIGHT:
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * 4 + attackFrame * GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * attackFrame,
                            GlobalSprites.get8Sprite());
                    break;
                case UP:
                case LEFT:
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * 4 + attackFrame * GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite() + GlobalSprites.get8Sprite() * attackFrame,
                            GlobalSprites.get8Sprite());
                    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                    tx.translate(-(toReturn.getWidth(null) - (GlobalSprites.get8Sprite() * attackFrame)), 0);
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    toReturn = op.filter(toReturn, null);
                    break;

                default: {
                    frame = 0;
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    break;
                }
            }
        } else {
            switch (facing) {
                case UP:
                case LEFT: {
                    toReturn = sprite.getSubimage(GlobalSprites.get8Sprite() * frame,
                            GlobalSprites.get8Sprite() * RIGHT, GlobalSprites.get8Sprite(),
                            GlobalSprites.get8Sprite());
                    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                    tx.translate(-toReturn.getWidth(null), 0);
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    toReturn = op.filter(toReturn, null);
                    break;
                }
                case DOWN:
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

        return toReturn;
    }

}
