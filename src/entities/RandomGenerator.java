package entities;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static long randomGenerator(int min, int max) {
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }
}
