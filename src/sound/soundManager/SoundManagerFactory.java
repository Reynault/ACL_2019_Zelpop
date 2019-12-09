package sound.soundManager;

import sound.Sound;

public class SoundManagerFactory {
    public static SoundManager getHeroSounds(){
        String[] sounds = {
                Sound.HERO_ATTACK_1,
                Sound.HERO_ATTACK_2,
                Sound.HERO_ATTACK_3,
        };
        return new SoundEntity(sounds);
    }

    public static SoundManager getGhostSounds(){
        String[] sounds = {
                Sound.GHOST_ATTACK
        };
        return new SoundEntity(sounds);
    }

    public static SoundManager getGoblinSounds(){
        String[] sounds = {
                Sound.GOBLIN_ATTACK
        };
        return new SoundEntity(sounds);
    }

    public static SoundManager getMonsterSounds() {
        String[] sounds = {
                Sound.MONSTER_ATTACK
        };
        return new SoundEntity(sounds);
    }

    public static SoundManager getProjectileSound(){
        String[] sounds = {
                Sound.HERO_ATTACK_1
        };
        return new SoundEntity(sounds);
    }

    public static SoundManager getTrapSounds(){
        return new SoundTile(Sound.TRAP_SOUND);
    }

    public static SoundManager getTeleportSounds(){
        return new SoundTile(Sound.TELEPORT_SOUND);
    }

    public static SoundManager getTreasureSounds(){
        return new SoundTile(Sound.TREASURE_SOUND);
    }
}
