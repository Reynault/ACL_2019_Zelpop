package model.state.buttons;

import model.ZelpopGame;

public class QuitMenu extends MenuButton {

    public QuitMenu(String name) {
        super(name);
    }

    @Override
    public void trigger(ZelpopGame game) {
        System.exit(0);
    }
}
