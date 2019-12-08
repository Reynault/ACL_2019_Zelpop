package sound.soundManager;

import sound.Sound;

public class SoundTile implements SoundManager{
    private String actionSound;

    public SoundTile(String actionSound) {
        this.actionSound = actionSound;
    }

    @Override
    public void playActionSound() {
        Sound.playSound(actionSound);
    }
}
