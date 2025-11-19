package model.analise;

import model.algoritmos.HeapSort;
import model.util.SortStrategy;

import java.util.Map;

public class HeapSortImpl implements SortStrategy<Integer, long[], String> {

    String sortName = "HeapSort";

    @Override
    public SortMetrics execute(Integer quant, long[] array, String algo) {

        HeapSort heapSort = new HeapSort(quant, array);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = heapSort.heapSort(array);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(array, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, false);
    }

    public String getSortName() {
        return sortName;
    }
}
