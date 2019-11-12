package model.state.buttons;

import model.ZelpopGame;
import model.state.InGame;

public class NewGame extends MenuButton {

    public NewGame(String name) {
        super(name);
    }

    @Override
    public void trigger(ZelpopGame game) {
        game.setState(new InGame());
    }
}
