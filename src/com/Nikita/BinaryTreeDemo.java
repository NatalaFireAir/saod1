package com.Nikita;
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
                System.out.println("B-Tree");
                BTree bTree = new BTree(A);
                System.out.println("Check = " + bTree.checkSearchTree());
                bTree.readLeftToRight();
                System.out.println();
                System.out.println("root: " + bTree.root.key );
                System.out.println("average height: " + bTree.findAverageHeight());
                System.out.println("height: " + bTree.findHeight());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
