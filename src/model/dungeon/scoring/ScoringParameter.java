package model.dungeon.scoring;

import model.dungeon.entity.Entity;
import model.dungeon.tile.Tile;

public abstract class ScoringParameter extends Scoring {
    Scoring decore;

    ScoringParameter(int multiplier, int score, Scoring decore) {
        super(multiplier, score);
        this.decore = decore;
    }


    public abstract double killMonster(Entity entity);

    public abstract int leaveMaze();

    public abstract int findChest(Tile tile);

}
