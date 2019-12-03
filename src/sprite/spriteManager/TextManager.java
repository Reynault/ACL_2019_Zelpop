package sprite.spriteManager;

import model.global.GlobalSprites;
import sprite.TextureFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextManager {

    private BufferedImage text;
    private BufferedImage[] images;

    public TextManager(){
        text = TextureFactory.getTextureFactory().getText();
        int taille = GlobalSprites.get8Sprite();
        int asciiCounter;
        images = new BufferedImage[123];

        // Int
        int x = 4;
        int y = 6;

        for(asciiCounter = 48; asciiCounter < (10+48); asciiCounter++){
            images[asciiCounter] = text.getSubimage(
                    x * taille,
                    y * taille,
                    taille,
                    taille
            );

            x++;
        }

        // Uppercase
        x = 10;
        y = 9;

        for(asciiCounter = 65; asciiCounter < (26+65); asciiCounter++){
            if(asciiCounter == (6+65) || asciiCounter == (22+65)){
                y++;
                x = 0;
            }
            images[asciiCounter] = text.getSubimage(
                    x * taille,
                    y * taille,
                    taille,
                    taille
            );

            x ++;
        }

        // Lowercase
        x = 0;
        y = 8;

        for(asciiCounter = 97; asciiCounter < (26+97); asciiCounter++){
            if(asciiCounter == (16+97)){
                x = 0;
                y ++;
            }
            images[asciiCounter] = text.getSubimage(
                    x * taille,
                    y * taille,
                    taille,
                    taille
            );

            x ++;
        }

        // Special

        // Space
        images[32] = text.getSubimage(
                11*taille,
                0,
                taille,
                taille
        );

        // :
        images[58] = text.getSubimage(
                10*taille,
                7*taille,
                taille,
                taille
        );

        // ,
        images[44] = text.getSubimage(
                8*taille,
                7*taille,
                taille,
                taille
        );

        // .
        images[46] = text.getSubimage(
                9*taille,
                7*taille,
                taille,
                taille
        );

        // .
        images[45] = text.getSubimage(
                5*taille,
                7*taille,
                taille,
                taille
        );
    }

    public BufferedImage getString(String s, Color color){
        BufferedImage character;

        BufferedImage image = new BufferedImage(
                GlobalSprites.get8Sprite() * s.length() * GlobalSprites.getScaling(),
                GlobalSprites.get8Sprite() * GlobalSprites.getScaling(),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D crayon = (Graphics2D) image.getGraphics();

        for(int i = 0; i < s.length(); i++){
            character = images[s.charAt(i)];

            for(int j = 0 ; j < character.getHeight(); j++){
                for(int k = 0; k < character.getWidth(); k++){
                    if( (character.getRGB(j, k) >> 24) != 0x00 ){
                        character.setRGB(
                                j,
                                k,
                                color.getRGB()
                        );
                    }
                }
            }

            crayon.drawImage(
                    character,
                    character.getWidth() * i * GlobalSprites.getScaling(),
                    0,
                    character.getWidth() * GlobalSprites.getScaling(),
                    character.getHeight() * GlobalSprites.getScaling(),
                    null
            );
        }

        crayon.dispose();

        return image;
    }

}
