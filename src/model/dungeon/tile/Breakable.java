package model.dungeon.tile;

import java.io.Serializable;

/**
 * Abstract class that represent a breakable object
 */
public abstract class Breakable implements Serializable {
    private int hp;
    private boolean destroyed;

    public Breakable(int hp) {
        this.hp = hp;
        this.destroyed = false;
    }

    public abstract boolean isBreakable();

    public void takeDamage(int damage){
        if(!destroyed) {
            hp -= damage;
            if (hp <= 0) {
                destroyed = true;
            }
        }
    }

    public boolean isDestroyed(){
        return hp <= 0;
    }
}
