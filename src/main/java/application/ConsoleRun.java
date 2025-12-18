package application;

import DAO.AnalysisDAO;
import DB.DbManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.analise.*;
import model.entities.Analysis;
import model.util.*;
import service.AnalysisService;
import view.BarChart;
import view.ScatterChart;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

public class ConsoleRun {
    public static int quant;

    static ObjectMapper mapper = new ObjectMapper();
    static AnalysisDAO analysisDAO;

    static {
        try {
            analysisDAO = new AnalysisDAO(DbManager.getConnection(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static AnalysisService analysisService = new AnalysisService(analysisDAO);

    public ConsoleRun() throws SQLException {
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String outDirectory = "src" + File.separator + "out";
        Scanner sc = new Scanner(System.in);

        String algoritmo;

        System.out.println("""
                Escolha um dos algoritmos:
                
                Bubble Sort
                Selection Sort
                Insertion Sort
                Merge Sort
                Quick Sort
                Heap Sort
                All
                
                OU digite "sair" para fechar o programa""");
        algoritmo = sc.nextLine().toLowerCase();

        if (algoritmo.equals("sair")
                || (algoritmo.equals("fechar") || (algoritmo.equals("fechar programa")))) {
            System.exit(0);
        }

        while (true) {
            System.out.println("Quantidade de números para serem ordenados:");
            try {
                quant = Integer.parseInt(sc.nextLine());
                if (quant < 0) {
                    System.out.println("||| Quantidade deve ser maior que zero! |||\n");
                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("||| Entrada inválida |||");
            }
        }

        DefineTipoQuantidade defTipo = new DefineTipoQuantidade();
        defTipo.defineTipo(quant);

        List<SortStrategy<Integer, long[], String>> strategies = new ArrayList<>();
        strategies.add(new BubbleSortImpl());
        strategies.add(new InsertionSortImpl());
        strategies.add(new SelectionSortImpl());
        strategies.add(new HeapSortImpl());
        strategies.add(new MergeSortImpl());
        strategies.add(new QuickSortImpl());

        long[] masterRnd = CaseGenerator.genAvgCase(quant);
        long[] masterBest = CaseGenerator.genBestCase(quant);
        long[] masterWorst = CaseGenerator.genWorstCase(quant);
        long[] masterTiny = new long[quant];

        if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

            for (int i = 0; i < quant; i++) {
                while (true) {
                    try {
                        System.out.printf("Digite o %d valor: ", (i + 1));
                        masterTiny[i] = Long.parseLong(sc.nextLine());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("||| Entrada Inválida |||");
                    }
                }
            }
        }

        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        Map<String, List<SortMetrics>> results = new HashMap<>();

        switch (algoritmo) {
            case "bubble sort", "bsort", "bubble", "b", "buble sort":

                System.out.println("\n||||| Bubble Sort |||||\n");

                results.put("Bubble Sort", runAndCollectMetrics(new BubbleSortImpl(), masterBest, masterRnd, masterWorst));

                if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                    SortMetrics manualMetrics = new BubbleSortImpl().execute(quant, masterTiny.clone(), "Bubble Sort");

                    manualMetrics.manualPrint();
                }
                break;

            case "insertion sort", "isort", "insertion", "i":

                System.out.println("\n||||| Insertion Sort |||||\n");

                results.put("Insertion Sort", runAndCollectMetrics(new InsertionSortImpl(), masterBest, masterRnd, masterWorst));

                if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                    SortMetrics manualMetrics = new InsertionSortImpl().execute(quant, masterTiny.clone(), "Insertion Sort");

                    manualMetrics.manualPrint();
                }
                break;

            case "selection sort", "ssort", "selection", "s":

                System.out.println("\n||||| Selection Sort |||||\n");

                results.put("Selection Sort", runAndCollectMetrics(new SelectionSortImpl(), masterBest, masterRnd, masterWorst));

                if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                    SortMetrics manualMetrics = new SelectionSortImpl().execute(quant, masterTiny.clone(), "Selection Sort");

                    manualMetrics.manualPrint();
                }
                break;

            case "merge sort", "msort", "merge", "m":

                results.put("Merge Sort", runAndCollectMetrics(new MergeSortImpl(), masterBest, masterRnd, masterWorst));

                if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                    SortMetrics manualMetrics = new MergeSortImpl().execute(quant, masterTiny.clone(), "Merge Sort");

                    manualMetrics.manualPrint();
                }
                break;

            case "quick sort", "qsort", "quick", "q":

                results.put("Quick Sort", runAndCollectMetrics(new QuickSortImpl(), masterBest, masterRnd, masterWorst));

                if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                    SortMetrics manualMetrics = new QuickSortImpl().execute(quant, masterTiny.clone(), "Quick Sort");

                    manualMetrics.manualPrint();
                }
                break;

            case "heap sort", "hsort", "heap", "h":

                results.put("Heap Sort", runAndCollectMetrics(new HeapSortImpl(), masterBest, masterRnd, masterWorst));

                if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                    SortMetrics manualMetrics = new HeapSortImpl().execute(quant, masterTiny.clone(), "Heap Sort");

                    manualMetrics.manualPrint();
                }
                break;

            case "all", "a":

                ExecutorService executor = ThreadPool.getExecutor();

                if (executor == null) {
                    throw new IllegalArgumentException("No processors available");
                }

                List<Future<List<SortMetrics>>> futures = new ArrayList<>();

                for (int i = 0; i < strategies.size(); i++) {
                    SortStrategy strategy = strategies.get(i);
                    int finalQuant = quant;
                    Callable<List<SortMetrics>> task = () -> {

                        List<SortMetrics> newStrategies = new ArrayList<>();

                        SortMetrics best = strategy.execute(finalQuant, masterBest.clone(), strategy.getSortName());
                        SortMetrics mid = strategy.execute(finalQuant, masterRnd.clone(), strategy.getSortName());
                        SortMetrics worst = strategy.execute(finalQuant, masterWorst.clone(), strategy.getSortName());

                        Collections.addAll(newStrategies, best, mid, worst);
                        return newStrategies;
                    };
                    futures.add(executor.submit(task));
                }

                for (int i = 0; i < futures.size(); i++) {

                    if (!futures.get(i).isDone()) {
                        System.out.println("Executing " + strategies.get(i).getSortName());
                    }
                    System.out.println("|||| " + strategies.get(i).getSortName() + " ||||");

                    try {
                        List<SortMetrics> metrics = futures.get(i).get(300, TimeUnit.SECONDS);
                        metrics.get(0).sortReport("Melhor caso");
                        metrics.get(1).sortReport("Médio caso");
                        metrics.get(2).sortReport("Pior caso");
                        results.put(strategies.get(i).getSortName(), metrics);

                        if (analysisService != null) {
                            try {
                                String algoName = strategies.get(i).getSortName();
                                analysisService.create(mapToEntity(metrics.get(0), algoName, "Best Case", quant));
                                analysisService.create(mapToEntity(metrics.get(1), algoName, "Average Case", quant));
                                analysisService.create(mapToEntity(metrics.get(2), algoName, "Worst Case", quant));
                            } catch (Exception e) {
                                System.err.println("  > Failed to save to DB: " + e.getMessage());
                            }
                        }

                        results.put(strategies.get(i).getSortName(), metrics);

                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
                        System.out.println(strategies.get(i).getSortName() + " Timed Out");
                        futures.get(i).cancel(true);
                    }
                }

                ThreadPool.monitor();
                break;

            default:
                System.out.println("Algoritmo não existe");
                break;
        }

        if (!results.isEmpty()) {

            DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
            LocalDateTime localDateTime = LocalDateTime.now();
            String time = localDateTime.format(dtFormat);

            File dir = new File(outDirectory);
            if (!dir.exists()) {
                dir.mkdir();
            }

            String jsonFileName = ("SortReport_" + time + "." + "json");
            File outFile = new File(dir, jsonFileName);

            try {
                om.writeValue(outFile, results);
            } catch (IOException e) {
                System.out.println("Error writing sort results");
                throw new RuntimeException(e);
            }

            System.out.println("Escolha o tipo de gráfico");
            String grafico = sc.nextLine();

            switch (grafico) {
                case "Bar Chart", "barchart", "bar":
                    //barchart
                    BarChart.toLoad = outFile.getAbsolutePath();
                    BarChart.main(new String[]{});
                    break;

                case "Scatter Chart", "scatter", "scater", "sca":
//scatterchart
                    ScatterChart.toLoad = outFile.getAbsolutePath();
                    ScatterChart.main(new String[]{});
                    break;
            }
        }

        ThreadPool.shutdown();
    }

    private static List<SortMetrics> runAndCollectMetrics(
            SortStrategy<Integer, long[], String> strategy,
            long[] masterBest,
            long[] masterAvg,
            long[] masterWorst) {

        List<SortMetrics> metricsList = new ArrayList<>();
        String algoName = strategy.getSortName();

        System.out.println("\n||||| " + algoName + " |||||\n");

        SortMetrics best = strategy.execute(quant, masterBest.clone(), strategy.getSortName());
        best.sortReport("Melhor caso");
        metricsList.add(best);

        try {
            analysisService.create(mapToEntity(best, algoName, "Best Case", quant));
        } catch (Exception e) {
            System.out.println("Error saving best case");
            throw new RuntimeException(e.getMessage());
        }

        SortMetrics avg = strategy.execute(quant, masterAvg.clone(), strategy.getSortName());
        avg.sortReport("Médio caso");
        metricsList.add(avg);

        try {
            analysisService.create(mapToEntity(avg, algoName, "Average Case", quant));
        } catch (Exception e) {
            System.out.println("Error saving average case");
            throw new RuntimeException(e.getMessage());
        }

        SortMetrics worst = strategy.execute(quant, masterWorst.clone(), strategy.getSortName());
        worst.sortReport("Pior caso");
        metricsList.add(worst);

        try {
            analysisService.create(mapToEntity(worst, algoName, "Worst Case", quant));
        } catch (Exception e) {
            System.out.println("Error saving worst case");
            throw new RuntimeException(e);
        }

        return metricsList;
    }

    private static Analysis mapToEntity(SortMetrics metrics, String algorithmName, String caseType, int inputSize) {

        JsonNode jsonMetrics = mapper.valueToTree(metrics);

        return new Analysis.Builder(
                null,
                algorithmName,
                caseType,
                jsonMetrics,
                LocalDateTime.now(),
                "ADMIN",
                new BigDecimal(inputSize)
        ).build();
    }
}
