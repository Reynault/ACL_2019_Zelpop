package model.state.buttons;

import model.ZelpopGame;
import model.dungeon.Dungeon;
import model.state.InGame;

public class ContinueButton extends MenuButton{
    public ContinueButton(String name) {
        super(name);
    }

    @Override
    public void trigger(ZelpopGame game) {
        Dungeon dungeon = game.load();
        game.setState(new InGame(dungeon));
    }
}
