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
	protected static final int SCALING = 4;

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
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		//crayon.setColor(Color.blue);
		//crayon.fillOval(0,0,10,10);

		/***** test en cours *****/
		//BufferedImage hero = TextureFactory.getTextureFactory().getHero();
		//Random random = new Random();
		//BufferedImage perso = hero.getSubimage(8 * random.nextInt(3),8 * random.nextInt(3), 8, 8);

		//SpriteManager spritePerso = new SpriteManagerHero(TextureFactory.getTextureFactory().getHero());
		//SpriteManager spriteMonster = new SpriteManagerMonster(TextureFactory.getTextureFactory().getMonster());

		//crayon.drawImage(spritePerso.getCurrentSprite(), 0 * SCALING, 0 * SCALING, spritePerso.getCurrentSprite().getWidth() * SCALING, spriteMonster.getCurrentSprite().getHeight() * SCALING, null);
		//crayon.drawImage(spriteMonster.getCurrentSprite(), 10 * SCALING,10 * SCALING, spriteMonster.getCurrentSprite().getWidth() * SCALING, spriteMonster.getCurrentSprite().getHeight() * SCALING, null);

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
