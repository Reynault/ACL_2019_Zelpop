package model.state;

import model.dungeon.Dungeon;

public class StateFactory {

    public static GameState getInGame(Dungeon dungeon){
        return new InGame(dungeon);
    }

    public static GameState getGameOver(Dungeon dungeon) {
        return new GameOver(dungeon);
    }

    public static GameState getMenu(){
        return new Menu();
    }

    public static GameState getHowToPlay() { return new HowToPlay(); }

}