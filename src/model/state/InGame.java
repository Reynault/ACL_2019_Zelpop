package model.state;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.global.Cmd;
import sound.Sound;
import sprite.TextureFactory;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class InGame implements GameState {

    private Dungeon dungeon;

    /**
     * Default constructor
     * @param dungeon dungeon of the game
     */
    public InGame(Dungeon dungeon){
        this.dungeon = dungeon;

        // task that play some random sounds
        TimerTask task = new TimerTask() {
            private Random random = new Random();
            private String[] sounds = {
                    Sound.CAVE_1,
                    Sound.CAVE_2,
                    Sound.CAVE_3,
                    Sound.CAVE_4,
                    Sound.CAVE_5,
                    Sound.CAVE_6,
                    Sound.CAVE_7,
            };

            @Override
            public void run() {
                // Playing a random sound
                Sound.playSound(sounds[random.nextInt(sounds.length)]);
            }
        };
        // Sound will play the task
        Sound.playDelaySound(task, 15000);
    }

    @Override
    public void draw(BufferedImage image) throws InterruptedException{
        Graphics2D crayon = (Graphics2D) image.getGraphics();
        crayon.setBackground(new Color(0x4A362A));
        crayon.clearRect(0,0, image.getWidth(), image.getHeight());
        dungeon.draw(image);
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
