package model.util;

public class CaseGenerator {

    public static long[] genBestCase(int quant) {
        long[] arr = new long[quant];
        for (int i = 0; i < quant; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static long[] genAvgCase(int quant) {
        long[] arr = new long[quant];
        for (int i = 0; i < quant; i++) {
            arr[i] = model.util.RandomGenerator.randomGenerator();
        }
        return arr;
    }

    public static long[] genWorstCase(int quant) {
        long[] arr = new long[quant];
        for (int i = 0; i < quant; i++) {
            arr[i] = (quant - 1) - i;
        }
        return arr;
    }
}
