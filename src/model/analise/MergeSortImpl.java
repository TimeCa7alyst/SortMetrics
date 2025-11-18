package model.analise;

import model.algoritmos.MergeSort;
import model.util.RandomGenerator;

public class MergeSortImpl {

    public void piorCasoMergeSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = (quant - 1) - i;
        }

        MergeSort mergeSort = new MergeSort(quant, arr);

        long tempo1 = System.nanoTime();
        mergeSort.mergeSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;
        System.out.println("Pior caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        mergeSort.mergeSortReport();
    }

    public void melhorCasoMergeSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = i;
        }

        MergeSort mergeSort = new MergeSort(quant, arr);
        long tempo1 = System.nanoTime();
        mergeSort.mergeSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Melhor caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        mergeSort.mergeSortReport();
    }

    public void medioCasoMergeSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = RandomGenerator.randomGenerator();
        }

        MergeSort mergeSort = new MergeSort(quant, arr);

        long tempo1 = System.nanoTime();
        mergeSort.mergeSort(arr);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Médio caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        mergeSort.mergeSortReport();
    }
}
