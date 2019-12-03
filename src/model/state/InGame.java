package model.state;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.global.Cmd;
import sprite.TextureFactory;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InGame implements GameState {

    private static int HEIGHT_LABEL = 20;

    private Dungeon dungeon;
    private String[] stats, letters;

    /**
     * Default constructor
     * @param dungeon dungeon of the game
     */
    public InGame(Dungeon dungeon){
        this.dungeon = dungeon;
        stats = new String[4];
        stats[0] = "HP:";
        stats[1] = "RGN:";
        stats[2] = "ATK:";
        stats[3] = "DEF:";

        letters = new String[4];
        letters[0] = "J";
        letters[1] = "K";
        letters[2] = "L";
        letters[3] = "M";
    }

    @Override
    public void draw(BufferedImage image) throws InterruptedException{
        BufferedImage textImage;
        TextManager textManager = TextureFactory.getTextManager();
        Color textColor;
        Graphics2D crayon = (Graphics2D) image.getGraphics();
        crayon.setBackground(new Color(0x4A362A));
        crayon.clearRect(0,0, image.getWidth(), image.getHeight());
        dungeon.draw(image);

        textColor = Color.WHITE;

        // Bottom-left corner : stats
        for(int i = 0 ; i < stats.length ; i++){
            textImage = textManager.getString(stats[i], textColor);
            crayon.drawImage(
                    textImage,
                    1010,
                    550 + i * HEIGHT_LABEL * 2,
                    75,
                    HEIGHT_LABEL,
                    null
            );

            // Press the letter to up the stat
            textImage = textManager.getString(letters[i], Color.black);
            crayon.drawImage(
                    textImage,
                    1150,
                    550 + i * HEIGHT_LABEL * 2,
                    15,
                    HEIGHT_LABEL,
                    null
            );

        }

        crayon.dispose();
    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {
        boolean dontUpdate = false;
        switch (commande) {
            case RIGHT:
                dungeon.moveHero(Cmd.RIGHT);
                break;
            case DOWN:
                dungeon.moveHero(Cmd.DOWN);
                break;
            case UP:
                dungeon.moveHero(Cmd.UP);
                break;
            case LEFT:
                dungeon.moveHero(Cmd.LEFT);
                break;
            case EXIT_GAME:
                dontUpdate = true;
                game.setState(StateFactory.getMenu());
                break;
            case ATTACK:
                dungeon.attack();
                break;
            case LEAVE_LEVEL:
                dontUpdate = true;
                dungeon.changeLevel();
                break;
            case SAVE:
                dontUpdate = true;
                game.save(dungeon);
                break;
            case RESTART:
                dontUpdate = true;
                break;
            // Up a stat
            case HP:
                dontUpdate = true;
                break;
            case RGN:
                dontUpdate = true;
                break;
            case ATK:
                dontUpdate = true;
                break;
            case CNT:
                dontUpdate = true;
                break;
        }

        if(!dontUpdate) {
            dungeon.updateAll(game);
        }
    }
}
