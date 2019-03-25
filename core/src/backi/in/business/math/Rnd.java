package backi.in.business.math;

import java.util.Random;

public class Rnd {

    private static Random random = new Random();

    public static float randomFloat(float minVal, float maxVal){
        return random.nextFloat()*(maxVal-minVal) + minVal;
    }
    public static int randomInt(int minVal, int maxVal){
        return random.nextInt(maxVal-minVal+1) + minVal;
    }
}
