package engine;

import model.global.Cmd;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	private static double FPS_LIMITER = 60;
	private double start = 0, diff, wait = (1 / FPS_LIMITER);

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		// init
		this.gui.paint();

		// boucle de game
		while (!this.game.isFinished()) {
			// met en attente
			lockFPS();
			// demande controle utilisateur
			Cmd c = this.gameController.getCommand();
			if (c != Cmd.IDLE) {
				// fait evoluer le game
				this.game.evolve(c);
				// affiche le game
				this.gui.paint();
			}
			this.gameController.resetCommand();
		}
	}

	private void lockFPS() throws InterruptedException{
		diff = (System.currentTimeMillis() - start)/1000;
		if (diff < wait) {
			Thread.sleep((long) ((wait - diff)*1000));
		}
		start = System.currentTimeMillis();
	}

}
