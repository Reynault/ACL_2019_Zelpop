package sound;

import java.applet.AudioClip;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SoundMaze implements SoundManager{

    Timer timer;
    AudioClip[] ambianceSound;
    Random random;

    static int TIME_BETWEEN_SOUND = 5000;

    SoundMaze(AudioClip[] ambianceSound) {
        this.ambianceSound = ambianceSound;
        this.random = new Random();
        this.timer = new Timer();
    }

    @Override
    public void playActionSound() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(random.nextInt(TIME_BETWEEN_SOUND));
                    System.out.println("SON ??");
                    ambianceSound[random.nextInt(ambianceSound.length)].play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 0,1);

    }

    @Override
    public void stopActionSound() {
        timer.cancel();
        for(AudioClip a: ambianceSound){
            a.stop();
        }
    }
}
