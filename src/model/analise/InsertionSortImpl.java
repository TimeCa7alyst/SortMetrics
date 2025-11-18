package model.analise;

import model.algoritmos.InsertionSort;
import model.util.RandomGenerator;

import java.util.Map;

public class InsertionSortImpl {

    public SortMetrics piorCasoInsertionSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = (quant - 1) - i;
        }

        InsertionSort insertionSort = new InsertionSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = insertionSort.insertionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        return new SortMetrics(vet, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }

    public SortMetrics melhorCasoInsertionSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = i;
        }

        InsertionSort insertionSort = new InsertionSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = insertionSort.insertionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        return new SortMetrics(vet, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }

    public SortMetrics medioInsertionSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = RandomGenerator.randomGenerator();
        }

        InsertionSort insertionSort = new InsertionSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = insertionSort.insertionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        return new SortMetrics(vet, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }
}
