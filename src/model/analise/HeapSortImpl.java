package model.analise;

import model.algoritmos.HeapSort;
import model.util.RandomGenerator;

import java.util.HashMap;
import java.util.Map;

public class HeapSortImpl {

    public SortMetrics piorCasoHeapSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = (quant - 1) - i;
        }

        HeapSort heapSort = new HeapSort(quant, arr);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = heapSort.heapSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long swapNumber = metrics.get("swapNumber");
        long compoNumber = metrics.get("compNumber");

        return new SortMetrics(arr, metrics.get("swapNumber"), metrics.get("compNumber"), totalMs, false);
    }

    public SortMetrics melhorCasoHeapSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = i;
        }

        HeapSort heapSort = new HeapSort(quant, arr);
        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = heapSort.heapSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long swapNumber = metrics.get("swapNumber");
        long compoNumber = metrics.get("compNumber");

        return new SortMetrics(arr, metrics.get("swapNumber"), metrics.get("compNumber"), totalMs, false);
    }

    public SortMetrics medioCasoHeapSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = RandomGenerator.randomGenerator();
        }

        HeapSort heapSort = new HeapSort(quant, arr);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = heapSort.heapSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long swapNumber = metrics.get("swapNumber");
        long compoNumber = metrics.get("compNumber");

        return new SortMetrics(arr, metrics.get("swapNumber"), metrics.get("compNumber"), totalMs, false);
    }
}
