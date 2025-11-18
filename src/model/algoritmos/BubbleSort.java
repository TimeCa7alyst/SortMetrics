package model.algoritmos;


import model.analise.SortMetrics;

import java.util.HashMap;
import java.util.Map;

public class BubbleSort {

    private long[] arr;

    public BubbleSort(int quant, long[] arr) {
        this.arr = arr;
    }

    public Map<String, Long> bubbleSort() {
        long temp;
        long swapNumber = 0;
        long compNumber = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - (i + 1); j++) {
                compNumber++;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapNumber++;
                }
            }
        }

        Map<String, Long> metrics = new HashMap<>();
        metrics.put("compNumber", compNumber);
        metrics.put("swapNumber", swapNumber);

        return metrics;
    }
}
