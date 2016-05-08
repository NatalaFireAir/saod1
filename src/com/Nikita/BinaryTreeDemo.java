package com.Nikita;

import sun.security.provider.certpath.Vertex;

import java.util.Random;

public class BinaryTreeDemo {
    int []nValues = {10, 50, 100, 200, 400};
    Random rnd = new Random(System.currentTimeMillis());
    int[] createRandArray(int n) {
        int[] array = new int[n];
        boolean isDuplicate;
        for (int i = 0; i < n; i++) {
            while(true) {
                isDuplicate = false;
                array[i] = rnd.nextInt(10000);
                for (int j = 0; j < i; j++) {
                    if (array[i] == array[j]){
                        isDuplicate = true;
                    }
                }
                if (isDuplicate == false) break;
            }
        }
        return array;
    }
    public static void main(String args[]){
        BinaryTreeDemo demo = new BinaryTreeDemo();
    for (int n:demo.nValues) {
            int[] A = demo.createRandArray(n);
            System.out.println();
            for (int a:A){
                System.out.print(a + ", ");
            }
            System.out.println();
            try {
                System.out.println(n + " elements");
                System.out.println();
                System.out.println("AVL Tree");
                AVLTree avlTree = new AVLTree(A);
                System.out.println("Check = " + avlTree.checkSearchTree());
                avlTree.readLeftToRight();
                System.out.println();
                System.out.println("root: " + avlTree.root.key );
                System.out.println("average height: " + avlTree.findAverageHeight());
                System.out.println("Average rotation count: " + (float)avlTree.turnCounter/avlTree.findSize());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
