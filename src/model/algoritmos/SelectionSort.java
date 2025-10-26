package model.algoritmos;

public class SelectionSort {

    private long[] vet;
    private long numTrocas = 0;
    private long numComparacoes = 0;

    public SelectionSort(int quant, long[] vet) {
        this.vet = vet;
    }

    public void selectionSort() {

        int min;
        long temp;

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
    }

    public void selectionSortReport() {

        System.out.println("Número de comparações: " + numComparacoes);
        System.out.println("Número de trocas: " + numTrocas);
        System.out.println("É estável: Não");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void sSortManualPrint(long[] vet) {
        System.out.println("VETOR ORDENADO");
        for (int i = 0; i < vet.length; i++) {
            System.out.printf("%d -> ", vet[i]);
        }
        System.out.println();
    }
}
