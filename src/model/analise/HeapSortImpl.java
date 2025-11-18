package model.analise;

import model.algoritmos.HeapSort;
import model.util.RandomGenerator;

public class HeapSortImpl {

    public void piorCasoHeapSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = (quant - 1) - i;
        }

        HeapSort heapSort = new HeapSort(quant, arr);

        long tempo1 = System.nanoTime();
        HeapSort.heapSort(arr);
        long tempo2  = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Pior caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        heapSort.heapSortReport();
    }

    public void melhorCasoHeapSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = i;
        }

        HeapSort heapSort = new HeapSort(quant, arr);
        long tempo1 = System.nanoTime();
        HeapSort.heapSort(arr);
        long tempo2  = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Melhor caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        heapSort.heapSortReport();
    }

    public void medioCasoHeapSort(int quant) {

        long[] arr = new long[quant];

        for (int i = 0; i < quant; i++) {
            arr[i] = RandomGenerator.randomGenerator();
        }

        HeapSort heapSort = new HeapSort(quant, arr);

        long tempo1 = System.nanoTime();
        HeapSort.heapSort(arr);
        long tempo2  = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Médio caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        heapSort.heapSortReport();
    }
}
