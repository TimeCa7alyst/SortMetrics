package entities;

public class SelectionSort {

    private long[] vet;
    private long tempo1;
    private long tempo2;
    private long totalNano;
    private long numTrocas = 0;
    private long numComparacoes = 0;

    public SelectionSort(int quant, long[] vet) {
        this.vet = vet;
    }

    public void selectionSort() {

        int min;
        long temp;

        tempo1 = System.nanoTime();

        for (int i = 0; i < vet.length - 1; i++) {
            min = i;
            for (int j = (i + 1); j < vet.length; j++) {
                numComparacoes++;
                if (vet[j] < vet[min]) {
                    min = j;
                }
            }
            if (i != min) {
                temp = vet[i];
                vet[i] = vet[min];
                vet[min] = temp;
                numTrocas++;
            }
        }
        tempo2 = System.nanoTime();
        totalNano = (tempo2 - tempo1);
    }

    public void selectionSortPrint() {
        System.out.print("VETOR ORDERNADO:\n");
        for (long l : vet) {
            System.out.printf("%d -> ", l);
        }

        System.out.println("\n");

        System.out.print("TEMPO DE EXECUÇÃO (Selection Sort):\n");

        double totalMs = this.totalNano / 1_000_000.0;

        System.out.printf("Tempo 1: %dns\n" +
                "Tempo 2: %dns\n", tempo1, tempo2);
        System.out.println("Total: " + totalMs + "ms");
        System.out.println();
        System.out.println("Número de comparações: " + numComparacoes);
        System.out.println("Número de trocas:" + numTrocas);
        System.out.println("É estável: Não");
    }
}
