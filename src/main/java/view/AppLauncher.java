package view;

public class AppLauncher {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments found. Defaulting to 'analysis.json'");
            args = new String[]{"analysis.json"};
        }

        BarChart.main(args);
    }
}