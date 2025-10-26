package application;

import controller.OrdenacaoController;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Launcher {

    public static void main(String[] args) {

        JFrame metodosOrdenacaoFrame = new JFrame("Algoritmos de Ordenação");

        CardLayout layout = new CardLayout();

        JPanel mainPanel = new JPanel(layout);

        SelectView selectView = new SelectView();

        QuantityInputVIew quantityInputView = new QuantityInputVIew();

        ManualView manualView = new ManualView();

        AnalysisView analysisView = new AnalysisView();

        ExitView exitView = new ExitView();

        mainPanel.add(selectView.getMainPanel(), "Primeira Tela");
        mainPanel.add(quantityInputView.getMainPanel(), "Segunda tela");
        mainPanel.add(manualView.getMainPanel(), "Tela opcional");
        mainPanel.add(analysisView.getMainPanel(), "Terceira Tela");
        mainPanel.add(exitView.getMainPanel(), "Quarta Tela");


        OrdenacaoController controller = new OrdenacaoController(metodosOrdenacaoFrame, mainPanel, layout,
                selectView,quantityInputView, manualView, analysisView, exitView);

        metodosOrdenacaoFrame.setContentPane(mainPanel);
        metodosOrdenacaoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        metodosOrdenacaoFrame.setResizable(false);
        metodosOrdenacaoFrame.pack();
        metodosOrdenacaoFrame.setLocationRelativeTo(null);
        metodosOrdenacaoFrame.setVisible(true);

        layout.show(mainPanel, "Primeira Tela");



    }
}
