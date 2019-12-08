package model.dungeon.entity;

public class StatFactory {
    public static Stats getHeroStats(){
        return new Stats(100, 1000, 1, 2);
    }
    public static Stats getmonsterStats(){
        return new Stats(25, 5, 1, 1);
    }
    public static Stats getGhostStats(){
        return new Stats(15, 15, 0, 5);
    }
    public static Stats getGoblinStats(){
        return new Stats(50, 5, 3, 1);
    }
}
