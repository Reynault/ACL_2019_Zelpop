package model.state;

import model.ZelpopGame;
import model.global.Cmd;

import java.awt.image.BufferedImage;

public interface GameState {

    void draw(BufferedImage image);

    void evolve(ZelpopGame game, Cmd commande);

}
