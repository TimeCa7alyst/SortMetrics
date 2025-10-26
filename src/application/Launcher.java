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

        SelectViewNetbeans selectView = new SelectViewNetbeans();

        QuantityInputViewNetbeans quantityInputView = new QuantityInputViewNetbeans();

        ManualViewNetbeans manualView = new ManualViewNetbeans();

        AnalysisViewNetbeans analysisView = new AnalysisViewNetbeans();

        ExitViewNetbeans exitView = new ExitViewNetbeans();

        mainPanel.add(selectView, "Primeira Tela");
        mainPanel.add(quantityInputView, "Segunda tela");
        mainPanel.add(manualView, "Tela opcional");
        mainPanel.add(analysisView, "Terceira Tela");
        mainPanel.add(exitView, "Quarta Tela");


        OrdenacaoController controller = new OrdenacaoController(metodosOrdenacaoFrame, mainPanel, layout,
                selectView,quantityInputView, manualView, analysisView, exitView);

        metodosOrdenacaoFrame.setContentPane(mainPanel);
        metodosOrdenacaoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        metodosOrdenacaoFrame.pack();

        metodosOrdenacaoFrame.setResizable(false);
        metodosOrdenacaoFrame.setLocationRelativeTo(null);
        metodosOrdenacaoFrame.setVisible(true);

        layout.show(mainPanel, "Primeira Tela");
    }
}
