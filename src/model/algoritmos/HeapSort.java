package model.algoritmos;

public class HeapSort {

    public long[] arr;
    public static long swapNumber = 0;
    public static long compNumber = 0;

    public HeapSort(int quant, long[] arr) {
        this.arr = arr;
    }

    static void heapify(long[] arr, int n, int i) {

        int largest = i;

        int l = 2 * i + 1;

        int r = 2 * i + 2;

        if (l < n) {
            compNumber++;
            if (arr[l] > arr[largest]) {
                largest = l;
            }
        }

        if (r < n) {
            compNumber++;
            if (arr[r] > arr[largest]) {
                largest = r;
            }
        }

        if (largest != i) {
            long temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            swapNumber++;

            heapify(arr, n, largest);
        }
    }

    public static void heapSort(long[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {

            long temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swapNumber++;

            heapify(arr, i, 0);
        }
    }

    public void heapSortReport() {
        System.out.println("Número de comparações: " + compNumber);
        System.out.println("Número de trocas: " + swapNumber);
        System.out.println("É estável: Não");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void hSortManualPrint(long[] arr) {
        System.out.println("VETOR ORDENADO: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d -> ", arr[i]);
        }
        System.out.println();
    }
}
