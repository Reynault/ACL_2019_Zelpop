package model.dungeon.entity;

public class StatFactory {

    public static double HERO_DAMAGE_CALLBACK = 10;

    // Hero
    public static Stats getHeroStats(){
        return new Stats(100, 1000, 1, 2);
    }
    public static Stats getProjectileStats(){
        return new Stats(1,1000, 0, 0);
    }


    // Monsters
    public static Stats getmonsterStats(){
        return new Stats( scaleStat(25), scaleStat(  5), scaleStat( 1), scaleStat( 1));
    }
    public static Stats getGhostStats( ){
        return new Stats(scaleStat(  15), scaleStat(  15), 0, scaleStat( 5));
    }
    public static Stats getGoblinStats( ){
        return new Stats(scaleStat( 50), scaleStat( 5), scaleStat( 3), scaleStat( 1));
    }

    // Scaling stats
    public static double scaleStat (double defaultStat){
        return ScaleStat.getInstance().scaleStat(defaultStat);
    }
}
