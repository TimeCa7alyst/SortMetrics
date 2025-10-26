package controller;

import model.algoritmos.BubbleSort;
import model.algoritmos.InsertionSort;
import model.algoritmos.SelectionSort;
import model.analise.BubbleSortImpl;
import model.analise.InsertionSortImpl;
import model.analise.SelectionSortImpl;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OrdenacaoController {

    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private SelectView selectView;
    private QuantityInputVIew quantityInputView;
    private ManualView manualView;
    private AnalysisView analysisView;
    private ExitView exitView;

    private BubbleSortImpl bubbleSort;
    private SelectionSortImpl selectionSort;
    private InsertionSortImpl insertionSort;

    private String selectedAlgo;
    private int quant;
    private long[] vet;
    public OrdenacaoController(JFrame mainFrame,JPanel mainPanel, CardLayout cardLayout, SelectView selectView,
                               QuantityInputVIew quantityInputView, ManualView manualView,
                               AnalysisView analysisView, ExitView exitView) {
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.selectView = selectView;
        this.quantityInputView = quantityInputView;
        this.manualView = manualView;
        this.analysisView = analysisView;
        this.exitView = exitView;

        this.bubbleSort = new BubbleSortImpl();
        this.selectionSort = new SelectionSortImpl();
        this.insertionSort = new InsertionSortImpl();

        //SelectView
        this.selectView.getContinuarButton().addActionListener
                (e -> onSelectViewNext());
        this.selectView.getSairButton().addActionListener
                (e -> onSelectViewExit());

        //QuantityInputView
        this.quantityInputView.getAnalisarButton().addActionListener
                (e -> onQuantityInputViewNext());
        this.quantityInputView.getSairButton2().addActionListener
                (e -> onQuantityInputViewExit());

        //ManualView
        this.manualView.getAnalisarButton2().addActionListener
                (e -> onManualViewNext());
        this.manualView.getSairButton3().addActionListener
                (e -> onManualViewExit());

        //AnalysisView
        this.analysisView.getContinuarButton2().addActionListener
                (e -> onAnalysisViewNext());
        this.analysisView.getSairButton4().addActionListener
                (e -> onAnalysisViewExit());
    }

    private void onSelectViewNext() {
        this.selectedAlgo = (String) selectView.getDropDown1().getSelectedItem();
        cardLayout.show(mainPanel, "Segunda tela");
        repackWindow();
    }

    private void onSelectViewExit() {
        cardLayout.show(mainPanel, "Quarta Tela");
        repackWindow();
    }
    private void onQuantityInputViewNext() {
        this.quant = Integer.parseInt(quantityInputView.getTextField1().getText());

        if (this.quant > 0 && this.quant <= 10) {
            this.vet = new long[this.quant];
            manualView.getLabelTitulo().setText("Digite os " + this.quant
                    + " números separados por espaços");
            manualView.getTextField1().setText("");
            cardLayout.show(mainPanel, "Tela opcional");
        } else if (this.quant > 10) {
            this.vet = null;
            runAnalysis();
            cardLayout.show(mainPanel, "Terceira Tela");
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Entrada inválida!");
        }
        repackWindow();
    }
    private void onQuantityInputViewExit() {
        cardLayout.show(mainPanel, "Quarta Tela");
        repackWindow();
    }
    private void onManualViewNext() {
        String input = manualView.getTextField1().getText();
        if (parseInputManual(input)) {
            runAnalysis();
            cardLayout.show(mainPanel, "Terceira Tela");
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Entrada inválida");
        }
        repackWindow();
    }
    private void onManualViewExit() {
        cardLayout.show(mainPanel, "Quarta Tela");
        repackWindow();
    }

    private void onAnalysisViewNext() {
        cardLayout.show(mainPanel, "Primeira Tela");
        repackWindow();
    }

    private void onAnalysisViewExit() {
        cardLayout.show(mainPanel, "Quarta Tela");
        repackWindow();
    }
    private boolean parseInputManual(String input) {
        String[] vet = input.trim().split("[\\s,]+");
        if (vet.length != this.quant) {
            return false;
        } else {
            for (int i = 0; i < vet.length; i++) {
                this.vet[i] = Long.parseLong(vet[i]);
            }
            return true;
        }
    }
    private void repackWindow() {
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }

    private void runAnalysis() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        switch (this.selectedAlgo) {
            case "Bubble Sort":
                System.out.println("\n||||| Bubble Sort |||||\n");
                bubbleSort.melhorCasoBubbleSort(this.quant);
                bubbleSort.medioCasoBubbleSort(this.quant);
                bubbleSort.piorCasoBubbleSort(this.quant);
                break;
            case "Insertion Sort":
                System.out.println("\n||||| Insertion Sort |||||\n");
                insertionSort.melhorCasoInsertionSort(this.quant);
                insertionSort.medioInsertionSort(this.quant);
                insertionSort.piorCasoInsertionSort(this.quant);
                break;
            case "Selection Sort":
                System.out.println("\n||||| Selection Sort |||||\n");
                selectionSort.melhorCasoSelectionSort(this.quant);
                selectionSort.medioCasoSelectionSort(this.quant);
                selectionSort.piorCasoSelectionSort(this.quant);
                break;
            default:
                System.out.println("Algoritmo selecionado é inválido.");
        }

        if (this.vet != null) {

            if (this.selectedAlgo.equals("Bubble Sort")) {
                BubbleSort bSortManual = new BubbleSort(this.quant, this.vet);
                bSortManual.bubbleSort();
                bSortManual.bSortManualPrint(this.vet);

            } else if (this.selectedAlgo.equals("Insertion Sort")) {
                InsertionSort iSortManual = new InsertionSort(this.quant, this.vet);
                iSortManual.insertionSort();
                iSortManual.iSortManualPrint(this.vet);

            } else if (this.selectedAlgo.equals("Selection Sort")) {
                SelectionSort sSortManual = new SelectionSort(this.quant, this.vet);
                sSortManual.selectionSort();
                sSortManual.sSortManualPrint(this.vet);
            }
        }
        System.out.flush();
        System.setOut(oldOut);
        String analiseText = baos.toString();

        analysisView.getTextArea1().setText(analiseText);
        analysisView.getTextArea1().setCaretPosition(0);
    }
}
