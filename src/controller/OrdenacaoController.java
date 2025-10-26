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

    public OrdenacaoController(JFrame mainFrame, JPanel mainPanel, CardLayout cardLayout, SelectView selectView,
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

        this.selectView.getContinuarButton().addActionListener
                (e -> onSelectViewNext());
        this.selectView.getSairButton().addActionListener
                (e -> onSelectViewExit());
        this.quantityInputView.getAnalisarButton().addActionListener
                (e -> onQuantityInputViewNext());
        this.quantityInputView.getSairButton2().addActionListener
                (e -> onQuantityInputViewExit());
        this.manualView.getAnalisarButton2().addActionListener
                (e -> onManualViewNext());
        this.manualView.getSairButton3().addActionListener
                (e -> onManualViewExit());
        this.analysisView.getContinuarButton2().addActionListener
                (e -> onAnalysisViewNext());
        this.analysisView.getSairButton4().addActionListener
                (e -> onAnalysisViewExit());
    }

    private void showSmallScreen(String screenName) {
        mainFrame.setResizable(false);
        cardLayout.show(mainPanel, screenName);
        repackWindow();
    }

    private void onSelectViewNext() {
        this.selectedAlgo = (String) selectView.getDropDown1().getSelectedItem();
        showSmallScreen("Segunda tela");
    }

    private void onSelectViewExit() {
        showSmallScreen("Quarta Tela");
        exitTimer();
    }
    private void onQuantityInputViewExit() {
        showSmallScreen("Quarta Tela");
        exitTimer();
    }

    private void onManualViewExit() {
        showSmallScreen("Quarta Tela");
        exitTimer();
    }

    private void onAnalysisViewNext() {
        showSmallScreen("Primeira Tela");
        exitTimer();
    }

    private void onAnalysisViewExit() {
        cardLayout.show(mainPanel, "Quarta Tela");
        exitTimer();
        repackWindow();
    }

    private void showLargeScreen(String screenName) {
        mainFrame.setResizable(true);
        cardLayout.show(mainPanel, screenName);
        mainFrame.setSize(analysisView.getWidth(), analysisView.getHeight());
        mainFrame.setLocationRelativeTo(null);
    }

    private void onQuantityInputViewNext() {
        try {
            this.quant = Integer.parseInt(quantityInputView.getTextField1().getText());

            if (this.quant > 0 && this.quant <= 10) {
                this.vet = new long[this.quant];
                manualView.getLabelTitulo().setText("Digite os " + this.quant
                        + " números separados por espaços");
                manualView.getTextField1().setText("");
                showSmallScreen("Tela opcional");
            } else if (this.quant > 10) {
                this.vet = null;
                runAnalysis();
                showLargeScreen("Terceira Tela");
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Entrada inválida! A quantidade deve ser maior que 0.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainPanel, "Entrada inválida! Digite um número válido.",
                    "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onManualViewNext() {
        String input = manualView.getTextField1().getText();
        if (parseInputManual(input)) {
            runAnalysis();
            showLargeScreen("Terceira Tela");
        } else {
            JOptionPane.showMessageDialog(mainPanel,
                    "Entrada inválida. Digite " + this.quant + " números separados por espaço.",
                    "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean parseInputManual(String input) {
        String[] vetParts = input.trim().split("[\\s,]+");
        if (vetParts.length != this.quant) {
            return false;
        } else {
            try {
                for (int i = 0; i < vetParts.length; i++) {
                    this.vet[i] = Long.parseLong(vetParts[i]);
                }
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
    private void exitTimer() {
        Timer timer = new Timer(3000, e -> {
            System.exit(0);
        });
        timer.setRepeats(false);
        timer.start();
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
            System.out.println("\n||||| Análise com Vetor Manual |||||\n");
            long[] vetCopy = this.vet.clone();
            if (this.selectedAlgo.equals("Bubble Sort")) {
                BubbleSort bSortManual = new BubbleSort(this.quant, vetCopy);
                bSortManual.bubbleSort();
                bSortManual.bSortManualPrint(vetCopy);
            } else if (this.selectedAlgo.equals("Insertion Sort")) {
                long[] vetCopy2 = this.vet.clone();
                InsertionSort iSortManual = new InsertionSort(this.quant, vetCopy2);
                iSortManual.insertionSort();
                iSortManual.iSortManualPrint(vetCopy2);
            } else if (this.selectedAlgo.equals("Selection Sort")) {
                long[] vetCopy3 = this.vet.clone();
                SelectionSort sSortManual = new SelectionSort(this.quant, vetCopy3);
                sSortManual.selectionSort();
                sSortManual.sSortManualPrint(vetCopy3);
            }
        }
        System.out.flush();
        System.setOut(oldOut);
        String analiseText = baos.toString();

        analysisView.getTextArea1().setText(analiseText);
        analysisView.getTextArea1().setCaretPosition(0);
    }
}
