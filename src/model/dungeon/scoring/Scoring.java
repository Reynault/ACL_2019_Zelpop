package model.dungeon.scoring;

import model.dungeon.entity.Entity;
import model.dungeon.tile.Tile;

public class Scoring {
    private int score;
    private int multiplier;

    Scoring(int multiplier, int score) {
        this.score = score;
        this.multiplier = multiplier;
    }


    public int killMonster(Entity entity) {
        return 1;
    }

    public int leaveMaze() {
        return score*multiplier;
    }

    public int findChest(Tile tile) {
        return tile.getGold();
    }
}
