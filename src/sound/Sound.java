package sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static AudioClip MONSTER_ATTACK  = Applet.newAudioClip(
            Sound.class.getResource("/audio/monsterAttack.wav")
        );

    public static AudioClip GOBLIN_ATTACK =  Applet.newAudioClip(
            Sound.class.getResource("/audio/goblinAttack.wav")
        );

    public static AudioClip HERO_ATTACK_1 =  Applet.newAudioClip(
            Sound.class.getResource("/audio/heroAttack1.wav")
    );

    public static AudioClip HERO_ATTACK_2 =  Applet.newAudioClip(
            Sound.class.getResource("/audio/heroAttack2.wav")
    );

    public static AudioClip HERO_ATTACK_3 =  Applet.newAudioClip(
            Sound.class.getResource("/audio/heroAttack3.wav")
    );

    public static AudioClip GHOST_ATTACK =  Applet.newAudioClip(
            Sound.class.getResource("/audio/ghostAttack.wav")
    );

    public static AudioClip MENU_BUTTON =  Applet.newAudioClip(
            Sound.class.getResource("/audio/buttonMenu.wav")
    );

    public static AudioClip LOW_LIFE =  Applet.newAudioClip(
            Sound.class.getResource("/audio/lowLife.wav")
    );

    public static AudioClip STAIRS_SOUND =  Applet.newAudioClip(
            Sound.class.getResource("/audio/stairs.wav")
    );

    public static AudioClip TRAP_SOUND =  Applet.newAudioClip(
            Sound.class.getResource("/audio/trap.wav")
    );

    public static AudioClip TELEPORT_SOUND =  Applet.newAudioClip(
            Sound.class.getResource("/audio/teleport.wav")
    );

    public static AudioClip TREASURE_SOUND =  Applet.newAudioClip(
            Sound.class.getResource("/audio/gold.wav")
    );

    public static AudioClip BREAK_SOUND =  Applet.newAudioClip(
            Sound.class.getResource("/audio/breakWall.wav")
    );
}
