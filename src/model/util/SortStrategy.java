package model.util;

import model.analise.SortMetrics;

public interface SortStrategy<QNT, ARR, ALG> {

    public SortMetrics execute (QNT quant, ARR array, ALG algo);

    public String getSortName();
}
