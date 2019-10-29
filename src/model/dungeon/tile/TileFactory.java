package model.dungeon.tile;

public class TileFactory {

    /**
     * Default constructor
     */
    public TileFactory() {
    }

    /**
     * Generate a tile
     */
    public Tile generateTile() {
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
}
