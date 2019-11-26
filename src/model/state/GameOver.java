package model.state;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.global.Cmd;
import sprite.TextureFactory;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOver implements GameState{

    private Dungeon dungeon;

    private static String REPLAY_MESSAGE = "Replay: r";
    private static String MENU_MESSAGE = "Menu: escape";

    public GameOver(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public void draw(BufferedImage image) throws InterruptedException{
        Graphics2D crayon = (Graphics2D) image.getGraphics();
        dungeon.draw(image);

        int width = image.getWidth();
        int height = image.getHeight();

        int midWidth = width / 2;
        int midHeight = height / 2;

        Color backgroundColor = new Color(89, 89, 89, 200);
        Color white = Color.WHITE;
        crayon.setColor(backgroundColor);
        crayon.fillRect(0,0, width, height);
        TextManager manager = TextureFactory.getTextManager();

        BufferedImage replay_message = manager.getString(REPLAY_MESSAGE, white);
        BufferedImage menu_message = manager.getString(MENU_MESSAGE, white);

        crayon.drawImage(
                replay_message,
                midWidth - (replay_message.getWidth()/2),
                midHeight - replay_message.getHeight(),
                replay_message.getWidth(),
                replay_message.getHeight(),
                null
        );


        crayon.drawImage(
                menu_message,
                midWidth - (menu_message.getWidth()/2),
                midHeight + menu_message.getHeight(),
                menu_message.getWidth(),
                menu_message.getHeight(),
                null
        );

        crayon.dispose();
    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {
        switch (commande){
            case EXIT_GAME:
                game.setState(StateFactory.getMenu());
                break;
            case RESTART:
                game.setState(StateFactory.getInGame(new Dungeon()));
                break;
        }
    }
}
