package model.state;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.global.Cmd;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InGame implements GameState {

    private Dungeon dungeon;

    /**
     * Default constructor
     * @param dungeon dungeon of the game
     */
    public InGame(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();
        crayon.setBackground(new Color(0x4A362A));
        crayon.clearRect(0,0, image.getWidth(), image.getHeight());
        dungeon.draw(image);
        crayon.dispose();
    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {
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
                game.setState(StateFactory.getMenu());
                break;
            case ATTACK:
                dungeon.attack();
                break;
            case LEAVE_LEVEL:
                dungeon.changeLevel();
                break;
            case SAVE:
                game.save(dungeon);
                break;
        }

        if(commande != Cmd.SAVE && commande != Cmd.LEAVE_LEVEL) {
            dungeon.updateAll(game);
        }
    }
}
