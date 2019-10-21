package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.Random;

import engine.GamePainter;
import sprite.TextureFactory;

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
	protected static final int SCALING = 4;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public ZelpopPainter() {
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		//crayon.setColor(Color.blue);
		//crayon.fillOval(0,0,10,10);

		/***** test en cours *****/
		BufferedImage hero = TextureFactory.getTextureFactory().getHero();
		Random random = new Random();
		BufferedImage perso = hero.getSubimage(8 * random.nextInt(3),8 * random.nextInt(3), 8, 8);
		crayon.drawImage(perso, 0, 0, perso.getWidth() * SCALING, perso.getHeight() * SCALING, null);

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
