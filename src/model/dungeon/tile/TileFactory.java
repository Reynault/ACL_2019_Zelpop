package model.dungeon.tile;

public class TileFactory {

    /**
     * Generate a tile
     */
    public static Tile generateTile() {
        return new Tile();
    }

    public static Tile getTrap(int damage){
        return new Trap(new Tile(), damage);
    }

    public static Tile getWall(){
        return new Wall(new Tile());
    }

    public static Tile getTreasure(int gold){
        return new Treasure(new Tile(), gold);
    }

    public static Tile getEmptyTile(){
        return new Tile();
    }

    public static Tile getStairs(){return new Stairs(new Tile());}
}
