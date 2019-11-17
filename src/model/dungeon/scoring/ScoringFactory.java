package model.dungeon.scoring;

public class ScoringFactory {
    public static Scoring getSimpleScoring(int multiplier, int score){
        return new Scoring(multiplier, score);
    }

    public static Scoring getLifeScoring(int multiplier, int score){
        return new ScoringLife(multiplier, score, new Scoring(multiplier, score));
    }
}
