package model.state;

import model.ZelpopGame;
import model.global.Cmd;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public interface GameState{

    void draw(BufferedImage image);

    void evolve(ZelpopGame game, Cmd commande);

}
