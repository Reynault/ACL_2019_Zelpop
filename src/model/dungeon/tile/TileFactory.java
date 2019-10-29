package model.dungeon.tile;

public abstract class TileFactory {

    /**
     * Generate a tile
     */
    public static Tile generateTile() {
        return new Tile();
    }


}
