package sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {



    public static AudioClip getMonsterAttack() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/monsterAttack.wav")
        );
    }

    public static AudioClip getGoblinAttack() {
        return Applet.newAudioClip(
            Sound.class.getResource("/audio/goblinAttack.wav")
        );
    }


    public static AudioClip getHeroAttack1(){
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/heroAttack1.wav")
        );
    }


    public static AudioClip getHeroAttack2(){
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/heroAttack2.wav")
        );
    }


    public static AudioClip getHeroAttack3(){
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/heroAttack3.wav")
        );
    }

    public static AudioClip getGhostAttack(){
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/ghostAttack.wav")
        );
    }

    public static AudioClip getMenuButton(){
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/buttonMenu.wav")
        );
    }


    public static AudioClip getLowLife(){
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/lowLife.wav")
        );
    }

    public static AudioClip getStairs(){
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/stairs.wav")
        );
    }

    public static AudioClip getTrap() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/trap.wav")
        );
    }

    public static AudioClip getTeleport() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/teleport.wav")
        );
    }

    public static AudioClip getTreasure() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/gold.wav")
        );
    }

    public static AudioClip getAmbiance1(){
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/gold.wav")
        );
    }

    public static AudioClip getAmbiance2() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/gold.wav")
        );
    }

    public static AudioClip getAmbiance3() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/gold.wav")
        );
    }

    public static AudioClip getAmbiance4() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/gold.wav")
        );
    }

    public static AudioClip getBreak() {
        return Applet.newAudioClip(
                Sound.class.getResource("/audio/breakWall.wav")
        );
    }
}
