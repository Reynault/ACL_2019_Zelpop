package sound;

import java.applet.AudioClip;
import java.util.Random;

public class SoundEntity implements SoundManager{
    AudioClip[] actionSounds;
    Random random;

    public SoundEntity(AudioClip[] actionSounds) {
        this.actionSounds = actionSounds;
        this.random = new Random();
    }

    @Override
    public void playActionSound() {
        actionSounds[random.nextInt(actionSounds.length)].play();
    }
}
