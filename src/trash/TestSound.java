package trash;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.util.Scanner;

public class TestSound {

    // https://convertisseur-youtube-mp3.net/     --> Youtube MP3

    // https://www.online-convert.com/fr/resultat/9dc02020-97a2-4624-b1ac-5f7da373a64e  --> MP3 WAV

    // https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java  --> Code
    public static void main(String[] args) throws InterruptedException {
        try {


//            AudioClip clip = Applet.newAudioClip(
//                    TestSound.class.getResource("/audio/gold.wav")
//            );

            Scanner sc = new Scanner(System.in);

            int request = 0;

            while (request != 5) {

                System.out.println("COMMANDE ?");

                request = sc.nextInt();

                switch (request) {
                    case 1:
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                                TestSound.class.getResourceAsStream("/audio/gold.wav"));
                        clip.open(inputStream);
                        System.out.println("START");
                        clip.start();
                        break;
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

/*
        // creation du jeu particulier et de son afficheur
        ZelpopGame game = new ZelpopGame();
        ZelpopPainter painter = new ZelpopPainter(game);
        ZelpopController controller = new ZelpopController();

        // classe qui lance le moteur de jeu generique
        GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        engine.run();
*/

    }
}
