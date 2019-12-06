package sound;

import java.applet.AudioClip;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SoundDungeon implements SoundManager{
    Timer timer;
    AudioClip[] ambianceSound;
    Random random;

    public SoundDungeon(AudioClip[] ambianceSound) {
        this.ambianceSound = ambianceSound;
        this.random = new Random();
    }

    @Override
    public void playActionSound() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ambianceSound[random.nextInt(ambianceSound.length)].play();
            }
        };

    }

    @Override
    public void stopActionSound() {

    }

    @Override
    public void loopActionSound() {

    }
}
