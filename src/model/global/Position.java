package model.global;

public class Position {
    private final int x;
    private final int y;
    private final GlobalDirection globalDirection;

    public Position(int x, int y, GlobalDirection globalDirection) {
        this.x = x;
        this.y = y;
        this.globalDirection = globalDirection;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GlobalDirection getGlobalDirection() {
        return globalDirection;
    }
}
