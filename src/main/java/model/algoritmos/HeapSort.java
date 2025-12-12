package model.algoritmos;

import java.util.HashMap;
import java.util.Map;

public class HeapSort {

    public long[] arr;
    public long swapNumber = 0;
    public long compNumber = 0;

    public HeapSort(int quant, long[] arr) {
        this.arr = arr;
    }

    void heapify(long[] arr, int n, int i) {

        int largest = i;

        int l = 2 * i + 1;

        int r = 2 * i + 2;

        if (l < n) {
            compNumber++;
            if (arr[l] > arr[largest]) {
                largest = l;
            }
        }

        if (r < n) {
            compNumber++;
            if (arr[r] > arr[largest]) {
                largest = r;
            }
        }

        if (largest != i) {
            long temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            swapNumber++;

            heapify(arr, n, largest);
        }
    }

    public Map<String, Long> heapSort(long[] arr) {
        compNumber = 0;
        swapNumber = 0;
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {

            long temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swapNumber++;

            heapify(arr, i, 0);
        }

        Map<String, Long> metrics = new HashMap<>();
        metrics.put("compNumber", compNumber);
        metrics.put("swapNumber", swapNumber);

        return metrics;
    }
}
