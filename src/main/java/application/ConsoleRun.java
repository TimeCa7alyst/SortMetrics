package application;

import DAO.AnalysisDAO;
import DB.DbManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.util.stream.Collectors;

public class ConsoleRun {
    public static int quant;
    static ObjectMapper mapper = new ObjectMapper();
    static AnalysisDAO analysisDAO;
    static AnalysisService analysisService;

    static Map<String, SortStrategy<Integer, long[], String>> strategyRegistry = new LinkedHashMap<>();

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            analysisDAO = new AnalysisDAO(DbManager.getConnection(), mapper);
            analysisService = new AnalysisService(analysisDAO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        registerStrategy(new BubbleSortImpl(), "bubble sort", "bsort", "bubble", "b");
        registerStrategy(new InsertionSortImpl(), "insertion sort", "isort", "insertion", "i");
        registerStrategy(new SelectionSortImpl(), "selection sort", "ssort", "selection", "s");
        registerStrategy(new MergeSortImpl(), "merge sort", "msort", "merge", "m");
        registerStrategy(new QuickSortImpl(), "quick sort", "qsort", "quick", "q");
        registerStrategy(new HeapSortImpl(), "heap sort", "hsort", "heap", "h");
    }

    private static void registerStrategy(SortStrategy<Integer, long[], String> strategy, String... aliases) {
        for (String alias : aliases) {
            strategyRegistry.put(alias, strategy);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        String outDirectory = "src" + File.separator + "out";

        new File(outDirectory).mkdirs();

        while (true) {
            System.out.println("""
                    \n================================
                    Choose operation:
                    1 - New Analysis (Run Algorithms)
                    2 - List All Analysis (Graph / Delete)
                    3 - Exit
                    ================================""");

            String choice = sc.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "new" -> runNewAnalysis(sc, outDirectory);
                case "2", "list" -> handleListAndCrud(sc, outDirectory);
                case "3", "exit" -> {
                    System.out.println("Exiting...");
                    ThreadPool.shutdown();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void handleListAndCrud(Scanner sc, String outDirectory) {
        List<Analysis> list = analysisService.findAll();

        if (list.isEmpty()) {
            System.out.println("No history found.");
            return;
        }

        System.out.println("\n--- Analysis History ---");
        System.out.printf("%-5s | %-15s | %-15s | %-20s%n", "ID", "Algorithm", "Case", "Date");
        System.out.println("----------------------------------------------------------------");
        for (Analysis a : list) {
            System.out.printf("%-5d | %-15s | %-15s | %s%n",
                    a.getId(),
                    a.getAlgorithm(),
                    a.getAlgoCase(),
                    a.getDate() != null ? a.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "N/A"
            );
        }

        System.out.println("""
                \nOptions:
                [G]raph specific IDs (Range)
                [D]elete an ID
                [B]ack to Main Menu
                """);
        String action = sc.nextLine().toLowerCase();

        switch (action) {
            case "g", "graph", "range" -> {
                try {
                    System.out.println("Enter start of range ID:");
                    int start = Integer.parseInt(sc.nextLine());

                    System.out.println("Enter end of range ID:");
                    int end = Integer.parseInt(sc.nextLine());

                    List<Analysis> selected = list.stream()
                            .filter(a -> a.getId() >= start && a.getId() <= end)
                            .collect(Collectors.toList());

                    if (!selected.isEmpty()) {
                        generateGraphFromHistory(selected, sc, outDirectory);
                    } else {
                        System.out.println("No valid IDs found in that range.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format.");
                }
            }
            case "d", "delete" -> {
                System.out.println("Enter ID to delete:");
                try {
                    int idToDelete = Integer.parseInt(sc.nextLine());
                    analysisService.delete(idToDelete);
                    System.out.println("Analysis deleted successfully.");
                } catch (Exception e) {
                    System.out.println("Error deleting: " + e.getMessage());
                }
            }
            case "b", "back" -> {}
            default -> System.out.println("Invalid action.");
        }
    }

    private static void runNewAnalysis(Scanner sc, String outDirectory) throws ExecutionException, InterruptedException {
        System.out.println("""
                Choose an algorithm:
                (Bubble, Selection, Insertion, Merge, Quick, Heap)
                OR type 'All' to run benchmark
                OR type 'exit' to return""");

        String input = sc.nextLine().toLowerCase().trim();

        if (input.equals("exit") || input.equals("back")) return;

        while (true) {
            System.out.println("Quantity of numbers to sort:");
            try {
                quant = Integer.parseInt(sc.nextLine());
                if (quant > 0) break;
                System.out.println("||| Quantity must be > 0 |||");
            } catch (NumberFormatException e) {
                System.out.println("||| Invalid Input |||");
            }
        }

        DefineTipoQuantidade defTipo = new DefineTipoQuantidade();
        defTipo.defineTipo(quant);
        boolean isSmall = Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA");

        long[] masterRnd = CaseGenerator.genAvgCase(quant);
        long[] masterBest = CaseGenerator.genBestCase(quant);
        long[] masterWorst = CaseGenerator.genWorstCase(quant);
        long[] masterTiny = new long[quant];

        if (isSmall) {
            for (int i = 0; i < quant; i++) {
                while (true) {
                    try {
                        System.out.printf("Type value %d: ", (i + 1));
                        masterTiny[i] = Long.parseLong(sc.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("||| Invalid Number |||");
                    }
                }
            }
        }

        Map<String, List<SortMetrics>> results = new HashMap<>();

        if (input.equals("all") || input.equals("a")) {
            runAllAlgorithms(masterBest, masterRnd, masterWorst, results);
        } else {
            SortStrategy<Integer, long[], String> strategy = strategyRegistry.get(input);

            if (strategy != null) {
                System.out.println("\n||||| " + strategy.getSortName() + " |||||\n");

                results.put(strategy.getSortName(),
                        runAndCollectMetrics(strategy, masterBest, masterRnd, masterWorst));

                if (isSmall) {
                    SortMetrics manualMetrics = strategy.execute(quant, masterTiny.clone(), strategy.getSortName());
                    manualMetrics.manualPrint();
                }
            } else {
                System.out.println("Algorithm not found.");
                return;
            }
        }

        if (!results.isEmpty()) {
            saveAndLaunchGraph(results, sc, outDirectory);
        }
    }

    private static void runAllAlgorithms(long[] masterBest, long[] masterRnd, long[] masterWorst, Map<String, List<SortMetrics>> results) throws InterruptedException, ExecutionException {
        ExecutorService executor = ThreadPool.getExecutor();

        Map<String, Future<List<SortMetrics>>> futuresMap = new LinkedHashMap<>();

        Set<SortStrategy<Integer, long[], String>> uniqueStrategies = new HashSet<>(strategyRegistry.values());

        for (SortStrategy<Integer, long[], String> strategy : uniqueStrategies) {
            int finalQuant = quant;
            String algoName = strategy.getSortName();

            Callable<List<SortMetrics>> task = () -> {
                List<SortMetrics> sMetrics = new ArrayList<>();
                sMetrics.add(strategy.execute(finalQuant, masterBest.clone(), algoName));
                sMetrics.add(strategy.execute(finalQuant, masterRnd.clone(), algoName));
                sMetrics.add(strategy.execute(finalQuant, masterWorst.clone(), algoName));
                return sMetrics;
            };

            futuresMap.put(algoName, executor.submit(task));
        }

        for (Map.Entry<String, Future<List<SortMetrics>>> entry : futuresMap.entrySet()) {
            String name = entry.getKey();
            Future<List<SortMetrics>> f = entry.getValue();

            try {
                List<SortMetrics> m = f.get(300, TimeUnit.SECONDS);

                System.out.println("|||| " + name + " ||||");

                if (m.size() >= 3) {
                    m.get(0).sortReport("Best Case");
                    m.get(1).sortReport("Avg Case");
                    m.get(2).sortReport("Worst Case");
                }

                results.put(name, m);

                saveMetricsListToDB(m, name);

            } catch (TimeoutException e) {
                System.out.println("Task for " + name + " timed out.");
                f.cancel(true);
            }
        }
    }

    private static void saveMetricsListToDB(List<SortMetrics> metrics, String algoName) {
        if (analysisService == null) return;
        try {
            analysisService.create(mapToEntity(metrics.get(0), algoName, "Best Case", quant));
            analysisService.create(mapToEntity(metrics.get(1), algoName, "Average Case", quant));
            analysisService.create(mapToEntity(metrics.get(2), algoName, "Worst Case", quant));
        } catch (Exception e) {
            System.err.println("Failed to save DB: " + e.getMessage());
        }
    }

    private static List<SortMetrics> runAndCollectMetrics(
            SortStrategy<Integer, long[], String> strategy,
            long[] masterBest,
            long[] masterAvg,
            long[] masterWorst) {

        List<SortMetrics> metricsList = new ArrayList<>();
        String algoName = strategy.getSortName();

        SortMetrics best = strategy.execute(quant, masterBest.clone(), algoName);
        best.sortReport("Best Case");
        metricsList.add(best);

        SortMetrics avg = strategy.execute(quant, masterAvg.clone(), algoName);
        avg.sortReport("Average Case");
        metricsList.add(avg);

        SortMetrics worst = strategy.execute(quant, masterWorst.clone(), algoName);
        worst.sortReport("Worst Case");
        metricsList.add(worst);

        saveMetricsListToDB(metricsList, algoName);

        return metricsList;
    }

    private static void saveAndLaunchGraph(Map<String, List<SortMetrics>> results, Scanner sc, String outDirectory) {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String jsonFileName = "SortReport_" + LocalDateTime.now().format(dtFormat) + ".json";
        File outFile = new File(outDirectory, jsonFileName);

        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(outFile, results);
            launchGraphSelection(sc, outFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing results: " + e.getMessage());
        }
    }

    private static void generateGraphFromHistory(List<Analysis> analysisList, Scanner sc, String outDirectory) {
        try {
            Map<String, List<SortMetrics>> results = new HashMap<>();
            for (Analysis a : analysisList) {
                SortMetrics metrics = mapper.treeToValue(a.getJsonFile(), SortMetrics.class);
                results.computeIfAbsent(a.getAlgorithm(), k -> new ArrayList<>()).add(metrics);
            }

            String jsonFileName = "History_Graph_" + System.currentTimeMillis() + ".json";
            File outFile = new File(outDirectory, jsonFileName);

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(outFile, results);

            launchGraphSelection(sc, outFile.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Error processing history: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void launchGraphSelection(Scanner sc, String filePath) {
        System.out.println("Select Graph Type (Bar Chart / Scatter Chart):");
        String type = sc.nextLine().toLowerCase();

        switch (type) {
            case "bar chart", "barchart", "bar", "b" -> {
                BarChart.showGraph(filePath);
            }
            case "scatter chart", "scatter", "scater", "sca", "s" -> {
                ScatterChart.toLoad = filePath;
                ScatterChart.main(new String[]{});
            }
            default -> System.out.println("Invalid graph type.");
        }
    }

    private static Analysis mapToEntity(SortMetrics metrics, String algorithmName, String caseType, int inputSize) {
        JsonNode jsonMetrics = mapper.valueToTree(metrics);
        return new Analysis.Builder(
                null, algorithmName, caseType, jsonMetrics,
                LocalDateTime.now(), "ADMIN", new BigDecimal(inputSize)
        ).build();
    }
}