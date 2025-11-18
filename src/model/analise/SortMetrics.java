package model.analise;

public class SortMetrics {

    private long[] sortArr;
    private long swapNumber;
    private long compNumber;
    private double totalMs;
    private boolean isStable;

    public SortMetrics(long[] sortArr, long swapNumber,
                       long compNumber, double totalMs, boolean isStable) {
        this.sortArr = sortArr;
        this.swapNumber = swapNumber;
        this.compNumber = compNumber;
        this.totalMs = totalMs;
        this.isStable = isStable;
    }

    public SortMetrics(long[] sortArr, long compNumber, double totalMs, boolean isStable) {
        this.sortArr = sortArr;
        this.compNumber = compNumber;
        this.totalMs = totalMs;
        this.isStable = isStable;
    }

    public void sortReport(String sortCase) {
        System.out.println(sortCase + ": \n");
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms\n");
        System.out.println("Número de comparações: " + compNumber);
        System.out.println("Número de trocas: " + swapNumber);
        System.out.println("É estável: " + (isStable ? "Sim" : "Não"));
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void sortReportNoSwaps(String sortCase) {
        System.out.println(sortCase + ": \n");
        System.out.println("TEMPO DE EXECUÇÃO: " + totalMs + "ms\n");
        System.out.println("Número de comparações: " + compNumber);
        System.out.println("Número de trocas: N");
        System.out.println("É estável: " + (isStable ? "Sim" : "Não"));
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public void manualPrint() {
        System.out.println("VETOR ORDENADO: ");
        for (int i = 0; i < sortArr.length; i++) {
            System.out.printf("%d -> ", sortArr[i]);
        }
        System.out.println();
    }

    public long getSwapNumber() {
        return swapNumber;
    }

    public long getCompNumber() {
        return compNumber;
    }

    public double getTotalMs() {
        return totalMs;
    }

    public boolean isStable() {
        return isStable;
    }
}
