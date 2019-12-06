package sound;

import java.applet.AudioClip;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SoundDungeon implements SoundManager{

    Timer timer;
    AudioClip[] ambianceSound;
    Random random;

    static int TIME_BETWEEN_SOUND = 500;

    public SoundDungeon(AudioClip[] ambianceSound) {
        this.ambianceSound = ambianceSound;
        this.random = new Random();
    }

    @Override
    public void playActionSound() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(random.nextInt(TIME_BETWEEN_SOUND));
                    ambianceSound[random.nextInt(ambianceSound.length)].play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 0,0);

    }
}
