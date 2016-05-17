package com.Nikita;

import java.util.Random;

/**
 * Класс, демонстрирующий особенности работы АВЛ-Дерева
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
     * Метод, возвращающий массив заданной величины из неповторяющихся рандомных значений
     */
    int[] createRandArray(int n) {
        int[] array = new int[n];
        boolean isDuplicate;
        for (int i = 0; i < n; i++) {
            while (true) {
                isDuplicate = false;
                array[i] = rnd.nextInt(10000);
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

    public static void main(String args[]) {
        BinaryTreeDemo demo = new BinaryTreeDemo();
        for (int n : demo.nValues) {
            int[] A = demo.createRandArray(n);
            System.out.println("Array:");
            for (int a : A) {
                System.out.print(a + ", ");
            }
            System.out.println();
            try {
                System.out.println(n + " elements");
                System.out.println();
                System.out.println("AVL-Tree:");
                AVLTree avlt = new AVLTree(A);
                avlt.readLeftToRight();
                System.out.println();
                System.out.println("root: " + avlt.root.key);
                System.out.println("average height: " + avlt.findAverageHeight());
                System.out.println("average rotate count: " + (float)avlt.turnCounter / avlt.findSize());
                System.out.println();
            } catch (Exception e) {
                System.out.println("Вершина с таким значением уже есть");
            }
        }
    }
}