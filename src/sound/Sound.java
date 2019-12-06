package sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static AudioClip getMainMenu() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/menu_theme.wav")
        );
    }

    public static AudioClip getMonsterAttack() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/monsterAttack.wav")
        );
    }

    static AudioClip GOBLIN_ATTACK = Applet.newAudioClip(
            Sound.class.getResource("/audio/goblinAttack.wav")
    );

    static AudioClip AMBIANCE_ONE = Applet.newAudioClip(
            Sound.class.getResource("/audio/ambiance_1.wav")
    );

    static AudioClip MENU_BUTTON = Applet.newAudioClip(
            Sound.class.getResource("/audio/buttonMenu.wav")
    );

    static AudioClip HERO_ATTACK = Applet.newAudioClip(
            Sound.class.getResource("/audio/heroAttack.wav")
    );

    static AudioClip LOW_LIFE = Applet.newAudioClip(
            Sound.class.getResource("/audio/lowLife.wav")
    );

    static AudioClip STAIRS = Applet.newAudioClip(
            Sound.class.getResource("/audio/stairs.wav")
    );
}
