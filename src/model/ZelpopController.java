package model;

import java.awt.event.KeyEvent;

import engine.GameController;
import model.global.Cmd;


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

	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	@Override
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
			case 'r':
			case 'R':
				this.commandeEnCours = Cmd.RESTART;
				break;
            case 'o':
            case 'O':
                this.commandeEnCours = Cmd.SAVE;
                break;
			case 'e':
			case 'E':
				this.commandeEnCours = Cmd.LEAVE_LEVEL;
				break;
            case 'b':
            case 'B':
                this.commandeEnCours = Cmd.INCREASE_SOUND;
                break;
            case 'n':
            case 'N':
                this.commandeEnCours = Cmd.DECREASE_SOUND;
                break;
			case 'w':
			case 'W':
				this.commandeEnCours = Cmd.RANGED;
				break;
        }

		switch (e.getKeyCode()){
			case KeyEvent.VK_SPACE:
				this.commandeEnCours = Cmd.ATTACK;
				break;
			case KeyEvent.VK_ESCAPE:
				this.commandeEnCours = Cmd.EXIT_GAME;
				break;
		}
	}

	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	@Override
	public void keyReleased(KeyEvent e) {}

	/**
	 * ne fait rien
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

}
