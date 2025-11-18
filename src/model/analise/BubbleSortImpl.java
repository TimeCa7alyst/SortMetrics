package model.analise;

import model.algoritmos.BubbleSort;
import model.util.RandomGenerator;

import java.util.Map;

public class BubbleSortImpl {

    public SortMetrics piorCasoBubbleSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = (quant - 1) - i;
        }

        BubbleSort bubbleSort = new BubbleSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = bubbleSort.bubbleSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        return new SortMetrics(vet, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }

    public SortMetrics melhorCasoBubbleSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = i;
        }

        BubbleSort bubbleSort = new BubbleSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = bubbleSort.bubbleSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        return new SortMetrics(vet, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);

    }

    public SortMetrics medioCasoBubbleSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = RandomGenerator.randomGenerator();
        }

        BubbleSort bubbleSort = new BubbleSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = bubbleSort.bubbleSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        return new SortMetrics(vet, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }
}
