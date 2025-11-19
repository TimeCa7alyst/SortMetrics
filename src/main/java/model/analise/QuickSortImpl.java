package model.analise;

import model.algoritmos.QuickSort;
import model.util.SortStrategy;

import java.util.Map;

public class QuickSortImpl implements SortStrategy<Integer, long[], String> {

    String sortName = "QuickSort";

    @Override
    public SortMetrics execute(Integer quant, long[] array, String algo) {

        QuickSort quickSort = new QuickSort(quant, array);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = quickSort.quickSort(array, 0, (quant - 1));
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(array, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, false);
    }

    public String getSortName() {
        return sortName;
    }
}
