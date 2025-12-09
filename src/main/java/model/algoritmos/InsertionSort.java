package model.algoritmos;

import java.util.HashMap;
import java.util.Map;

public class InsertionSort {

    public long[] arr;

    public InsertionSort(int quant, long[] arr) {
        this.arr = arr;
    }

    public Map<String, Long> insertionSort() {
        long temp;
        long swapNumber = 0;
        long compNumber = 0;

        for (int i = 1; i < arr.length; i++) {

            temp = arr[i];
            int j = (i - 1);
            while (j >= 0 && arr[j] > temp) {
                compNumber++;
                arr[j + 1] = arr[j];
                swapNumber++;
                j--;
            }
            arr[j + 1] = temp;
        }

        Map<String, Long> metrics = new HashMap<>();
        metrics.put("compNumber", compNumber);
        metrics.put("swapNumber", swapNumber);

        return metrics;
    }
}