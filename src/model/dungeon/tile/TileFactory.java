package model.dungeon.tile;

public class TileFactory {

    /**
     * Generate a tile
     */
    public static Tile generateTile() {
        return new Tile();
    }

    public static Tile getTrap(){
        return new Trap(new Tile());
    }

    public static Tile getWall(){
        return new Wall(new Tile());
    }

    public static Tile getTreasure(){
        return new Treasure(new Tile());
    }

    public static Tile getEmptyTile(){
        return new Tile();
    }

    public static Tile getStairs(){return new Stairs(new Tile());}
}
