package model.dungeon.scoring;

import model.dungeon.entity.Entity;
import model.dungeon.tile.Tile;

public class Scoring {

    private int multiplier ;

    public int killMonster(Entity entity){
        return 1;
    }

    public int leaveMaze(){
        return 0;
    }

    public int findChest(Tile tile){
        return 1;
    }
}
