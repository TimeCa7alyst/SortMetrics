package model.analise;

import model.algoritmos.MergeSort;
import model.util.RandomGenerator;
import java.util.Map;

public class MergeSortImpl {

    public SortMetrics piorCasoMergeSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = (quant - 1) - i;
        }

        MergeSort mergeSort = new MergeSort(quant, arr);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = mergeSort.executeMergeSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(arr, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }

    public SortMetrics melhorCasoMergeSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = i;
        }

        MergeSort mergeSort = new MergeSort(quant, arr);
        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = mergeSort.executeMergeSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(arr, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }

    public SortMetrics medioCasoMergeSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = RandomGenerator.randomGenerator();
        }

        MergeSort mergeSort = new MergeSort(quant, arr);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = mergeSort.executeMergeSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(arr, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }
}