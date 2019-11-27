package model.global;

public class GlobalGrow {
    public static double growRateHp(){
        return 1.01;
    }
    public static double growRateDef(){
        return 1.01;
    }
    public static double growRatevit(){
        return 1.01;
    }
    public static double growRateAtk(){
        return 1.01;
    }


    public static double startingPriceHp(){
        return 1;
    }
    public static double startingPriceDef(){
        return 1;
    }
    public static double startingPriceVit(){
        return 1;
    }
    public static double startingPriceAtk(){
        return 1;
    }

    public static double priceGrow(double currentStat, double growOfStat, double startingPriceOfStat){
        return startingPriceOfStat * Math.pow(growOfStat, currentStat);
    }

    public static double statGrow(double currentStat){
        return currentStat * 1.01;
    }
}
