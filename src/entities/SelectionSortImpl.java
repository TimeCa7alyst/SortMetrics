package entities;

public class SelectionSortImpl {

    public void piorCasoSelectionSort(int quant, long[] vet) {

        vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = (quant - 1) - i;
        }

        SelectionSort selectionSort = new SelectionSort(quant, vet);

        long tempo1 = System.nanoTime();
        selectionSort.selectionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Pior caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        selectionSort.selectionSortPrint();
    }

    public void melhorCasoSelectionSort(int quant, long[] vet) {
        vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = i;
        }

        SelectionSort selectionSort = new SelectionSort(quant, vet);
        long tempo1 = System.nanoTime();
        selectionSort.selectionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        System.out.println("Melhor caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        selectionSort.selectionSortPrint();
    }

    public void medioCasoSelectionSort(int quant, long[] vet) {
        vet = new long[quant];

        for (int i = 0; i < quant; i++) {
            vet[i] = RandomGenerator.randomGenerator();
        }

        SelectionSort selectionSort = new SelectionSort(quant, vet);

        long tempo1 = System.nanoTime();
        selectionSort.selectionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;


        System.out.println("Médio caso: ");
        System.out.println();
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms");
        System.out.println();
        selectionSort.selectionSortPrint();
    }
}
