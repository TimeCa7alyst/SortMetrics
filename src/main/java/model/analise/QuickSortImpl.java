package model.analise;

import model.algoritmos.QuickSort;
import model.util.SortStrategy;

import java.util.Map;

public class QuickSortImpl implements SortStrategy<Integer, long[], String> {

    String sortName = "QuickSort";

    @Override
    public SortMetrics execute(Integer quant, long[] array, String algo) {

        System.gc();

        QuickSort quickSort = new QuickSort(quant, array);

        long tempo1 = System.nanoTime();
        long memory1 = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        Map<String, Long> metrics = quickSort.quickSort(array, 0, (quant - 1));

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
