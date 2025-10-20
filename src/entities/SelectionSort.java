package entities;

public class SelectionSort {

    public void selectionSort(long[] vet) {

        int min;
        long temp;

        for (int i = 0; i < vet.length - 1; i++) {
            min = i;
            for (int j = (i + 1); j < vet.length; j++) {
                if (vet[j] < vet[min]) {
                    min = j;
                }
            }
            if (i != min) {
                temp = vet[i];
                vet[i] = vet[min];
                vet[min] = temp;
            }
        }
    }

    static void selectionSortPrint(long[] vet) {
        System.out.println("Vetor ordenado:\n");
        for (int i = 0; i < vet.length; i++) {
            System.out.printf("%d -> \n", vet[i]);
        }
    }
}
