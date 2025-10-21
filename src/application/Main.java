package application;


import entities.BubbleSort;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("quantidade de números para serem ordenados:");
        int quant = sc.nextInt();

        BubbleSort bSort = new BubbleSort(quant);

        bSort.bSortPrint();
        bSort.getVet();
    }
}