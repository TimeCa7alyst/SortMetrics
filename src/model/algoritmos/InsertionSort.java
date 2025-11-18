package model.algoritmos;

public class InsertionSort {

    public long[] arr;
    private long swapNumber = 0;
    private long compNumber = 0;

    public InsertionSort(int quant, long[] arr) {
        this.arr = arr;
    }

    public void insertionSort() {
        long temp;

        for (int i = 1; i < arr.length; i++) {

            temp = arr[i];
            int j = (i - 1);
            compNumber++;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                swapNumber++;
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public void insertionSortReport() {

        System.out.println("Número de comparações: " + compNumber);
        System.out.println("Número de trocas: " + swapNumber);
        System.out.println("É estável: Sim");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void iSortManualPrint(long[] vet) {
        System.out.println("VETOR ORDENADO: ");
        for (int i = 0; i < vet.length; i++) {
            System.out.printf("%d -> ", vet[i]);
        }
        System.out.println();
    }
}