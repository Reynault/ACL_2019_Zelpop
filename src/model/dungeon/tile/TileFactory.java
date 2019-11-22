package model.dungeon.tile;

public class TileFactory {

    private static int DEFAULT_HP = 1;

    /**
     * Generate a tile
     */
    public static Tile getEmptyTile() {
        return new Tile(DEFAULT_HP);
    }

    public static Tile getTrap(int damage){
        return new Trap(DEFAULT_HP, new Tile(DEFAULT_HP), damage);
    }

    public static Tile getWall(){
        return new Wall(new Tile(DEFAULT_HP));
    }

    public static Tile getTreasure(int gold){
        return new Treasure(DEFAULT_HP, new Tile(DEFAULT_HP), gold);
    }

    public static Tile getStairs(){return new Stairs(DEFAULT_HP, new Tile(DEFAULT_HP));}

    public static Tile getTeleport() {return new Teleport(DEFAULT_HP, new Tile(DEFAULT_HP));}

    public static Tile getBreakableWall() {return new BreakableWall(DEFAULT_HP, new Tile(DEFAULT_HP));}
}
