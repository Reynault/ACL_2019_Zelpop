package sound.soundManager;

import sound.Sound;

import java.util.Random;

public class SoundEntity implements SoundManager {
    private String[] attackSounds;
    private Random random;

    public SoundEntity(String[] attackSounds) {
        this.attackSounds = attackSounds;
        random = new Random();
    }

    @Override
    public void playActionSound() {
        Sound.playSound(attackSounds[random.nextInt(attackSounds.length)]);
    }
}
