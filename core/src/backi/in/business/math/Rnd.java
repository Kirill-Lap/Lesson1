package backi.in.business.math;

import java.util.Random;

public class Rnd {

    private static Random random = new Random();

    public static float randomFloat(float minVal, float maxVal){
        return random.nextFloat()*(maxVal-minVal) + minVal;
    }
}
