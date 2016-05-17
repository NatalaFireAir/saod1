package com.Nikita;

import java.util.Random;

/**
 * Класс, демонстрирующий особенности построения Дерева Оптимального поиска
 * приближенными методами
 */
public class BinaryTreeDemo {
    /**
     * генератор случайных значений
     */
    Random rnd = new Random(System.currentTimeMillis());
    /**
     * Массив значений n - варианты количества элементов дерева
     */
    int[] nValues = {10, 50, 100, 200, 400};

    /**
     * Метод, возвращающий массив заданной величины из неповторяющихся рандомных значений типа int
     */
    int[] createRandArray(int n, int m) {
        int[] array = new int[n];
        boolean isDuplicate;
        for (int i = 0; i < n; i++) {
            while (true) {
                isDuplicate = false;
                array[i] = rnd.nextInt(m);
                for (int j = 0; j < i; j++) {
                    if (array[i] == array[j]) {
                        isDuplicate = true;
                    }
                }
                if (isDuplicate == false) break;
            }
        }
        return array;
    }

    /**
     * Метод, возвращающий массив заданной величины из рандомных значений типа double
     */
    double[] createRandDoubleArray(int n) {
        double[] dArray = new double[n];
        for (int i = 0; i < n; i++) {
            dArray[i] = rnd.nextDouble();
        }
        return dArray;
    }

    public static void main(String args[]) {
        BinaryTreeDemo demo = new BinaryTreeDemo();

        for (int n : demo.nValues) {
            int[] A = demo.createRandArray(n, 10000);
            double[] B = demo.createRandDoubleArray(n);
            System.out.println("Array:  ");
            for (int a : A) {
                System.out.print(a + ", ");
            }
            System.out.println();
            try {
                System.out.println(n + " elements");
                System.out.println();
                System.out.println("Optimal Search Tree (1 variant):");
                OptimalSearchTree ost = new OptimalSearchTree(A, B);
                ost.createOST_A1();
                System.out.println("Check = " + ost.checkSearchTree());
                ost.readLeftToRight();
                System.out.println();
                System.out.println("root: " + ost.root.key + "(" + ost.root.weight + ")");
                System.out.println("average weight-height: " + ost.findAvgWeightHeight());
                System.out.println();
                System.out.println("Optimal Search Tree (2 variant):");
                OptimalSearchTree ost2 = new OptimalSearchTree(A, B);
                ost2.createOST_A2();
                System.out.println("Check = " + ost2.checkSearchTree());
                ost2.readLeftToRight();
                System.out.println();
                System.out.println("root: " + ost2.root.key + "(" + ost.root.weight + ")");
                System.out.println("average weight-height: " + ost2.findAvgWeightHeight());
                System.out.println();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
