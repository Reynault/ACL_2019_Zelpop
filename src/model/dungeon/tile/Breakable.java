package model.dungeon.tile;

/**
 * Abstract class that represent a breakable object
 */
public abstract class Breakable {
    private int hp;
    public abstract boolean isBreakable();
    public void takeDamage(int damage){
        hp -= damage;
    }
    public boolean isDestroyed(){
        return hp <= 0;
    }
}
