package model.state.buttons;

import model.ZelpopGame;

public abstract class MenuButton {
    private String name;

    public MenuButton(String name) {
        this.name = name;
    }

    public abstract void trigger(ZelpopGame game);

    public String getName(){
        return name;
    }
}
