package view;

import application.ConsoleRun;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.analise.SortMetrics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BarChart extends Application {

    public static String toLoad;

    private static Map<String, Map<String, Number>> data = new HashMap<>();

    public static void addData(String algo, String sortCase, Number time) {
        data.computeIfAbsent(algo, k -> new HashMap<>()).put(sortCase, time);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Sorts");
        final CategoryAxis x = new CategoryAxis();
        final NumberAxis y = new NumberAxis();
        final javafx.scene.chart.BarChart<String, Number> barChart
                = new javafx.scene.chart.BarChart<>(x, y);
        String sortingTime = "Sorting time " + "(" + ConsoleRun.quant + " Numbers" + ")";
                barChart.setTitle(sortingTime);
        x.setLabel("Algorithm Name");
        y.setLabel("Time (ms)");

        if (toLoad == null || toLoad.isEmpty()) {
            System.out.println("No file path provided to load!");
            return;
        }

        ObjectMapper om = new ObjectMapper();
        HashMap<String, List<SortMetrics>> inputData = om.readValue(new File
                        (toLoad),
                new TypeReference<HashMap<String, List<SortMetrics>>>() {});

        XYChart.Series series = new XYChart.Series();
        series.setName("Worst Case");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Average Case");

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Best Case");

        for (Map.Entry<String, List<SortMetrics>> entry : inputData.entrySet()) {
            String algoName = entry.getKey();
            List<SortMetrics> metrics = entry.getValue();

            series.getData().add(new XYChart.Data<>(algoName, metrics.get(0).getTotalMs()));
            series2.getData().add(new XYChart.Data<>(algoName, metrics.get(1).getTotalMs()));
            series3.getData().add(new XYChart.Data<>(algoName, metrics.get(2).getTotalMs()));
        }

        Scene scene = new Scene(barChart, 1000, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/BarChart.css")).toExternalForm());
        barChart.getData().addAll(series, series2, series3);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
