package model.analise;

import model.algoritmos.QuickSort;
import model.util.RandomGenerator;

import java.util.Map;

public class QuickSortImpl {

    public SortMetrics piorCasoQuickSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = (quant - 1) - i;
        }

        QuickSort quickSort = new QuickSort(quant, arr);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = quickSort.quickSort(arr, 0, (quant - 1));
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long swapNumber = metrics.get("swapNumber");
        long compoNumber = metrics.get("compNumber");

        return new SortMetrics(arr, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, false);
    }

    public SortMetrics melhorCasoQuickSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = i;
        }

        QuickSort quickSort = new QuickSort(quant, arr);
        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = quickSort.quickSort(arr, 0, (quant - 1));
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long swapNumber = metrics.get("swapNumber");
        long compoNumber = metrics.get("compNumber");

        return new SortMetrics(arr, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, false);
    }

    public SortMetrics medioCasoQuickSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = RandomGenerator.randomGenerator();
        }

        QuickSort quickSort = new QuickSort(quant, arr);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = quickSort.quickSort(arr, 0, (quant - 1));
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long swapNumber = metrics.get("swapNumber");
        long compoNumber = metrics.get("compNumber");

        return new SortMetrics(arr, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, false);
    }
}
