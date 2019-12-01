package model.state;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.global.Cmd;
import sprite.TextureFactory;
import sprite.spriteManager.SpriteManager;
import sprite.spriteManager.SpriteManagerHero;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HowToPlay implements GameState {

    private static int TOP_SPAN = 50;
    private static int HEIGHT_LABEL = 20;

    private String title;
    private String[] command, mobs, stats;
    private BufferedImage[] images, ennemies;


    public HowToPlay() {
        title = "How to play";

        command = new String[5];
        command[0] = "Z Q S D : move";
        command[1] = "SPACE : attack";
        command[2] = "Z : change level above stairs";
        command[3] = "O : save";
        command[4] = "ESCAPE : quit to menu without save";

        mobs = new String[4];
        mobs[0] = "You";
        mobs[1] = "Enemies";
        mobs[2] = "Chest";
        mobs[3] = "Trap or not";

        images = new BufferedImage[4];
        images[0] = TextureFactory.getTextureFactory().getHero();
        images[1] = TextureFactory.getTextureFactory().getMonster();
        images[2] = TextureFactory.getTextureFactory().getFilledTreasure();
        images[3] = TextureFactory.getTextureFactory().getTraps();

        ennemies = new BufferedImage[3];
        ennemies[0] = TextureFactory.getTextureFactory().getMonster();
        ennemies[1] = TextureFactory.getTextureFactory().getGhost();
        ennemies[2] = TextureFactory.getTextureFactory().getGobelin();

        stats = new String[4];
        stats[0] = "Health";
        stats[1] = "Regen";
        stats[2] = "Attack";
        stats[3] = "Counter";

    }

    @Override
    public void draw(BufferedImage image) throws InterruptedException {
        BufferedImage textImage;
        int width, height, widthText;
        int cptCommand, cptMob;
        TextManager textManager = TextureFactory.getTextManager();
        Color backgroundColor, textColor;

        Graphics2D crayon = (Graphics2D) image.getGraphics();

        width = image.getWidth();
        height = image.getHeight();

        // Setting background color (Brown)
        backgroundColor = new Color(0x4A362A);
        crayon.setBackground(backgroundColor);
        crayon.clearRect(0, 0, width, height);
        // Setting the right interface
        backgroundColor = new Color(0x646464);
        crayon.setBackground(backgroundColor);
        crayon.clearRect(1000, 0, 280, height);

        // Fecthing the title
        textColor = Color.WHITE;
        textImage = textManager.getString(title, textColor);

        // Draw the title with the pencil
        crayon.drawImage(
                textImage,
                (width / 2) - (textImage.getWidth() / 2),
                TOP_SPAN/2,
                textImage.getWidth(),
                textImage.getHeight(),
                null
        );

        // Draw the labels for the commands
        cptCommand = HEIGHT_LABEL;
        for(String s : command){
            if(s.equals(command[2]) || s.equals(command[4])){ // change level & quit
                widthText = 700;
            }
            else if(s.equals(command[3])){   // save
                widthText = 200;
            }
            else {  // move & attack
                widthText = 400;
            }
            textImage = textManager.getString(s, textColor);
            crayon.drawImage(
                    textImage,
                    HEIGHT_LABEL,
                    TOP_SPAN + HEIGHT_LABEL + cptCommand * 2,
                    widthText,
                    HEIGHT_LABEL,
                    null
            );
            cptCommand = cptCommand + HEIGHT_LABEL;
        }

        // Draw the labels for the mobs/tiles
        cptMob = HEIGHT_LABEL;
        for(int i = 0 ; i < mobs.length ; i++){
            if(i == 0 || i == 2){
                widthText = 100;
            }
            else {  // move & attack
                widthText = 200;
            }
            textImage = textManager.getString(mobs[i], textColor);
            crayon.drawImage(
                    textImage,
                    cptMob,
                    TOP_SPAN + HEIGHT_LABEL *5 + cptCommand * 2,
                    widthText,
                    HEIGHT_LABEL,
                    null
            );
            if(i != 1) {
                crayon.drawImage(
                        images[i],
                        cptMob,
                        TOP_SPAN + HEIGHT_LABEL * 7 + cptCommand * 2,
                        100,
                        100,
                        null);
            }
            else {  // Multiple images
                //TODO Défilement des mobs
            }
            cptMob = cptMob + HEIGHT_LABEL *4 + widthText;
        }

        // Draw the labels of the right part
        textImage = textManager.getString("Depth", textColor);
        crayon.drawImage(
                textImage,
                1100 - HEIGHT_LABEL,
                TOP_SPAN / 2,
                100,
                HEIGHT_LABEL,
                null
        );

        textImage = textManager.getString("Mini map", textColor);
        crayon.drawImage(
                textImage,
                1100 - HEIGHT_LABEL,
                170,
                100,
                HEIGHT_LABEL/2,
                null
        );

        textImage = textManager.getString("Health left", textColor);
        crayon.drawImage(
                textImage,
                1100 - HEIGHT_LABEL,
                350,
                100,
                HEIGHT_LABEL,
                null
        );

        textImage = textManager.getString("Score", Color.ORANGE);
        crayon.drawImage(
                textImage,
                1100 - HEIGHT_LABEL,
                450,
                100,
                HEIGHT_LABEL,
                null
        );

        // Bottom-left corner
        for(int i = 0 ; i < stats.length ; i++){
            textImage = textManager.getString(stats[i], textColor);
            crayon.drawImage(
                    textImage,
                    1020,
                    550 + i * HEIGHT_LABEL * 2,
                    100,
                    HEIGHT_LABEL,
                    null
            );

            //TODO Affichage du bouton pour augmenter la stat

            textImage = textManager.getString("cost", Color.RED);
            crayon.drawImage(
                    textImage,
                    1200,
                    550 + i * HEIGHT_LABEL * 2,
                    50,
                    HEIGHT_LABEL/2,
                    null
            );

        }


    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {
        if (commande == Cmd.ATTACK) {
            game.setState(StateFactory.getInGame(new Dungeon()));
        }
        else if(commande == Cmd.EXIT_GAME) {
            game.setState(StateFactory.getMenu());
        }
    }
}
