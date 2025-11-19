package model.analise;

import model.algoritmos.SelectionSort;
import model.util.SortStrategy;

import java.util.Map;

public class SelectionSortImpl implements SortStrategy<Integer, long[], String> {

    String sortName = "SelectionSort";

    @Override
    public SortMetrics execute(Integer quant, long[] array, String algo) {

        SelectionSort selectionSort = new SelectionSort(quant, array);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = selectionSort.selectionSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(array, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, false);
    }

    public String getSortName() {
        return sortName;
    }
}
