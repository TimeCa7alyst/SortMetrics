package application;


import entities.*;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String algoritmo;

        do {
            System.out.println("Escolha um dos algoritmos:\n\n" +
                    "Bubble Sort\n" +
                    "Selection Sort\n" +
                    "Insertion Sort\n\n" +
                    "OU digite \"sair\" para fechar o programa");
            algoritmo = sc.nextLine().toLowerCase();

            if (algoritmo.equals("sair")
                    || (algoritmo.equals("fechar") || (algoritmo.equals("fechar programa")))) {
                System.exit(0);
            }


            System.out.println("quantidade de números para serem ordenados:");
            int quant = Integer.parseInt(sc.nextLine());


            DefineTipo defTipo = new DefineTipo();
            defTipo.defineTipo(quant);
            long[] vet = new long[quant];

            if (Objects.equals(String.valueOf(defTipo.getTipo()), "PEQUENA")) {
                for (int i = 0; i < quant; i++) {
                    System.out.printf("Digite o %d valor: ", (i + 1));
                    vet[i] = Long.parseLong(sc.nextLine());
                }
            } else {

                for (int i = 0; i < vet.length; i++) {
                    vet[i] = RandomGenerator.randomGenerator();
                }
            }

            switch (algoritmo) {
                case "bubble sort", "bsort", "bubble", "b", "buble sort":
                    BubbleSortImpl bubbleSort = new BubbleSortImpl();

                    System.out.println("||||| Bubble Sort |||||\n");

                    bubbleSort.melhorCasoBubbleSort(quant, vet);
                    bubbleSort.medioCasoBubbleSort(quant, vet);
                    bubbleSort.piorCasoBubbleSort(quant, vet);
                    break;

                case "insertion sort", "isort", "insertion", "i":
                    InsertionSortImpl insertionSort = new InsertionSortImpl();

                    System.out.println("||||| Insertion Sort |||||\n");

                    insertionSort.melhorCasoInsertionSort(quant, vet);
                    insertionSort.medioInsertionSort(quant, vet);
                    insertionSort.piorCasoInsertionSort(quant, vet);
                    break;

                case "selection sort", "ssort", "selection", "s":
                    SelectionSortImpl selectionSort = new SelectionSortImpl();

                    System.out.println("||||| Selection Sort |||||\n");

                    selectionSort.melhorCasoSelectionSort(quant, vet);
                    selectionSort.medioCasoSelectionSort(quant, vet);
                    selectionSort.piorCasoSelectionSort(quant, vet);
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

        } while (true);
        System.out.println("Obrigado por utilizar nosso programa :)\n\n" +
                "Denis Alves\n" +
                "Ulisses Aguiar\n" +
                "BSI | Período 2 | 2025");
    }
}