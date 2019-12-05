package sound;

import trash.TestSound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    private static AudioClip MONSTER_ATTACK = Applet.newAudioClip(
            TestSound.class.getResource("/audio/monsterAttack.wav")
    );

    private static AudioClip GOBLIN_ATTACK = Applet.newAudioClip(
            TestSound.class.getResource("/audio/goblinAttack.wav")
    );

    private static AudioClip AMBIANCE_ONE = Applet.newAudioClip(
            TestSound.class.getResource("/audio/ambiance_1.wav")
    );

    private static AudioClip MENU_BUTTON = Applet.newAudioClip(
            TestSound.class.getResource("/audio/buttonMenu.wav")
    );

    private static AudioClip HERO_ATTACK = Applet.newAudioClip(
            TestSound.class.getResource("/audio/heroAttack.wav")
    );

    private static AudioClip LOW_LIFE = Applet.newAudioClip(
            TestSound.class.getResource("/audio/lowLife.wav")
    );

    private static AudioClip STAIRS = Applet.newAudioClip(
            TestSound.class.getResource("/audio/stairs.wav")
    );

    public static AudioClip getMonsterAttack() {
        return MONSTER_ATTACK;
    }

    public static AudioClip getGoblinAttack() {
        return GOBLIN_ATTACK;
    }

    public static AudioClip getAmbianceOne() {
        return AMBIANCE_ONE;
    }

    public static AudioClip getMenuButton() {
        return MENU_BUTTON;
    }

    public static AudioClip getHeroAttack() {
        return HERO_ATTACK;
    }

    public static AudioClip getLowLife() {
        return LOW_LIFE;
    }

    public static AudioClip getSTAIRS() {
        return STAIRS;
    }
}
