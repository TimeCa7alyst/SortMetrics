package analise;

import algoritmos.InsertionSort;
import util.RandomGenerator;

public class InsertionSortImpl {

    public void piorCasoInsertionSort(int quant) {

        long[] vet = new long[quant];

        vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = (quant - 1) - i;
        }

        InsertionSort insertionSort = new InsertionSort(quant, vet);

        long tempo1 = System.nanoTime();
        insertionSort.insertionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Pior caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        insertionSort.insertionSortPrint();
    }

    public void melhorCasoInsertionSort(int quant) {

        long[] vet = new long[quant];

        vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = i;
        }

        InsertionSort insertionSort = new InsertionSort(quant, vet);
        long tempo1 = System.nanoTime();
        insertionSort.insertionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Melhor caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        insertionSort.insertionSortPrint();
    }

    public void medioInsertionSort(int quant) {

        long[] vet = new long[quant];
        vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = RandomGenerator.randomGenerator();
        }

        InsertionSort insertionSort = new InsertionSort(quant, vet);

        long tempo1 = System.nanoTime();
        insertionSort.insertionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;


        System.out.println("Médio caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        insertionSort.insertionSortPrint();
    }
}
