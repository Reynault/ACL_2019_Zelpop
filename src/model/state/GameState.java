package model.state;

import engine.Cmd;
import model.ZelpopGame;

import java.awt.image.BufferedImage;

public interface GameState {

    void draw(BufferedImage image);

    void evolve(ZelpopGame game, Cmd commande);

}
