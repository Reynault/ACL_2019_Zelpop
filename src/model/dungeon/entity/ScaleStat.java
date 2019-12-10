package model.dungeon.entity;

public class ScaleStat {

    private static ScaleStat instance = new ScaleStat();
    private double level = 0 ;

    public final static ScaleStat getInstance() {
        return ScaleStat.instance;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel() {
        level ++;
    }

    public void resetLevel(){level = 0;}

    public double scaleStat (double defaultStat){
        return defaultStat + (defaultStat * (this.level/5));
    }

}
