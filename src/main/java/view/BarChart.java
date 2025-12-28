package view;

import application.ConsoleRun;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.analise.SortMetrics;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BarChart extends Application {

    public static String toLoad;
    private static boolean isFxRunning = false;

    public static void showGraph(String filePath) {
        toLoad = filePath;

        if (!isFxRunning) {
            isFxRunning = true;
            Platform.setImplicitExit(false);
            new Thread(() -> Application.launch(BarChart.class)).start();
        } else {

            Platform.runLater(() -> {
                try {
                    new BarChart().start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Sorts Analysis");
        final CategoryAxis x = new CategoryAxis();
        final NumberAxis y = new NumberAxis();
        final javafx.scene.chart.BarChart<String, Number> barChart
                = new javafx.scene.chart.BarChart<>(x, y);

        String q = (ConsoleRun.quant > 0) ? String.valueOf(ConsoleRun.quant) : "Stored";
        barChart.setTitle("Sorting Time (" + q + " Numbers)");

        x.setLabel("Algorithm");
        y.setLabel("Time (ms)");

        if (toLoad == null || toLoad.isEmpty()) {
            System.err.println("No file path provided!");
            return;
        }

        ObjectMapper om = new ObjectMapper();
        Map<String, List<SortMetrics>> inputData = om.readValue(new File(toLoad),
                new TypeReference<Map<String, List<SortMetrics>>>() {});

        XYChart.Series seriesBest = new XYChart.Series();
        seriesBest.setName("Best Case");

        XYChart.Series seriesAvg = new XYChart.Series();
        seriesAvg.setName("Average Case");

        XYChart.Series seriesWorst = new XYChart.Series();
        seriesWorst.setName("Worst Case");

        for (Map.Entry<String, List<SortMetrics>> entry : inputData.entrySet()) {
            String algoName = entry.getKey();
            List<SortMetrics> metrics = entry.getValue();

            if (metrics.size() > 0) {
                seriesBest.getData().add(new XYChart.Data<>(algoName, metrics.get(0).getTotalMs()));
            }
            if (metrics.size() > 1) {
                seriesAvg.getData().add(new XYChart.Data<>(algoName, metrics.get(1).getTotalMs()));
            }
            if (metrics.size() > 2) {
                seriesWorst.getData().add(new XYChart.Data<>(algoName, metrics.get(2).getTotalMs()));
            }
        }

        Scene scene = new Scene(barChart, 1000, 800);

        try {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/BarChart.css")).toExternalForm());
        } catch (Exception e) {
            System.out.println("Warning: BarChart.css not found, using default style.");
        }

        barChart.getData().addAll(seriesBest, seriesAvg, seriesWorst);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}