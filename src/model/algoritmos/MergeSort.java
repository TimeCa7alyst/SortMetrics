package model.algoritmos;

import java.util.Arrays;

public class MergeSort {
    private static long[] arr;
    private long[] arr2;
    private long[] helper;

    public MergeSort(int quant, long[] arr) {
        this.arr = arr;
        this.arr2 = arr;
        this.helper = new long[quant];
    }

    public static long[] merge(long[] arr, long[] arr2) {

        long[] combined = new long[arr.length + arr2.length];

        int index = 0;
        int i = 0;
        int j = 0;

        while (i < arr.length && j < arr2.length) {
            if (arr[i] < arr2[j]) {
                combined[index] = arr[i];
                index++;
                i++;
            } else {
                combined[index] = arr2[j];
                index++;
                j++;
            }
        }
        while (i < arr.length) {
            combined[index] = arr[i];
            index++;
            i++;
        }
        while (j < arr2.length) {
            combined[index] = arr2[j];
            index++;
            j++;
        }
        return combined;
    }

    public static long[] mergeSort(long[] arr) {
        if (arr.length <= 1) return arr;

        long mid = arr.length / 2;
        long[] left = mergeSort(Arrays.copyOfRange(arr, 0, (int) mid));
        long[] right = mergeSort(Arrays.copyOfRange(arr, (int) mid, arr.length));

        return merge(left, right);
    }

    public void mergeSortReport() {
        System.out.println("É estável: Sim");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void mSortManualPrint(long[] arr) {
        System.out.println("VETOR ORDENADO: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d -> ", arr[i]);
        }
        System.out.println();
    }
}
