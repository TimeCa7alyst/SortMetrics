package entities;

import java.util.Arrays;

public class InsertionSort {

    public void insertionSort(long[] vet) {

        long temp;
        for (int i = 0; i < vet.length; i++) {
            temp = vet[i];
            int j = (i - 1);
            while (j >= 0 && vet[j] > temp) {
                vet[j + 1] = vet[j];
                j--;
            }
            vet[j + 1] = temp;
        }
    }

    static void insertionSortPrint(long[] vet) {

        System.out.println("Vetor ordenado:\n");
        System.out.println(Arrays.toString(vet) + "\n");
    }
}
