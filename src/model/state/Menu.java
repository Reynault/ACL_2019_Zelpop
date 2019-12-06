package model.state;

import model.ZelpopGame;
import model.global.Cmd;
import model.global.GlobalSprites;
import model.state.buttons.ContinueButton;
import model.state.buttons.MenuButton;
import model.state.buttons.NewGame;
import model.state.buttons.QuitMenu;
import sprite.TextureFactory;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu implements GameState {
    private String title;

    private MenuButton[] buttons;
    private int selectedButton;

    private static int BUTTON_WIDTH = GlobalSprites.get8Sprite() * GlobalSprites.getScaling() * 10;
    private static int BUTTON_HEIGHT = GlobalSprites.get8Sprite() * GlobalSprites.getScaling() * 3;
    private static int TOP_SPAN = 50;

    public Menu() {
        title = "Zelpop";

        selectedButton = 0;

        buttons = new MenuButton[3];
        buttons[0] = new NewGame("New Game");
        buttons[1] = new ContinueButton("Continue");
        buttons[2] = new QuitMenu("Quit");
    }

    @Override
    public void draw(BufferedImage image) throws InterruptedException {

        MenuButton button;
        BufferedImage titleImage;
        int width, height;
        TextManager textManager = TextureFactory.getTextManager();
        Color backgroundColor, titleColor, buttonColor, selectedButtonColor;

        Graphics2D crayon = (Graphics2D) image.getGraphics();

        width = image.getWidth();
        height = image.getHeight();

        backgroundColor = new Color(0x4A362A);
        titleColor = Color.WHITE;
        buttonColor = Color.BLACK;
        selectedButtonColor = Color.GRAY;

        // Setting background color (Brown)
        crayon.setBackground(backgroundColor);
        crayon.clearRect(0, 0, width, height);

        // Fecthing the title
        titleImage = textManager.getString(title, titleColor);

        // Which is an image drawn by the pencil
        crayon.drawImage(
                titleImage,
                (width / 2) - (titleImage.getWidth() / 2),
                TOP_SPAN,
                titleImage.getWidth(),
                titleImage.getHeight(),
                null
        );

        for (int i = 0; i < buttons.length; i++) {
            button = buttons[i];
            if (i == selectedButton) {
                crayon.setColor(selectedButtonColor);
            } else {
                crayon.setColor(buttonColor);
            }
            crayon.fillRect(
                    ((width / 2) - (BUTTON_WIDTH / 2)),
                    ((TOP_SPAN + BUTTON_HEIGHT) * i) + (titleImage.getHeight() + TOP_SPAN * 3),
                    BUTTON_WIDTH,
                    BUTTON_HEIGHT
            );
            titleImage = textManager.getString(button.getName(), titleColor);
            crayon.drawImage(
                    titleImage,
                    ((width / 2) - (titleImage.getWidth() / 2)),
                    ((TOP_SPAN + BUTTON_HEIGHT) * i) + (titleImage.getHeight() + TOP_SPAN * 3) + (BUTTON_HEIGHT / 2) -
                            (GlobalSprites.get8Sprite() * GlobalSprites.getScaling()) / 2,
                    titleImage.getWidth(),
                    titleImage.getHeight(),
                    null
            );
        }

        crayon.dispose();
    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {
        switch (commande) {
            case UP:
                if (selectedButton == 0) {
                    selectedButton = 2;
                } else {
                    selectedButton = (selectedButton - 1) % 3;
                }
                break;
            case DOWN:
                selectedButton = (selectedButton + 1) % 3;
                break;
            case ATTACK:
                buttons[selectedButton].trigger(game);
                break;
            case EXIT_GAME:
                buttons[2].trigger(game);
                break;
        }
    }
}
