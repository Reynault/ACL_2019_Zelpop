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
        if(dungeon == null){
            game.setState(new InGame(new Dungeon()));
        }else{
            game.setState(new InGame(dungeon));
        }
    }
}
