package sound;

import java.applet.AudioClip;

public class SoundManagerFactory {
    public static SoundManager getMonsterSounds(){
        AudioClip[] sounds = {Sound.getMonsterAttack()};
        return new SoundEntity(sounds);
    }

    public static SoundManager getGoblinSounds(){
        AudioClip[] sounds = {Sound.getGoblinAttack()};
        return new SoundEntity(sounds);
    }

    public static SoundManager getGhostSounds(){
        AudioClip[] sounds = {Sound.getGhostAttack()};
        return new SoundEntity(sounds);
    }

    public static SoundManager getHeroSounds(){
        AudioClip[] sounds = {
                Sound.getHeroAttack1(),
                Sound.getHeroAttack2(),
                Sound.getHeroAttack3()
        };
        return new SoundEntity(sounds);
    }

    public static SoundManager getTrapSounds(){
        return new SoundTile(Sound.getTrap());
    }

    public static SoundManager getTeleportSounds(){
        return new SoundTile(Sound.getTeleport());
    }

    public static SoundManager getStairsSounds(){
        return new SoundTile(Sound.getStairs());
    }

    public static SoundManager getTreasureSounds(){
        return new SoundTile(Sound.getTreasure());
    }

    public static SoundManager getMazeSounds(){
        AudioClip[] sounds = {
                Sound.getAmbiance1(),
                Sound.getAmbiance2(),
                Sound.getAmbiance3(),
                Sound.getAmbiance4(),
        };
        return  new SoundMaze(sounds);
    }
}
