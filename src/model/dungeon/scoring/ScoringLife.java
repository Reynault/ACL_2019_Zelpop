package model.dungeon.scoring;

import model.dungeon.entity.Entity;
import model.dungeon.tile.Tile;

public class ScoringLife extends ScoringParameter{

    ScoringLife(int multiplier, int score, Scoring decore) {
        super(multiplier, score, decore);
    }

    @Override
    public double killMonster(Entity entity) {
        return entity.getMaxHp();
    }

    @Override
    public int leaveMaze() {
        return decore.leaveMaze();
    }

    @Override
    public int findChest(Tile tile) {
        return decore.findChest(tile);
    }
}
