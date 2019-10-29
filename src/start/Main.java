package start;

import model.ZelpopPainter;
import engine.GameEngineGraphical;
import model.ZelpopController;
import model.ZelpopGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		ZelpopGame game = new ZelpopGame("helpFilePacman.txt");
		ZelpopPainter painter = new ZelpopPainter(game);
		ZelpopController controller = new ZelpopController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
