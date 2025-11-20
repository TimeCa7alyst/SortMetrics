package model.algoritmos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MergeSort {

    private long compNumber = 0;

    public MergeSort(int quant, long[] arr) {
    }

    public Map<String, Long> executeMergeSort(long[] arr) {
        this.compNumber = 0;

        long[] sorted = recursiveSort(arr);

        System.arraycopy(sorted, 0, arr, 0, arr.length);

        Map<String, Long> metrics = new HashMap<>();
        metrics.put("compNumber", compNumber);
        metrics.put("swapNumber", 0L);

        return metrics;
    }

    private long[] recursiveSort(long[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;

        long[] left = recursiveSort(Arrays.copyOfRange(arr, 0, mid));
        long[] right = recursiveSort(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(left, right);
    }

    private long[] merge(long[] left, long[] right) {
        long[] combined = new long[left.length + right.length];
        int index = 0;
        int i = 0;
        int j = 0;

        while (i < left.length && j < right.length) {
            compNumber++;

            if (left[i] < right[j]) {
                combined[index++] = left[i++];
            } else {
                combined[index++] = right[j++];
            }
        }

        while (i < left.length) {
            combined[index++] = left[i++];
        }
        while (j < right.length) {
            combined[index++] = right[j++];
        }

        return combined;
    }
}