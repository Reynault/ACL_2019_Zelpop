package model.dungeon.entity;

public class ScaleStat {

    private static ScaleStat instance = new ScaleStat();
    private int level = 0 ;

    public final static ScaleStat getInstance() {
        return ScaleStat.instance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        level ++;
    }

    public double scaleStat (double defaultStat){
        return defaultStat + (defaultStat / (this.level/5));
    }

}
