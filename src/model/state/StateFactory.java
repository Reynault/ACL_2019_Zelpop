package model.state;

import model.dungeon.Dungeon;
import sound.Sound;

public class StateFactory {

    public static GameState getInGame(Dungeon dungeon){
        Sound.stopDelaySound();
        return new InGame(dungeon);
    }

    public static GameState getGameOver(Dungeon dungeon) {
        Sound.stopDelaySound();
        return new GameOver(dungeon);
    }

    public static GameState getMenu(){
        Sound.stopDelaySound();
        return new Menu();
    }

    public static GameState getHowToPlay() { return new HowToPlay(); }

}