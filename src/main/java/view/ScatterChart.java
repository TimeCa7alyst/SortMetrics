package view;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import model.analise.SortMetrics;

import java.io.File;
import java.util.*;

public class ScatterChart extends Application {

    public static String toLoad;

    private static Map<String, Map<String, Number>> data = new HashMap<>();

    public static void addData(String algo, String sortCase, Number time) {
        data.computeIfAbsent(algo, k -> new HashMap<>()).put(sortCase, time);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Scatter chart");
        final NumberAxis x = new NumberAxis();
        final NumberAxis y = new NumberAxis();
        final javafx.scene.chart.ScatterChart<Number, Number> scatter =
                new javafx.scene.chart.ScatterChart<Number, Number>(x, y);
        x.setLabel("Time (ms)");
        y.setLabel("Memory Used (MB)");
        scatter.setTitle("Sorting Algos");

        if (toLoad == null || toLoad.isEmpty()) {
            System.out.println("\"No file path provided to load!");
            return;
        }

        String[] cases = {"Best Case", "Average Case", "Worst Case"};

        ObjectMapper om = new ObjectMapper();
        HashMap<String, List<SortMetrics>> inputData = om.readValue(new File
                        (toLoad),
                new TypeReference<HashMap<String, List<SortMetrics>>>() {
                });

        for (Map.Entry<String, List<SortMetrics>> entry : inputData.entrySet()) {
            String algoName = entry.getKey();
            List<SortMetrics> metrics = entry.getValue();

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(algoName);

            for (int i = 0; i < metrics.size(); i++) {

                SortMetrics metric = metrics.get(i);

                String caseName = (i < cases.length) ? cases[i] : "Unknown case";

                double memoryMB = (double) metric.getMemoryUsed() / (1024 * 1024);

                XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(metric.getTotalMs(), Math.max(0, memoryMB));

                series.getData().add(dataPoint);

                dataPoint.nodeProperty().addListener((obs, oldNode, newNode) -> {

                    if (newNode != null) {
                        String info = String.format("%s - %s\nTime: %.2f ms\nMemory: %.2f MB",
                                algoName,
                                caseName,
                                metric.getTotalMs(),
                                memoryMB
                        );

                        Tooltip t = new Tooltip(info);

                        t.setShowDelay(javafx.util.Duration.millis(100));

                        Tooltip.install(newNode, t);
                    }
                });
            }

            scatter.getData().add(series);
        }

        Scene scene = new Scene(scatter, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
