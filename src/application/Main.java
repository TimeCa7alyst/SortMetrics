package application;


import model.algoritmos.*;
import model.analise.*;
import model.util.*;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int quant;
        Scanner sc = new Scanner(System.in);

        String algoritmo;

        do {
            System.out.println("Escolha um dos algoritmos:\n\n" +
                    "Bubble Sort\n" +
                    "Selection Sort\n" +
                    "Insertion Sort\n" +
                    "Merge Sort\n" +
                    "Quick Sort\n" +
                    "Heap Sort\n\n" +
                    "OU digite \"sair\" para fechar o programa");
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

                    bubbleSort.melhorCasoBubbleSort(quant);
                    bubbleSort.medioCasoBubbleSort(quant);
                    bubbleSort.piorCasoBubbleSort(quant);

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        BubbleSort bSortManual = new BubbleSort(quant, vet);
                        bSortManual.bubbleSort();
                        bSortManual.bSortManualPrint(vet);
                    }
                    break;

                case "insertion sort", "isort", "insertion", "i":
                    InsertionSortImpl insertionSort = new InsertionSortImpl();

                    System.out.println("\n||||| Insertion Sort |||||\n");

                    insertionSort.melhorCasoInsertionSort(quant);
                    insertionSort.medioInsertionSort(quant);
                    insertionSort.piorCasoInsertionSort(quant);

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        InsertionSort iSortManual = new InsertionSort(quant, vet);
                        iSortManual.insertionSort();
                        iSortManual.iSortManualPrint(vet);
                    }
                    break;

                case "selection sort", "ssort", "selection", "s":
                    SelectionSortImpl selectionSort = new SelectionSortImpl();

                    System.out.println("\n||||| Selection Sort |||||\n");

                    selectionSort.melhorCasoSelectionSort(quant);
                    selectionSort.medioCasoSelectionSort(quant);
                    selectionSort.piorCasoSelectionSort(quant);

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        SelectionSort sSortManual = new SelectionSort(quant, vet);
                        sSortManual.selectionSort();
                        sSortManual.sSortManualPrint(vet);
                    }
                    break;

                case "merge sort", "msort", "merge", "m":
                    MergeSortImpl mergeSort = new MergeSortImpl();

                    System.out.println("\n||||| Merge Sort |||||\n");

                    mergeSort.melhorCasoMergeSort(quant);
                    mergeSort.medioCasoMergeSort(quant);
                    mergeSort.piorCasoMergeSort(quant);

                    if (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        MergeSort mSortManual = new MergeSort(quant, vet);
                        mSortManual.mSortManualPrint(vet);
                    }
                    break;

                case "quick sort", "qsort", "quick", "q":
                    QuickSortImpl quickSort = new QuickSortImpl();

                    System.out.println("\n||||| Quick Sort |||||\n");

                    quickSort.melhorCasoQuickSort(quant);
                    quickSort.medioCasoQuickSort(quant);
                    quickSort.piorCasoQuickSort(quant);

                    if  (Objects.equals(String.valueOf(defTipo.getTipoQuantidade()), "PEQUENA")) {
                        QuickSort qSortManual = new QuickSort(quant, vet);
                        qSortManual.qSortManualPrint(vet);
                    }
                    break;

                case "heap sort", "hsort", "heap", "h":
                    HeapSortImpl heapSort = new HeapSortImpl();

                    System.out.println("\n||||| Heap Sort |||||\n");

                    heapSort.melhorCasoHeapSort(quant);
                    heapSort.piorCasoHeapSort(quant);
                    heapSort.medioCasoHeapSort(quant);

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
