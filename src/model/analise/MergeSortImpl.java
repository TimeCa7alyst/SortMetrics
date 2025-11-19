package model.analise;

import model.algoritmos.MergeSort;
import model.util.SortStrategy;

import java.util.Map;

public class MergeSortImpl implements SortStrategy<Integer, long[], String> {

    String sortName = "MergeSort";

    @Override
    public SortMetrics execute(Integer quant, long[] array, String algo) {

        MergeSort mergeSort = new MergeSort(quant, array);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = mergeSort.executeMergeSort(array);
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(array, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }

    public String getSortName() {
        return sortName;
    }
}