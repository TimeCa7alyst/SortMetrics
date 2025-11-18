package application;


import model.algoritmos.*;
import model.analise.*;
import model.util.*;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleRun {
    public static void main(String[] args) {
        int quant;
        Scanner sc = new Scanner(System.in);

        String algoritmo;

        do {
            System.out.println("""
                    Escolha um dos algoritmos:
                    
                    Bubble Sort
                    Selection Sort
                    Insertion Sort
                    Merge Sort
                    Quick Sort
                    Heap Sort
                    
                    OU digite "sair" para fechar o programa""");
            algoritmo = sc.nextLine().toLowerCase();

            if (algoritmo.equals("sair")
                    || (algoritmo.equals("fechar") || (algoritmo.equals("fechar programa")))) {
                System.exit(0);
            }

            while (true) {
                System.out.println("Quantidade de números para serem ordenados:");
                try {
                    quant = Integer.parseInt(sc.nextLine());
                    if (quant < 0) {
                        System.out.println("||| Quantidade deve ser maior que zero! |||\n");
                    } else {
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("||| Entrada inválida |||");
                }
            }

            DefineTipoQuantidade defTipo = new DefineTipoQuantidade();
            defTipo.defineTipo(quant);
            long[] vet = new long[quant];

            if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {

                for (int i = 0; i < quant; i++) {
                    while (true) {
                        try {
                            System.out.printf("Digite o %d valor: ", (i + 1));
                            vet[i] = Long.parseLong(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("||| Entrada Inválida |||");
                        }
                    }
                }
            } else {

                for (int i = 0; i < vet.length; i++) {
                    vet[i] = RandomGenerator.randomGenerator();
                }
            }

            switch (algoritmo) {
                case "bubble sort", "bsort", "bubble", "b", "buble sort":
                    BubbleSortImpl bubbleSort = new BubbleSortImpl();

                    System.out.println("\n||||| Bubble Sort |||||\n");

                    SortMetrics melhor = bubbleSort.melhorCasoBubbleSort(quant);
                    melhor.sortReport("Melhor caso");

                    SortMetrics medio = bubbleSort.medioCasoBubbleSort(quant);
                    medio.sortReport("Medio caso");

                    SortMetrics pior = bubbleSort.piorCasoBubbleSort(quant);
                    pior.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        BubbleSort bSortManual = new BubbleSort(quant, vet);
                        Map<String, Long> metrics = bSortManual.bubbleSort();

                        long comp = metrics.get("compNumber");
                        long swap = metrics.get("swapNumber");
                        SortMetrics sortMetrics = new SortMetrics(vet, swap, comp, 0, true);

                        sortMetrics.manualPrint();
                    }
                    break;

                case "insertion sort", "isort", "insertion", "i":
                    InsertionSortImpl insertionSort = new InsertionSortImpl();

                    System.out.println("\n||||| Insertion Sort |||||\n");

                    SortMetrics melhorInsertion = insertionSort.melhorCasoInsertionSort(quant);
                    melhorInsertion.sortReport("Melhor caso");

                    SortMetrics medioInsertion = insertionSort.medioInsertionSort(quant);
                    medioInsertion.sortReport("Medio caso");

                    SortMetrics piorInsertion = insertionSort.piorCasoInsertionSort(quant);
                    piorInsertion.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        InsertionSort iSortManual = new InsertionSort(quant, vet);
                        Map<String, Long> metrics = iSortManual.insertionSort();

                        long comp = metrics.get("compNumber");
                        long swap = metrics.get("swapNumber");
                        SortMetrics sortMetrics = new SortMetrics(vet, swap, comp, 0, true);

                        sortMetrics.manualPrint();
                    }
                    break;

                case "selection sort", "ssort", "selection", "s":
                    SelectionSortImpl selectionSort = new SelectionSortImpl();

                    System.out.println("\n||||| Selection Sort |||||\n");

                    SortMetrics melhorSelection = selectionSort.melhorCasoSelectionSort(quant);
                    melhorSelection.sortReport("Melhor caso");

                    SortMetrics medioSelection = selectionSort.medioCasoSelectionSort(quant);
                    medioSelection.sortReport("Medio caso");

                    SortMetrics piorSelection = selectionSort.piorCasoSelectionSort(quant);
                    piorSelection.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        SelectionSort sSortManual = new SelectionSort(quant, vet);
                        Map<String, Long> metrics = sSortManual.selectionSort();

                        long comp = metrics.get("compNumber");
                        long swap = metrics.get("swapNumber");
                        SortMetrics sortMetrics = new SortMetrics(vet, swap, comp, 0, true);

                        sortMetrics.manualPrint();
                    }
                    break;

                case "merge sort", "msort", "merge", "m":
                    MergeSortImpl mergeSort = new MergeSortImpl();

                    System.out.println("\n||||| Merge Sort |||||\n");

                    SortMetrics melhorMerge = mergeSort.melhorCasoMergeSort(quant);
                    melhorMerge.sortReportNoSwaps("Melhor caso");

                    SortMetrics medioMerge = mergeSort.medioCasoMergeSort(quant);
                    medioMerge.sortReportNoSwaps("Medio caso");

                    SortMetrics piorMerge = mergeSort.piorCasoMergeSort(quant);
                    piorMerge.sortReportNoSwaps("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        MergeSort mSortManual = new MergeSort(quant, vet);
                        mSortManual.executeMergeSort(vet);
                        mSortManual.mSortManualPrint(vet);
                    }
                    break;

                case "quick sort", "qsort", "quick", "q":
                    QuickSortImpl quickSort = new QuickSortImpl();

                    System.out.println("\n||||| Quick Sort |||||\n");

                    SortMetrics melhorQuick = quickSort.melhorCasoQuickSort(quant);
                    melhorQuick.sortReport("Melhor caso");

                    SortMetrics medioQuick = quickSort.medioCasoQuickSort(quant);
                    medioQuick.sortReport("Medio caso");

                    SortMetrics piorQuick = quickSort.piorCasoQuickSort(quant);
                    piorQuick.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        QuickSort qSortManual = new QuickSort(quant, vet);
                        qSortManual.qSortManualPrint(vet);
                    }
                    break;

                case "heap sort", "hsort", "heap", "h":
                    HeapSortImpl heapSort = new HeapSortImpl();

                    System.out.println("\n||||| Heap Sort |||||\n");

                    SortMetrics melhorHeap = heapSort.melhorCasoHeapSort(quant);
                    melhorHeap.sortReport("Melhor caso");

                    SortMetrics medioHeap = heapSort.medioCasoHeapSort(quant);
                    medioHeap.sortReport("Medio caso");

                    SortMetrics piorHeap = heapSort.piorCasoHeapSort(quant);
                    piorHeap.sortReport("Pior caso");

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        HeapSort hSortManual = new HeapSort(quant, vet);
                        hSortManual.hSortManualPrint(vet);
                    }
                    break;

                default:
                    System.out.println("Algoritmo não existe");
                    break;
            }

            System.out.println();

            System.out.println("Deseja continuar? (s/n)");
            String resposta = sc.nextLine().toLowerCase();

            if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                break;
            } else {
                System.out.println("-----------------------------------\n");
            }
        }

        while (true);
    }
}
