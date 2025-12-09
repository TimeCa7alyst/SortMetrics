package model.analise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SortMetrics {

    @JsonIgnore
    private long[] sortArr;

    private long swapNumber;
    private long compNumber;
    private double totalMs;
    private long memoryUsed;
    private boolean stable;

    public SortMetrics(long[] sortArr, long swapNumber,
                       long compNumber, double totalMs, long memoryUsed, boolean stable) {
        this.sortArr = sortArr;
        this.swapNumber = swapNumber;
        this.compNumber = compNumber;
        this.totalMs = totalMs;
        this.memoryUsed = memoryUsed;
        this.stable = stable;
    }

    public SortMetrics() {
    }

    public void sortReport(String sortCase) {
        double memoryUsedDouble = (double) memoryUsed / (1024 * 1024);

        System.out.println(sortCase + ": \n");
        System.out.printf("TEMPO DE EXECUÇÃO: %.2fms\n", totalMs);
        System.out.printf("MEMÓRIA UTILIZADA: %.2fMB\n\n", memoryUsedDouble);
        System.out.println("Número de entradas: " + sortArr.length);
        System.out.println("Número de comparações: " + compNumber);
        System.out.println("Número de trocas: " + swapNumber);
        System.out.println("É estável: " + (stable ? "Sim" : "Não"));
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

    public long getMemoryUsed() {
        return memoryUsed;
    }

    public boolean isStable() {
        return stable;
    }
}
