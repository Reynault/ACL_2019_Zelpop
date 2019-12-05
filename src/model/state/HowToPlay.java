package model.state;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.global.Cmd;
import model.global.GlobalSprites;
import sprite.TextureFactory;
import sprite.spriteManager.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HowToPlay implements GameState {

    private static int TOP_SPAN = 50;
    private static int HEIGHT_LABEL = 20;

    private BufferedImage title;
    private BufferedImage[] command, mobs, stats, sidebar;
    private SpriteManager[] ennemies, images;

    private Timer animationTimer;
    private int currentEnnemy;
    private Random random;

    public HowToPlay() {
        currentEnnemy = 0;

        Color textColor = Color.WHITE;
        TextManager textManager = new TextManager();

        random = new Random();

        // Setting title and text
        title = textManager.getString("How to play", textColor);

        command = new BufferedImage[6];
        command[0] = textManager.getString("Z Q S D : move", textColor);
        command[1] = textManager.getString("SPACE : attack", textColor);
        command[2] = textManager.getString("E : change level above stairs", textColor);
        command[3] = textManager.getString("O : save", textColor);
        command[4] = textManager.getString("ESCAPE : quit to menu without save", textColor);
        command[5] = textManager.getString("J K L M : up a stat for a cost", textColor);

        mobs = new BufferedImage[4];
        mobs[0] = textManager.getString("You", textColor);
        mobs[1] = textManager.getString("Enemies", textColor);
        mobs[2] = textManager.getString("Chest", textColor);
        mobs[3] = textManager.getString("Trap or not", textColor);

        stats = new BufferedImage[4];
        stats[0] = textManager.getString("Health", textColor);
        stats[1] = textManager.getString("Regen", textColor);
        stats[2] = textManager.getString("Attack", textColor);
        stats[3] = textManager.getString("Counter", textColor);

        sidebar = new BufferedImage[5];
        sidebar[0] = textManager.getString("Depth", textColor);
        sidebar[1] = textManager.getString("Mini map", textColor);
        sidebar[2] = textManager.getString("Health left", textColor);
        sidebar[3] = textManager.getString("Score", Color.ORANGE);
        sidebar[4] = textManager.getString("cost", Color.RED);

        // Setting images of tiles and hero
        images = new SpriteManager[4];

        images[0] = new SpriteManagerHero(
                TextureFactory.getTextureFactory().getHero()
        );

        images[2] = new SpriteManagerTile(
                TextureFactory.getTextureFactory().getFilledTreasure()
        );

        images[3] = new SpriteManagerTile(
                TextureFactory.getTextureFactory().getTraps()
        );

        // Setting images for ennemies
        ennemies = new SpriteManager[3];

        ennemies[0] = new SpriteManagerMonster(
                TextureFactory.getTextureFactory().getGobelin()
        );
        ennemies[1] = new SpriteManagerMonster(
                TextureFactory.getTextureFactory().getMonster()
        );
        ennemies[2] = new SpriteManagerMonster(
                TextureFactory.getTextureFactory().getGhost()
        );

        // Timer is the object that will launch the animation
        animationTimer = new Timer();

        // The animation is done by a timer task object
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // Looping on ennemies
                currentEnnemy = (currentEnnemy+1) % 3;

                // Changing the direction for both hero and ennemy
                switch (random.nextInt(4)){
                    case 0:
                        images[0].setSprite(Cmd.DOWN);
                        ennemies[currentEnnemy].setSprite(Cmd.DOWN);
                        break;
                    case 1:
                        images[0].setSprite(Cmd.UP);
                        ennemies[currentEnnemy].setSprite(Cmd.UP);
                        break;
                    case 2:
                        images[0].setSprite(Cmd.LEFT);
                        ennemies[currentEnnemy].setSprite(Cmd.LEFT);
                        break;
                    case 3:
                        images[0].setSprite(Cmd.RIGHT);
                        ennemies[currentEnnemy].setSprite(Cmd.RIGHT);
                        break;
                }
            }
        };
        // Then we schedule the task
        animationTimer.schedule(timerTask,
                GlobalSprites.getHowToAnimationDelay(),
                GlobalSprites.getHowToAnimationDelay());
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

        // Draw the title with the pencil
        crayon.drawImage(
                title,
                (width / 2) - (title.getWidth() / 2),
                TOP_SPAN/2,
                title.getWidth(),
                title.getHeight(),
                null
        );

        // Draw the labels for the commands
        cptCommand = HEIGHT_LABEL;
        for(BufferedImage s : command){
            if(s.equals(command[2]) || s.equals(command[4]) || s.equals(command[5])){ // change level & quit & up a stat
                widthText = 700;
            }
            else if(s.equals(command[3])){   // save
                widthText = 200;
            }
            else {  // move & attack
                widthText = 400;
            }
            crayon.drawImage(
                    s,
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
        for(int i = 0 ; i < mobs.length ; i++) {
            if (i == 0 || i == 2) {
                widthText = 100;
            } else {  // move & attack
                widthText = 200;
            }
            crayon.drawImage(
                    mobs[i],
                    cptMob,
                    TOP_SPAN + HEIGHT_LABEL * 5 + cptCommand * 2,
                    widthText,
                    HEIGHT_LABEL,
                    null
            );
            cptMob = cptMob + HEIGHT_LABEL *4 + widthText;
        }

        // Draw tiles, hero and ennemies
        cptMob = HEIGHT_LABEL;
        for (int i = 0; i < images.length; i++){
            if (i == 0 || i == 2) {
                widthText = 100;
            } else {  // move & attack
                widthText = 200;
            }
            if(i == 1) {
                crayon.drawImage(
                        ennemies[currentEnnemy].getCurrentSprite(),
                        cptMob,
                        TOP_SPAN + HEIGHT_LABEL * 7 + cptCommand * 2,
                        100,
                        100,
                        null);
            }else{
                crayon.drawImage(
                        images[i].getCurrentSprite(),
                        cptMob,
                        TOP_SPAN + HEIGHT_LABEL * 7 + cptCommand * 2,
                        100,
                        100,
                        null);
            }
            cptMob += HEIGHT_LABEL * 4 + widthText;
        }

        // Draw the labels of the right part
        crayon.drawImage(
                sidebar[0],
                1100 - HEIGHT_LABEL,
                TOP_SPAN / 2,
                100,
                HEIGHT_LABEL,
                null
        );

        crayon.drawImage(
                sidebar[1],
                1100 - HEIGHT_LABEL,
                170,
                120,
                HEIGHT_LABEL,
                null
        );

        crayon.drawImage(
                sidebar[2],
                1100 - HEIGHT_LABEL,
                350,
                120,
                HEIGHT_LABEL,
                null
        );

        crayon.drawImage(
                sidebar[3],
                1100 - HEIGHT_LABEL,
                450,
                100,
                HEIGHT_LABEL,
                null
        );

        // Bottom-left corner
        for(int i = 0 ; i < stats.length ; i++){
            crayon.drawImage(
                    stats[i],
                    1020,
                    550 + i * HEIGHT_LABEL * 2,
                    100,
                    HEIGHT_LABEL,
                    null
            );

            crayon.drawImage(
                    sidebar[4],
                    1200,
                    550 + i * HEIGHT_LABEL * 2,
                    50,
                    HEIGHT_LABEL/2,
                    null
            );

        }
        crayon.dispose();

    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {

        // Then we execute the command
        if (commande == Cmd.ATTACK) {
            // First thing first, we cancel the animation timer
            animationTimer.cancel();
            game.setState(StateFactory.getInGame(new Dungeon(
                    HowToPlay.class.getClassLoader().getResourceAsStream("level/testMazeStat.txt")
            )));
        }else if(commande == Cmd.EXIT_GAME) {
            // First thing first, we cancel the animation timer
            animationTimer.cancel();
            game.setState(StateFactory.getMenu());
        }
    }
}
