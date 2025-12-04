package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class BarChart extends Application {
    final static String bubble = "Bubble Sort";
    final static String selection = "Selection Sort";
    final static String insertion = "Insertion Sort";

    private static Map<String, Map<String, Number>> data = new HashMap<>();

    public static void addData(String algo, String sortCase, Number time) {
        data.computeIfAbsent(algo, k -> new HashMap<>()).put(sortCase, time);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sorts");
        final CategoryAxis x = new CategoryAxis();
        final NumberAxis y = new NumberAxis();
        final javafx.scene.chart.BarChart<String, Number> barChart
                = new javafx.scene.chart.BarChart<>(x, y);
        barChart.setTitle("Sorting time");
        x.setLabel("Sorting Algo");
        y.setLabel("Time (ms)");

        XYChart.Series series = new XYChart.Series();
        series.setName("Melhor caso");
        series.getData().add(new XYChart.Data<String, Number>(bubble, 1000));
        series.getData().add(new XYChart.Data<String, Number>(selection, 2000));
        series.getData().add(new XYChart.Data<String, Number>(insertion, 4000));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Médio caso");
        series2.getData().add(new XYChart.Data<String, Number>(bubble, 5000));
        series2.getData().add(new XYChart.Data<String, Number>(selection, 6000));
        series2.getData().add(new XYChart.Data<String, Number>(insertion, 12000));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Pior caso");
        series3.getData().add(new XYChart.Data<String, Number>(bubble, 10000));
        series3.getData().add(new XYChart.Data<String, Number>(selection, 20000));
        series3.getData().add(new XYChart.Data<String, Number>(insertion, 40000));

        Scene scene = new Scene(barChart, 800, 600);
        barChart.getData().addAll(series, series2, series3);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
