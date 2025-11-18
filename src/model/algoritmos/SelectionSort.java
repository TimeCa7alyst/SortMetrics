package model.algoritmos;

import java.util.HashMap;
import java.util.Map;

public class SelectionSort {

    private long[] arr;

    public SelectionSort(int quant, long[] arr) {
        this.arr = arr;
    }

    public Map<String, Long> selectionSort() {

        int min;
        long temp;
        long swapNumber = 0;
        long compNumber = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = (i + 1); j < arr.length; j++) {
                compNumber++;
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
                swapNumber++;
            }
        }

        Map<String, Long> metrics = new HashMap<>();
        metrics.put("compNumber", compNumber);
        metrics.put("swapNumber", swapNumber);

        return metrics;
    }
}
