package model.algoritmos;

public class QuickSort {
    private static long[] arr;
    private static long compNumber = 0;
    private static long swapNumber = 0;

    public QuickSort(int quant, long[] arr) {
        this.arr = arr;
    }

    static long partition(long[] arr, int low, int high) {

        long pivot = arr[high];

        int i = low - 1;

        for (int j = low; j <= high; j++) {
            compNumber++;

            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static void swap(long[] arr, int i, int j) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swapNumber++;
    }

    public void quickSort(long[] arr, int low, int high) {
        if (low < high) {

            int partitionIndex = (int) partition(arr, low, high);

            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);

        }
    }

    public void quickSortReport() {
        System.out.println("Número de comparações: " + compNumber);
        System.out.println("Número de trocas: " + swapNumber);
        System.out.println("É estável: Não");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void qSortManualPrint(long[] arr) {
        System.out.println("VETOR ORDENADO: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d -> ", arr[i]);
        }
        System.out.println();
    }
}
