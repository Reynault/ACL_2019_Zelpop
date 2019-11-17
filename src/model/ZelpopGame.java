package model;

import java.awt.image.BufferedImage;
import java.io.*;

import engine.Game;
import model.dungeon.Dungeon;
import model.global.Cmd;
import model.state.GameState;
import model.state.Menu;

public class ZelpopGame implements Game, Serializable {

	private GameState currentState;

	/**
	 * Default constructor
	 * 
	 */
	public ZelpopGame() {
		currentState = new Menu();
	}

	/**
	 * Draw the game
	 * @param img image
	 */
	public void draw(BufferedImage img){
		currentState.draw(img);
    }

	/**
	 * Set a state for the game
	 * @param state state for the game
	 */
	public void setState(GameState state) {
		currentState = state;
	}

	/**
	 * Save the game in a file
	 */
	public void save(Dungeon dungeon) {
		File f = new File("zelpopSave.ser");
		try{
			if(!f.exists()){
				f.createNewFile();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}

		try {
			FileOutputStream file = new FileOutputStream(f);
			ObjectOutputStream o = new ObjectOutputStream(file);
			o.writeObject(dungeon);
			o.close();
			file.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("Sauvegarde effectuée");
	}

	/**
	 * Load a game from a file
	 */
	public Dungeon load() {
		Dungeon dungeon = null;
		try {
			FileInputStream file = new FileInputStream(new File("zelpopSave.ser"));
			ObjectInputStream o = new ObjectInputStream(file);
			dungeon = (Dungeon)o.readObject();
			dungeon.setImages();
			o.close();
			file.close();
		}catch (Exception e){
			dungeon = new Dungeon();
		}
		return dungeon;
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

}
