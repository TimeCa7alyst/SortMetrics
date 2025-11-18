package model.analise;

import model.algoritmos.SelectionSort;
import model.util.RandomGenerator;
import java.util.Map;

public class SelectionSortImpl {

    public void piorCasoSelectionSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = (quant - 1) - i;
        }

        SelectionSort selectionSort = new SelectionSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = selectionSort.selectionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        SortMetrics sortMetrics = new SortMetrics(vet, swapNumber,
                compNumber,totalMs, isStable);

        sortMetrics.sortReport("Pior caso");
    }

    public void melhorCasoSelectionSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = i;
        }

        SelectionSort selectionSort = new SelectionSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = selectionSort.selectionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        SortMetrics sortMetrics = new SortMetrics(vet, swapNumber,
                compNumber,totalMs, isStable);

        sortMetrics.sortReport("Melhor caso");
    }

    public void medioCasoSelectionSort(int quant) {
        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = RandomGenerator.randomGenerator();
        }

        SelectionSort selectionSort = new SelectionSort(quant, vet);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = selectionSort.selectionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        long compNumber = metrics.get("compNumber");
        long swapNumber = metrics.get("swapNumber");
        boolean isStable = true;

        SortMetrics sortMetrics = new SortMetrics(vet, swapNumber,
                compNumber,totalMs, isStable);

        sortMetrics.sortReport("Médio caso");
    }
}
