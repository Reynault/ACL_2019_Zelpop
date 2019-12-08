package sound;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Sound is a factory class providing all sound assets and a method to play it
 */
public class Sound {

    public static float SOUND_LEVEL = -15;

    public static String MONSTER_ATTACK = "/audio/monsterAttack.wav";
    public static String GOBLIN_ATTACK = "/audio/goblinAttack.wav";
    public static String HERO_ATTACK_1 = "/audio/heroAttack1.wav";
    public static String HERO_ATTACK_2 = "/audio/heroAttack2.wav";
    public static String HERO_ATTACK_3 = "/audio/heroAttack3.wav";
    public static String GHOST_ATTACK = "/audio/ghostAttack.wav";
    public static String MENU_BUTTON = "/audio/buttonMenu.wav";
    public static String LOW_LIFE = "/audio/lowLife.wav";
    public static String STAIRS_SOUND = "/audio/stairs.wav";
    public static String TRAP_SOUND = "/audio/trap.wav";
    public static String TELEPORT_SOUND = "/audio/teleport.wav";
    public static String TREASURE_SOUND = "/audio/gold.wav";
    public static String BREAK_SOUND = "/audio/breakWall.wav";

    /**
     * PlaySound is used to play a sound using clips
     *
     * @param sound
     */
    public static void playSound(String sound) {
        try {

            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Sound.class.getResourceAsStream(sound));
            clip.open(inputStream);

            FloatControl soundControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            soundControl.setValue(SOUND_LEVEL);

            clip.start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to decrease the sound from the game
     */
    public static void decreaseSound(){
        float sound = SOUND_LEVEL - 5;
        if(sound > -80) {
            SOUND_LEVEL = sound;
        }
    }

    /**
     * Method used to amplify the sound from the game
     */
    public static void increaseSound(){
        float sound = SOUND_LEVEL + 5;
        if(sound < 10) {
            SOUND_LEVEL = sound;
        }
    }
}
