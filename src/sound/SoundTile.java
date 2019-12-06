package sound;

import java.applet.AudioClip;

public class SoundTile implements SoundManager{
    AudioClip actionSound;

    public SoundTile(AudioClip actionSound) {
        this.actionSound = actionSound;
    }

    @Override
    public void playActionSound() {
        actionSound.play();
    }

}
