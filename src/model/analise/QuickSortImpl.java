package model.analise;

import model.algoritmos.QuickSort;
import model.util.RandomGenerator;

public class QuickSortImpl {

    public void piorCasoMergeSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = (quant - 1) - i;
        }

        QuickSort quickSort = new QuickSort(quant, arr);

        long tempo1 = System.nanoTime();
        quickSort.quickSort(arr, 0, (quant - 1));
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000_000;
        System.out.println("Pior caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        quickSort.quickSortReport();
    }

    public void melhorCasoQuickSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = i;
        }

        QuickSort quickSort = new QuickSort(quant, arr);
        long tempo1 = System.nanoTime();
        quickSort.quickSort(arr, 0, (quant - 1));
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000_000;

        System.out.println("Melhor caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        quickSort.quickSortReport();
    }

    public void medioCasoQuickSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = RandomGenerator.randomGenerator();
        }

        QuickSort quickSort = new QuickSort(quant, arr);

        long tempo1 = System.nanoTime();
        quickSort.quickSort(arr, 0, (quant - 1));
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000_000;

        System.out.println("Médio caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        quickSort.quickSortReport();
    }
}
