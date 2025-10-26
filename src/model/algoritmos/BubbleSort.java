package model.algoritmos;


public class BubbleSort {

    private long[] vet;
    private long numTrocas = 0;
    private long numComparacoes = 0;

    public BubbleSort(int quant, long[] vet) {
        this.vet = vet;
    }

    public void bubbleSort() {
        long temp;

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
    }

    public void bSortReport() {

        System.out.println("Número de comparações: " + numComparacoes);
        System.out.println("Número de trocas: " + numTrocas);
        System.out.println("É estável: Sim");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void bSortManualPrint(long[] vet) {
        System.out.println("VETOR ORDENADO: ");
        for (int i = 0; i < vet.length; i++) {
            System.out.printf("%d -> ", vet[i]);
        }
        System.out.println();
    }
}
