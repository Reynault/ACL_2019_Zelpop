package sound;

import trash.TestSound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    static AudioClip MONSTER_ATTACK = Applet.newAudioClip(
            TestSound.class.getResource("/audio/monsterAttack.wav")
    );

    static AudioClip GOBLIN_ATTACK = Applet.newAudioClip(
            TestSound.class.getResource("/audio/goblinAttack.wav")
    );

    static AudioClip AMBIANCE_ONE = Applet.newAudioClip(
            TestSound.class.getResource("/audio/ambiance_1.wav")
    );

    static AudioClip MENU_BUTTON = Applet.newAudioClip(
            TestSound.class.getResource("/audio/buttonMenu.wav")
    );

    static AudioClip HERO_ATTACK = Applet.newAudioClip(
            TestSound.class.getResource("/audio/heroAttack.wav")
    );

    static AudioClip LOW_LIFE = Applet.newAudioClip(
            TestSound.class.getResource("/audio/lowLife.wav")
    );

    static AudioClip STAIRS = Applet.newAudioClip(
            TestSound.class.getResource("/audio/stairs.wav")
    );
}
