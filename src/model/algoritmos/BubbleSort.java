package model.algoritmos;


public class BubbleSort {

    private long[] arr;
    private long swapNumber = 0;
    private long compNumber = 0;

    public BubbleSort(int quant, long[] arr) {
        this.arr = arr;
    }

    public void bubbleSort() {
        long temp;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - (i + 1); j++) {
                compNumber++;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapNumber++;
                }
            }
        }
    }

    public void bSortReport() {

        System.out.println("Número de comparações: " + compNumber);
        System.out.println("Número de trocas: " + swapNumber);
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
