package algoritmos;

public class InsertionSort {

    public long[] vet;
    private long numTrocas = 0;
    private long numComparacoes = 0;

    public InsertionSort(int quant, long[] vet) {
        this.vet = vet;
    }

    public void insertionSort() {
        long temp;


        for (int i = 1; i < vet.length; i++) {

            temp = vet[i];
            int j = (i - 1);
            numComparacoes++;
            while (j >= 0 && vet[j] > temp) {
                vet[j + 1] = vet[j];
                numTrocas++;
                j--;
            }
            vet[j + 1] = temp;
        }
    }

    public void insertionSortPrint() {

        System.out.println("Número de comparações: " + numComparacoes);
        System.out.println("Número de trocas:" + numTrocas);
        System.out.println("É estável: Sim");
        System.out.println("------------------------------------------");
        System.out.println();
    }
}