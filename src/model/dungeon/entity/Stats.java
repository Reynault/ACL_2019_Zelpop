package model.dungeon.entity;

import model.global.GlobalGrow;

import java.io.Serializable;

public class Stats implements Serializable {
    private double maxHp;
    private double currentHp;
    private double damage;
    private double defence;
    private double vitality;

    private double maxHpCostToUpgrade = GlobalGrow.startingPriceHp();
    private double damageCostToUpgrade = GlobalGrow.startingPriceAtk();
    private double defenceCostToUpgrade = GlobalGrow.startingPriceDef();
    private double vitalityCostToUpgrade = GlobalGrow.startingPriceVit();

    public Stats(double maxHp, double damage, double defence, double vitality) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.damage = damage;
        this.defence = defence;
        this.vitality = vitality;
    }

    public void setCurrentHp(double currentHp) {
        this.currentHp = currentHp;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public double getCurrentHp() {
        return currentHp;
    }

    public double getDamage() {
        return damage;
    }

    public double getDefence() {
        return defence;
    }

    public double getVitality() {
        return vitality;
    }

    public double getMaxHpCostToUpgrade() {
        return maxHpCostToUpgrade;
    }

    public double getDamageCostToUpgrade() {
        return damageCostToUpgrade;
    }

    public double getDefenceCostToUpgrade() {
        return defenceCostToUpgrade;
    }

    public double getVitalityCostToUpgrade() {
        return vitalityCostToUpgrade;
    }

    public void setDmg(int dmg) {
        damage = dmg;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
