package model.global;

import java.io.Serializable;

public class Position implements Serializable {
    private final int x;
    private final int y;
    private final Cmd cmd;

    public Position(int x, int y, Cmd cmd) {
        this.x = x;
        this.y = y;
        this.cmd = cmd;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cmd getCmd() {
        return cmd;
    }
}
