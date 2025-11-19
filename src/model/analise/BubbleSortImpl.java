package model.analise;

import model.algoritmos.BubbleSort;
import model.util.SortStrategy;

import java.util.Map;

public class BubbleSortImpl implements SortStrategy<Integer, long[], String> {

    String sortName = "BubbleSort";

    @Override
    public SortMetrics execute(Integer quant, long[] array, String algoName) {

        BubbleSort bubbleSort = new BubbleSort(quant, array);

        long tempo1 = System.nanoTime();
        Map<String, Long> metrics = bubbleSort.bubbleSort();
        long tempo2 = System.nanoTime();

        double totalMs = (tempo2 - tempo1) / 1_000_000.0;

        return new SortMetrics(array, metrics.get("swapNumber"),
                metrics.get("compNumber"), totalMs, true);
    }

    public String getSortName() {
        return sortName;
    }
}
