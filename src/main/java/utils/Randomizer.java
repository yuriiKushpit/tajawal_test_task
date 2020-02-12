package utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public static String getRandomValueFromArray(String[] array) {
        int  rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static int getRandomNumber(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
