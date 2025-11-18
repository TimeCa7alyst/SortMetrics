package model.algoritmos;

public class SelectionSort {

    private long[] arr;
    private long swapNumber = 0;
    private long compNumber = 0;

    public SelectionSort(int quant, long[] arr) {
        this.arr = arr;
    }

    public void selectionSort() {

        int min;
        long temp;

        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = (i + 1); j < arr.length; j++) {
                compNumber++;
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
                swapNumber++;
            }
        }
    }

    public void selectionSortReport() {

        System.out.println("Número de comparações: " + compNumber);
        System.out.println("Número de trocas: " + swapNumber);
        System.out.println("É estável: Não");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void sSortManualPrint(long[] vet) {
        System.out.println("VETOR ORDENADO: ");
        for (int i = 0; i < vet.length; i++) {
            System.out.printf("%d -> ", vet[i]);
        }
        System.out.println();
    }
}
