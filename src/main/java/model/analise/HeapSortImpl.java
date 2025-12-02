package model.analise;

import model.algoritmos.HeapSort;
import model.util.SortStrategy;

import java.util.Map;

public class HeapSortImpl implements SortStrategy<Integer, long[], String> {

    String sortName = "HeapSort";

    @Override
    public SortMetrics execute(Integer quant, long[] array, String algo) {

        HeapSort heapSort = new HeapSort(quant, array);

        System.gc();

        long tempo1 = System.nanoTime();
        long memory1 = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        Map<String, Long> metrics = heapSort.heapSort(array);

        long memory2 = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;
        long totalMemory = (memory2 - memory1);

        return new SortMetrics(array, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, totalMemory, false);
    }

    public String getSortName() {
        return sortName;
    }
}
