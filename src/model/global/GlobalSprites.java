package model.global;

public class GlobalSprites {

    /**
     * Getter for a sprite 8x8 pixels
     * @return the length of a sprite 8x8 pixels
     */
    public static int get8Sprite(){
        return 8;
    }

    /**
     * Getter for a sprite 16x16 pixels
     * @return the length of a sprite 8x8 pixels
     */
    public static int get16Sprite(){
        return 16;
    }

    public static int getScaling(){return 4;}

    public static int getMiniScaling(){return 1;}

    public static int getAnimationDelay(){return 50;}

    public static long getHowToAnimationDelay() {
        return 250;
    }
}
