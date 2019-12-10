package model.global;

public class GlobalGrow {
    public static double growRateHp(){
        return 1.01;
    }
    public static double growRateDef(){
        return 1.006;
    }
    public static double growRatevit(){
        return 1.005;
    }
    public static double growRateAtk(){
        return 1.004;
    }

    public static double startingPriceHp(){
        return 100;
    }
    public static double startingPriceDef(){
        return 100;
    }
    public static double startingPriceVit(){
        return 100;
    }
    public static double startingPriceAtk(){
        return 100;
    }

    public static double priceGrow(double currentStat, double growOfStat, double startingPriceOfStat){
        return startingPriceOfStat * Math.pow(growOfStat, currentStat);
    }

    public static double statGrow(double currentStat){
        return currentStat * 1.05;
    }
}
