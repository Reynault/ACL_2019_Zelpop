package model.state;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.global.Cmd;
import sprite.TextureFactory;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HowToPlay implements GameState {

    private static int TOP_SPAN = 50;

    private String title;
    private Label[] labels;


    public HowToPlay() {
        title = "How to play";

    }

    @Override
    public void draw(BufferedImage image) throws InterruptedException {
        BufferedImage titleImage;
        int width, height;
        TextManager textManager = TextureFactory.getTextManager();
        Color backgroundColor, titleColor;

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
        titleColor = Color.WHITE;
        titleImage = textManager.getString(title, titleColor);

        // Draw the title with the pencil
        crayon.drawImage(
                titleImage,
                (width / 2) - (titleImage.getWidth() / 2),
                TOP_SPAN,
                titleImage.getWidth(),
                titleImage.getHeight(),
                null
        );
    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {
        if (commande == Cmd.ATTACK) {
            game.setState(StateFactory.getInGame(new Dungeon()));
        }
    }
}
