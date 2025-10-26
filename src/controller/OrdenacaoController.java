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
    private SelectViewNetbeans selectView;
    private QuantityInputViewNetbeans quantityInputView;
    private ManualViewNetbeans manualView;
    private AnalysisViewNetbeans analysisView;
    private ExitViewNetbeans exitView;

    private BubbleSortImpl bubbleSort;
    private SelectionSortImpl selectionSort;
    private InsertionSortImpl insertionSort;

    private String selectedAlgo;
    private int quant;
    private long[] vet;

    public OrdenacaoController(JFrame mainFrame, JPanel mainPanel, CardLayout cardLayout,
                               SelectViewNetbeans selectView, QuantityInputViewNetbeans quantityInputView,
                               ManualViewNetbeans manualView, AnalysisViewNetbeans analysisView, ExitViewNetbeans exitView) {
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
                (e -> onSelectViewNetbeansNext());

        this.selectView.getSairButton().addActionListener
                (e -> onSelectViewNetbeansExit());

        this.quantityInputView.getAnalisarButton().addActionListener
                (e -> onQuantityInputViewNetbeansNext());

        this.quantityInputView.getSairButton2().addActionListener
                (e -> onQuantityInputViewNetbeansExit());

        this.manualView.getAnalisarButton2().addActionListener
                (e -> onManualViewNetbeansNext());

        this.manualView.getSairButton3().addActionListener
                (e -> onManualViewNetbeansExit());

        this.analysisView.getContinuarButton2().addActionListener
                (e -> onAnalysisViewNetbeansNext());

        this.analysisView.getSairButton4().addActionListener
                (e -> onAnalysisViewNetbeansExit());
    }

    private void showSmallScreen(String screenName, JPanel padraoPanel) {
        mainFrame.setResizable(false);
    cardLayout.show(mainPanel, screenName);
    Dimension tamanhoInicial = selectView.getPreferredSize();
    mainFrame.setSize(tamanhoInicial.width + 20, tamanhoInicial.height + 40);
    mainFrame.setLocationRelativeTo(null);
    }
    private void showLargeScreen(String screenName) {
        mainFrame.setResizable(false);
    cardLayout.show(mainPanel, screenName);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
    }

    private void onSelectViewNetbeansNext() {
        this.selectedAlgo = (String) selectView.getDropDown1().getSelectedItem();
        showSmallScreen("Segunda tela", quantityInputView);
    }

    private void onSelectViewNetbeansExit() {
        showSmallScreen("Quarta Tela", exitView);
        exitTimer();
    }
    private void onQuantityInputViewNetbeansExit() {
        showSmallScreen("Quarta Tela", exitView);
        exitTimer();
    }

    private void onManualViewNetbeansExit() {
        showSmallScreen("Quarta Tela", exitView);
        exitTimer();
    }

    private void onAnalysisViewNetbeansNext() {
        showSmallScreen("Primeira Tela", selectView);
    }

    private void onAnalysisViewNetbeansExit() {
        showSmallScreen("Quarta Tela", exitView);
        exitTimer();
    }
    private void onQuantityInputViewNetbeansNext() {
        try {
            this.quant = Integer.parseInt(quantityInputView.getTextField1().getText());

            if (this.quant > 0 && this.quant <= 10) {
                this.vet = new long[this.quant];
                manualView.getLabelTitulo().setText("Digite os " + this.quant
                        + " números separados por espaços ou vírgulas");
                manualView.getTextField1().setText("");
                showSmallScreen("Tela opcional", manualView);
            } else if (this.quant > 10) {
                this.vet = null;
                rodarAnalysis();
                showLargeScreen("Terceira Tela");
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Entrada inválida! A quantidade deve ser maior que 0.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainPanel, "Entrada inválida! Digite um número válido.",
                    "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onManualViewNetbeansNext() {
        String input = manualView.getTextField1().getText();
        if (parseInputManual(input)) {
            rodarAnalysis();
            showLargeScreen("Terceira Tela");
        } else {
            JOptionPane.showMessageDialog(mainPanel,
                    "Entrada inválida. Digite " + this.quant + " números separados por espaço ou vírgula.",
                    "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* utlizamos expressoes regulares (regex) para cortar a string de input do usuario em diversos valores
    possibilitando analizar cada entrada separadamente */

    //"corte a string em um novo pedaço sempre que houver um espaço em branco ou uma vírgula"
    private boolean parseInputManual(String input) {
        String[] vetDivisor = input.trim().split("[\\s,]+");
        if (vetDivisor.length != this.quant) {
            return false;
        } else {
            try {
                for (int i = 0; i < vetDivisor.length; i++) {
                    this.vet[i] = Long.parseLong(vetDivisor[i]);
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

    private void rodarAnalysis() {
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
    }
}