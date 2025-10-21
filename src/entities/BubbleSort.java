package entities;


public class BubbleSort {

    private long[] vet;
    private long tempo1;
    private long tempo2;
    private long totalNano;
    private long numTrocas = 0;
    private long numComparacoes = 0;

    public BubbleSort(int quant, long[] vet) {
        this.vet = vet;
    }

    public void bubbleSort() {
        long temp;

        tempo1 = System.nanoTime();


        for (int i = 0; i < vet.length; i++) {
            for (int j = 0; j < vet.length - (i + 1); j++) {
                numComparacoes++;
                if (vet[j] > vet[j + 1]) {
                    temp = vet[j];
                    vet[j] = vet[j + 1];
                    vet[j + 1] = temp;
                    numTrocas++;
                }
            }
        }
        tempo2 = System.nanoTime();
        totalNano = (tempo2 - tempo1);
    }


    public void bSortPrint() {

        System.out.print("VETOR ORDERNADO:\n");
        for (long l : vet) {
            System.out.printf("%d -> ", l);
        }

        System.out.println("\n");

        System.out.print("TEMPO DE EXECUÇÃO (Bubble Sort):\n");

        double totalMs = this.totalNano / 1_000_000.0;

        System.out.printf("Tempo 1: %dns\n" +
                "Tempo 2: %dns\n", tempo1, tempo2);
        System.out.println("Total: " + totalMs + "ms");
        System.out.println();
        System.out.println("Número de comparações: " + numComparacoes);
        System.out.println("Número de trocas:" + numTrocas);
        System.out.println("É estável: Sim");
    }

}
