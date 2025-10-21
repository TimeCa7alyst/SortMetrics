package util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static long randomGenerator() {
        return ThreadLocalRandom.current().nextLong() & Long.MAX_VALUE;
    }
}
