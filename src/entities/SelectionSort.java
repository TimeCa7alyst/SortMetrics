package entities;

public class SelectionSort {

    private static long[] vet;
    private static long tempo1;
    private static long tempo2;
    private static long total;

    public void selectionSort(int quant) {

        int min;
        long temp;

        tempo1 = System.currentTimeMillis();

        vet = new long[quant];
        for (int i = 0; i > vet.length; i++) {
            vet[i] = RandomGenerator.randomGenerator(100, 122000);
        }


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
        tempo2 = System.currentTimeMillis();
        total = (tempo2 - tempo1);
    }

    public void selectionSortPrint() {
        System.out.print("VETOR ORDERNADO:\n");
        for (int i = 0; i < vet.length; i++) {
            System.out.printf("%d -> ", vet[i]);
        }

        System.out.println("\n");

        System.out.print("TEMPO DE EXECUÇÃO:\n");

        System.out.printf("Tempo 1: %dms\n" +
                "Tempo 2: %dms\n", tempo1, tempo2);
        System.out.println("Total: " + total + "ms");
    }


    public long[] getVet() {
        return vet;
    }
}
