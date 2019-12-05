package model.dungeon.entity;

public class StatFactory {
    public static Stats getHeroStats(){
        return new Stats(100, 10, 1, 2);
    }
    public static Stats getmonsterStats(){
        return new Stats(25, 5, 1, 1);
    }
    public static Stats getGhostStats(){
        return new Stats(15, 25, 0, 10);
    }
    public static Stats getGoblinStats(){
        return new Stats(75, 5, 3, 1);
    }
}
