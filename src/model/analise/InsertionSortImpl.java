package model.analise;

import model.algoritmos.InsertionSort;
import model.util.SortStrategy;

import java.util.Map;

public class InsertionSortImpl implements SortStrategy<Integer, long[], String> {

    String sortName = "InsertionSort";

    @Override
    public SortMetrics execute(Integer quant, long[] array, String algo) {

        InsertionSort insertionSort = new InsertionSort(quant, array);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = insertionSort.insertionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(array, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }

    public String getSortName() {
        return sortName;
    }
}
