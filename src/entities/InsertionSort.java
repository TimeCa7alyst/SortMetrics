package entities;

public class InsertionSort {

    public long[] vet;
    private long tempo1;
    private long tempo2;
    private long totalNano;
    private long numTrocas = 0;
    private long numComparacoes = 0;

    public InsertionSort(int quant, long[] vet) {
        this.vet = vet;
    }

    public void insertionSort() {
        long temp;

        tempo1 = System.nanoTime();

        for (int i = 1; i < vet.length; i++) {

            temp = vet[i];
            int j = (i - 1);
            numComparacoes++;
            while (j >= 0 && vet[j] > temp) {
                vet[j + 1] = vet[j];
                numTrocas++;
                j--;
            }
            vet[j + 1] = temp;
        }
        tempo2 = System.nanoTime();
        totalNano = (tempo2 - tempo1);
    }

    public void insertionSortPrint() {

        System.out.print("VETOR ORDERNADO:\n");
        for (long l : vet) {
            System.out.printf("%d -> ", l);
        }

        System.out.println("\n");

        System.out.print("TEMPO DE EXECUÇÃO (Insertion Sort):\n");


        double totalMs = this.totalNano / 1_000_000.0;

        System.out.printf("Tempo 1: %dns\n" +
                "Tempo 2: %dns\n", this.tempo1, this.tempo2);
        System.out.println("Total: " + totalMs + "ms");
        System.out.println();
        System.out.println("Número de comparações: " + numComparacoes);
        System.out.println("Número de trocas:" + numTrocas);
        System.out.println("É estável: Sim");
    }
}