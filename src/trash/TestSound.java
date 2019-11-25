package trash;

import engine.GameEngineGraphical;
import model.ZelpopController;
import model.ZelpopGame;
import model.ZelpopPainter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TestSound {

    // https://convertisseur-youtube-mp3.net/     --> Youtube MP3

    // https://www.online-convert.com/fr/resultat/9dc02020-97a2-4624-b1ac-5f7da373a64e  --> MP3 WAV

    // https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java  --> Code
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            TestSound.class.getResourceAsStream("/music/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException{
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    TestSound.class.getResourceAsStream("/music/main_menu.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        // creation du jeu particulier et de son afficheur
        ZelpopGame game = new ZelpopGame();
        ZelpopPainter painter = new ZelpopPainter(game);
        ZelpopController controller = new ZelpopController();

        // classe qui lance le moteur de jeu generique
        GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        engine.run();
    }
}
