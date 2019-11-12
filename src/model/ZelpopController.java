package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.Cmd;
import engine.GameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class ZelpopController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public ZelpopController() {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}

	@Override
	public void resetCommand() {
		this.commandeEnCours = Cmd.IDLE;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyChar()) {
			// si on appuie sur 'q',commande joueur est gauche
			case 'q':
			case 'Q':
				this.commandeEnCours = Cmd.LEFT;
				break;
			case 'd':
			case 'D':
				this.commandeEnCours = Cmd.RIGHT;
				break;
			case 's':
			case 'S':
				this.commandeEnCours = Cmd.DOWN;
				break;
			case 'z':
			case 'Z':
				this.commandeEnCours = Cmd.UP;
				break;
		}

		switch (e.getKeyCode()){
			case KeyEvent.VK_SPACE:
				this.commandeEnCours = Cmd.ATTACK;
				break;
		}
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}
