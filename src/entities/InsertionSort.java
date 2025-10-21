package entities;

public class InsertionSort {

    private long[] vet;
    private long tempo1;
    private long tempo2;
    private long total;

    public void insertionSort(int quant) {

        long temp;

        tempo1 = System.currentTimeMillis();

        vet = new long[quant];
        for (int i = 0; i < vet.length; i++) {
            vet[i] = RandomGenerator.randomGenerator(100, 200000);
        }


        for (int i = 0; i < vet.length; i++) {
            temp = vet[i];
            int j = (i - 1);
            while (j >= 0 && vet[j] > temp) {
                vet[j + 1] = vet[j];
                j--;
            }
            vet[j + 1] = temp;
        }
        tempo2 = System.currentTimeMillis();
        total = (tempo2 - tempo1);
    }

    public void insertionSortPrint() {

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
