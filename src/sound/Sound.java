package sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.*;

/**
 * Sound is a factory class providing all sound assets and a method to play it
 */
public class Sound {

    public static float SOUND_LEVEL = -15;

    public static String CAVE_1 = "/audio/Cave1.wav";
    public static String CAVE_2 = "/audio/Cave2.wav";
    public static String CAVE_3 = "/audio/Cave3.wav";
    public static String CAVE_4 = "/audio/Cave4.wav";
    public static String CAVE_5 = "/audio/Cave5.wav";
    public static String CAVE_6 = "/audio/Cave6.wav";
    public static String CAVE_7 = "/audio/Cave7.wav";

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

    public static List<Clip> clips = new ArrayList<>();

    /**
     * PlaySound is used to play a sound using clips
     *
     * @param sound
     */
    public static void playSound(String sound) {
        try {
            
            for(Clip c: clips){
                if(!c.isRunning()){
                    c.close();
                }
            }


            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Sound.class.getResource(sound));

            AudioFormat format = inputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            
            Clip clip = (Clip)AudioSystem.getLine(info);
            clip.open(inputStream);

            FloatControl soundControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            soundControl.setValue(SOUND_LEVEL);

            clip.start();

            clips.add(clip);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, Clip> loops = new HashMap<>();

    public static void loopSound(String sound) {
        try {

            if (!loops.containsKey(sound)) {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Sound.class.getResourceAsStream(sound));
                clip.open(inputStream);

                FloatControl soundControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                soundControl.setValue(SOUND_LEVEL);

                clip.loop(Clip.LOOP_CONTINUOUSLY);

                loops.put(sound, clip);
            }

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopSound(String sound) {
        if (loops.containsKey(sound)) {
            loops.get(sound).stop();
            loops.get(sound).close();
            loops.remove(sound);
        }
    }

    private static Timer delay = new Timer();
    private static boolean isRunning = false;

    public static void playDelaySound(TimerTask task, int period) {
        isRunning = true;
        delay.schedule(task, period, period);
    }

    public static void stopDelaySound() {
        Set<String> keys = loops.keySet();
        for (String key: keys){
            loops.get(key).stop();
            loops.get(key).close();
            loops.remove(key);
        }

        if (isRunning) {
            delay.purge();
            delay.cancel();
            delay = new Timer();
            isRunning = false;
        }
    }

    /**
     * Method used to decrease the sound from the game
     */
    public static void decreaseSound() {
        float sound = SOUND_LEVEL - 5;
        if (sound > -80) {
            SOUND_LEVEL = sound;
        }
    }

    /**
     * Method used to amplify the sound from the game
     */
    public static void increaseSound() {
        float sound = SOUND_LEVEL + 5;
        if (sound < 10) {
            SOUND_LEVEL = sound;
        }
    }
}
