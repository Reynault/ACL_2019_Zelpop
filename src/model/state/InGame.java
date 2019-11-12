package model.state;

import engine.Cmd;
import engine.Game;
import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.global.GlobalDirection;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InGame implements GameState{
    private Dungeon dungeon;

    public InGame(){
        dungeon = new Dungeon();
    }

    public InGame(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();
        crayon.setBackground(new Color(0x4A362A));
        crayon.clearRect(0,0, image.getWidth(), image.getHeight());
        dungeon.draw(image);
    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {
        if(commande != Cmd.IDLE) {
            switch (commande) {
                case RIGHT:
                    dungeon.moveHero(GlobalDirection.RIGHT);
                    break;
                case DOWN:
                    dungeon.moveHero(GlobalDirection.DOWN);
                    break;
                case UP:
                    dungeon.moveHero(GlobalDirection.UP);
                    break;
                case LEFT:
                    dungeon.moveHero(GlobalDirection.LEFT);
                    break;
                case EXIT_GAME:
                    System.exit(0);
                    break;
            }

            dungeon.updateAll();
        }
    }
}
