package analise;

import algoritmos.BubbleSort;
import util.RandomGenerator;

public class BubbleSortImpl {

    public void piorCasoBubbleSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = (quant - 1) - i;
        }

        BubbleSort bubbleSort = new BubbleSort(quant, vet);

        long tempo1 = System.nanoTime();
        bubbleSort.bubbleSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Pior caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        bubbleSort.bSortReport();
    }

    public void melhorCasoBubbleSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = i;
        }

        BubbleSort bubbleSort = new BubbleSort(quant, vet);
        long tempo1 = System.nanoTime();
        bubbleSort.bubbleSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Melhor caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        bubbleSort.bSortReport();
    }

    public void medioCasoBubbleSort(int quant) {

        long[] vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = RandomGenerator.randomGenerator();
        }

        BubbleSort bubbleSort = new BubbleSort(quant, vet);

        long tempo1 = System.nanoTime();
        bubbleSort.bubbleSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Médio caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        bubbleSort.bSortReport();
    }
}
