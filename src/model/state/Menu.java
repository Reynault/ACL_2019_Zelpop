package model.state;

import engine.Cmd;
import model.ZelpopGame;
import model.global.GlobalSprites;
import model.state.buttons.ContinueButton;
import model.state.buttons.MenuButton;
import model.state.buttons.NewGame;
import model.state.buttons.QuitMenu;
import sprite.spriteManager.TextManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu implements GameState{
    private String title;

    private MenuButton[] buttons;
    private int selectedButton;
    private TextManager textManager;

    private static int BUTTON_WIDTH = GlobalSprites.get8Sprite()*GlobalSprites.getScaling()*6;
    private static int BUTTON_HEIGHT = GlobalSprites.get8Sprite()*GlobalSprites.getScaling()*3;
    private static int TOP_SPAN = 50;

    public Menu(){
        title = "Zelpop";

        selectedButton = 0;

        buttons = new MenuButton[3];
        buttons[0] = new NewGame("New Game");
        buttons[1] = new ContinueButton("Continue");
        buttons[2] = new QuitMenu("Quit");

        textManager = new TextManager();
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();
        crayon.setColor(Color.BLACK);
        int width = image.getWidth();
        int height = image.getHeight();

        MenuButton button;

        BufferedImage titleImage = textManager.getString(title, Color.RED);
        BufferedImage buttonImage;

        crayon.drawImage(
                titleImage,
                (width/2) - (titleImage.getWidth()/2),
                TOP_SPAN,
                titleImage.getWidth(),
                titleImage.getHeight(),
                null
        );

        for(int i = 0; i < buttons.length; i++){
            button = buttons[i];

//            crayon.draw(
//                    ((width/2) - (BUTTON_WIDTH/2)),
//                    ((TOP_SPAN + BUTTON_HEIGHT) * i) + (titleImage.getHeight() + TOP_SPAN*2),
//                    BUTTON_WIDTH,
//                    BUTTON_HEIGHT
//            );
        }
    }

    @Override
    public void evolve(ZelpopGame game, Cmd commande) {
        switch (commande){
            case UP:
                if(selectedButton == 0){
                    selectedButton = 2;
                }else {
                    selectedButton = (selectedButton - 1) % 3;
                }
                break;
            case DOWN:
                selectedButton = (selectedButton + 1) % 3;
                break;
            case ATTACK:
                buttons[selectedButton].trigger(game);
                break;
        }
    }
}
