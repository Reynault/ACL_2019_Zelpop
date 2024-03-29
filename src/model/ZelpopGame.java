package model;

import java.awt.image.BufferedImage;
import java.io.*;

import engine.Game;
import model.dungeon.Dungeon;
import model.global.Cmd;
import model.state.GameState;
import model.state.Menu;
import sound.Sound;

public class ZelpopGame implements Game, Serializable {

	private GameState currentState;
	private String SAVE_PATH = "zelpopSave.ser";

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
	public void draw(BufferedImage img) throws InterruptedException{
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
		File f = new File(SAVE_PATH);
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
//		System.out.println("Sauvegarde effectuée");
	}

	/**
	 * Load a game from a file
	 */
	public Dungeon load() {
		Dungeon dungeon = null;
		try {
			FileInputStream file = new FileInputStream(new File(SAVE_PATH));
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

		// This switch is here to change the sound volume anywhere and anytime in the game
		switch (commande){
			case INCREASE_SOUND:
				Sound.increaseSound();
				Sound.playSound(Sound.MENU_BUTTON);
				break;
			case DECREASE_SOUND:
				Sound.decreaseSound();
				Sound.playSound(Sound.MENU_BUTTON);
				break;
		}
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
