package entities;


public class BubbleSort {

    private final long[] vet;
    private final long tempo1;
    private final long tempo2;
    private final long total;


    public BubbleSort(int quant) {
        long temp;

        tempo1 = System.currentTimeMillis();

        vet = new long[quant];
        for (int i = 0; i < vet.length; i++) {
            vet[i] = RandomGenerator.randomGenerator(100, 122000);
        }


        for (int i = 0; i < vet.length; i++) {
            for (int j = 0; j < vet.length - (i + 1); j++) {
                if (vet[j] > vet[j + 1]) {
                    temp = vet[j];
                    vet[j] = vet[j + 1];
                    vet[j + 1] = temp;

                }
            }
        }
        tempo2 = System.currentTimeMillis();
        total = (tempo2 - tempo1);
    }


    public void bSortPrint() {

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
