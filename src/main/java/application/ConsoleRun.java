package application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.analise.*;
import model.util.*;
import view.BarChart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

public class ConsoleRun {
    public static int quant;
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String outDirectory = "src" + File.separator + "out";
        Scanner sc = new Scanner(System.in);

        String algoritmo;

        do {
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

            switch (algoritmo) {
                case "bubble sort", "bsort", "bubble", "b", "buble sort":

                    System.out.println("\n||||| Bubble Sort |||||\n");

                    SortStrategy<Integer, long[], String> bubble = new BubbleSortImpl();

                    SortMetrics melhor = bubble.execute(quant, masterBest.clone(), "Bubble Sort");
                    melhor.sortReport("Melhor caso");

                    SortMetrics medio = bubble.execute(quant, masterRnd.clone(), "Bubble Sort");
                    medio.sortReport("Medio caso");

                    SortMetrics pior = bubble.execute(quant, masterWorst.clone(), "Bubble Sort");
                    pior.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                        SortMetrics manualMetrics = bubble.execute(quant, masterTiny.clone(), "Bubble Sort");

                        manualMetrics.manualPrint();
                    }
                    break;

                case "insertion sort", "isort", "insertion", "i":

                    System.out.println("\n||||| Insertion Sort |||||\n");

                    SortStrategy<Integer, long[], String> insertion = new InsertionSortImpl();

                    SortMetrics melhorInsertion = insertion.execute(quant, masterBest.clone(), "Insertion Sort");
                    melhorInsertion.sortReport("Melhor caso");

                    SortMetrics medioInsertion = insertion.execute(quant, masterRnd.clone(), "Insertion Sort");
                    medioInsertion.sortReport("Medio caso");

                    SortMetrics piorInsertion = insertion.execute(quant, masterWorst.clone(), "Insertion Sort");
                    piorInsertion.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                        SortMetrics manualMetrics = insertion.execute(quant, masterTiny.clone(), "Insertion Sort");

                        manualMetrics.manualPrint();
                    }
                    break;

                case "selection sort", "ssort", "selection", "s":

                    System.out.println("\n||||| Selection Sort |||||\n");

                    SortStrategy<Integer, long[], String> selection = new SelectionSortImpl();

                    SortMetrics melhorSelection = selection.execute(quant, masterBest.clone(), "Selection Sort");
                    melhorSelection.sortReport("Melhor caso");

                    SortMetrics medioSelection = selection.execute(quant, masterRnd.clone(), "Selection Sort");
                    medioSelection.sortReport("Medio caso");

                    SortMetrics piorSelection = selection.execute(quant, masterWorst.clone(), "Selection Sort");
                    piorSelection.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                        SortMetrics manualMetrics = selection.execute(quant, masterTiny.clone(), "Selection Sort");

                        manualMetrics.manualPrint();
                    }
                    break;

                case "merge sort", "msort", "merge", "m":

                    System.out.println("\n||||| Merge Sort |||||\n");

                    SortStrategy<Integer, long[], String> merge = new MergeSortImpl();

                    SortMetrics melhorMerge = merge.execute(quant, masterBest.clone(), "Merge Sort");
                    melhorMerge.sortReport("Melhor caso");

                    SortMetrics medioMerge = merge.execute(quant, masterRnd.clone(), "Merge Sort");
                    medioMerge.sortReport("Medio caso");

                    SortMetrics piorMerge = merge.execute(quant, masterWorst.clone(), "Merge Sort");
                    piorMerge.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                        SortMetrics manualMetrics = merge.execute(quant, masterTiny.clone(), "Merge Sort");

                        manualMetrics.manualPrint();
                    }
                    break;

                case "quick sort", "qsort", "quick", "q":
                    QuickSortImpl quickSort = new QuickSortImpl();

                    System.out.println("\n||||| Quick Sort |||||\n");

                    SortStrategy<Integer, long[], String> quick = new QuickSortImpl();

                    SortMetrics melhorQuick = quickSort.execute(quant, masterBest.clone(), "Quick Sort");
                    melhorQuick.sortReport("Melhor caso");

                    SortMetrics medioQuick = quickSort.execute(quant, masterRnd.clone(), "Quick Sort");
                    medioQuick.sortReport("Medio caso");

                    SortMetrics piorQuick = quickSort.execute(quant, masterWorst.clone(), "Quick Sort");
                    piorQuick.sortReport("Pior caso");


                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                        SortMetrics manualMetrics = quickSort.execute(quant, masterBest.clone(), "Quick Sort");

                        manualMetrics.manualPrint();
                    }
                    break;

                case "heap sort", "hsort", "heap", "h":

                    System.out.println("\n||||| Heap Sort |||||\n");

                    SortStrategy<Integer, long[], String> heap = new HeapSortImpl();

                    SortMetrics melhorHeap = heap.execute(quant, masterBest.clone(), "Heap Sort");
                    melhorHeap.sortReport("Melhor caso");

                    SortMetrics medioHeap = heap.execute(quant, masterRnd.clone(), "Heap Sort");
                    medioHeap.sortReport("Medio caso");

                    SortMetrics piorHeap = heap.execute(quant, masterWorst.clone(), "Heap Sort");
                    piorHeap.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                        SortMetrics manualMetrics = heap.execute(quant, masterTiny.clone(), "Heap Sort");

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

                    Map<String, List<SortMetrics>> results = new HashMap<>();

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

                        } catch (InterruptedException | ExecutionException | TimeoutException e) {
                            System.out.println(strategies.get(i).getSortName() + " Timed Out");
                            futures.get(i).cancel(true);
                        }
                    }

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

                    BarChart.toLoad = outFile.getAbsolutePath();
                    BarChart.main(new String[]{});
                    System.exit(0);

                    ThreadPool.monitor();
                    break;

                default:
                    System.out.println("Algoritmo não existe");
                    break;
            }

            System.out.println();

            System.out.println("Deseja continuar? (s/n)");
            String resposta = sc.nextLine().toLowerCase();

            if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                break;
            } else {
                System.out.println("-----------------------------------\n");
            }
        }

        while (true);
        ThreadPool.shutdown();
    }
}
