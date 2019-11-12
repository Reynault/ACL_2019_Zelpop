package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import model.dungeon.Dungeon;
import model.global.GlobalDirection;
import model.state.GameState;
import model.state.Menu;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class ZelpopGame implements Game {

	private GameState currentState;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public ZelpopGame() {
		currentState = new Menu();
	}

	public void draw(BufferedImage img){
		currentState.draw(img);
    }

	public void setState(GameState state) {
		currentState = state;
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		currentState.evolve(this, commande);
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

	public void save(){

	}

	public Dungeon load(){
		return null;
	}

}
