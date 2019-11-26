package model;

import java.awt.*;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class ZelpopPainter implements GamePainter {

	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 1280;
	protected static final int HEIGHT = 720;

	protected Game game;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public ZelpopPainter(Game game) {
	    this.game = game;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) throws InterruptedException{
		game.draw(im);
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
