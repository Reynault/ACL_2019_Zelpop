package model.state.buttons;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.state.InGame;
import model.state.StateFactory;

public class NewGame extends MenuButton {

    public NewGame(String name) {
        super(name);
    }

    @Override
    public void trigger(ZelpopGame game) {
        game.setState(StateFactory.getInGame(new Dungeon()));
    }
}
