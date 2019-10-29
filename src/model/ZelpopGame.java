package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import model.dungeon.Dungeon;
import model.global.GlobalDirection;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class ZelpopGame implements Game {

    private Dungeon dungeon;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public ZelpopGame(String source) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}

		dungeon = new Dungeon();
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		switch (commande){
			case RIGHT:
			    dungeon.moveHero(GlobalDirection.RIGHT);
				break;
			case DOWN:
                dungeon.moveHero(GlobalDirection.DOWN);
			    break;
			case UP:
                dungeon.moveHero(GlobalDirection.UP);
			    break;
			case LEFT:
                dungeon.moveHero(GlobalDirection.LEFT);
			    break;
		}

		dungeon.updateAll();
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

}
